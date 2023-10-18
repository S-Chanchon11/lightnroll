package com.example.light

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.UUID
import android.app.Activity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bt: BluetoothSocket
    lateinit var deviceName: String
    lateinit var deviceAddress: String
    lateinit var btAdapter: BluetoothAdapter
    lateinit var btManager: BluetoothManager
    lateinit var btDevice: BluetoothDevice

    private val STATUS: Int = 1 //Bluetooth connection status
    private val REQUEST_ENABLE_BT = 1
    var m_bluetoothAdapter: BluetoothAdapter? = null
    lateinit var m_pairedDevices: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1

    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(m_bluetoothAdapter == null){
            toast("this device doesn't support bluetooth")
            return
        }
        if(!m_bluetoothAdapter!!.isEnabled){
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
        }

        select_device_refresh.setOnClickListener{ pairedDeviceList() }

    }

    private fun pairedDeviceList(){
        m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
        val list:ArrayList<BluetoothDevice> = ArrayList()

        if(!m_pairedDevices.isEmpty()){
            for(device: BluetoothDevice in m_pairedDevices){
                list.add(device)
                Log.i("device", ""+device)
            }
        }else{
            toast("no paired bluetooth devices found")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        select_device_list.adapter = adapter
        select_device_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val device: BluetoothDevice = list[position]
            val address: String = device.address

            val intent = Intent(this, Control::class.java)
            intent.putExtra(EXTRA_ADDRESS, address)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
            if(resultCode == Activity.RESULT_OK){
                if(m_bluetoothAdapter!!.isEnabled){
                    Toast.makeText(baseContext,"Bluetooth has been enabled.",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(baseContext,"Bluetooth has been disabled.",Toast.LENGTH_SHORT).show()
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(baseContext,"Bluetooth enabling has been canceled.",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //unregisterReceiver(receiver)
    }

    fun BluetoothConn() {

        btManager = getSystemService(BluetoothManager::class.java)
        btAdapter = btManager.adapter
        if (btAdapter == null) {
            Log.w("BT ADAPTER", "device not support")
        }

        if (btAdapter?.isEnabled == false) {
            val Intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // if permission is not granted
                return
            }
            startActivityForResult(Intent, REQUEST_ENABLE_BT)
        }

        val receiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val action: String? = intent.action
                when (action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        // Discovery has found a device. Get the BluetoothDevice
                        // object and its info from the Intent.
                        val device: BluetoothDevice? =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        deviceName = device.name
                        deviceAddress = device?.address // MAC address
                    }
                }
            }
        }
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)

        val discoverDevicesIntent = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, discoverDevicesIntent)
        btAdapter.startDiscovery()
        val uuid: UUID = UUID.fromString("00000001-0000-1000-8000-22305F9B34FB")

        //val device = btAdapter.getRemoteDevice("Device_Address")
        val socket = btDevice.createRfcommSocketToServiceRecord(uuid)

        socket.connect()
    }


}





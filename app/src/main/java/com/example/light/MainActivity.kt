package com.example.light

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var bt:BluetoothSocket
    lateinit var deviceName: String
    lateinit var deviceAddress: String
    lateinit var btAdapter: BluetoothAdapter
    lateinit var btManager: BluetoothManager

    private val STATUS: Int = 1 //Bluetooth connection status
    private val REQUEST_ENABLE_BT = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btManager = getSystemService(BluetoothManager::class.java)
        btAdapter = btManager.adapter
        if (btAdapter == null){
            Log.w("BT ADAPTER","device not support")
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

        private val receiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val action: String? = intent.action
                when(action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        // Discovery has found a device. Get the BluetoothDevice
                        // object and its info from the Intent.
                        val device: BluetoothDevice? =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        val deviceName = device.name
                        val deviceHardwareAddress = device?.address // MAC address
                    }
                }
            }
        }
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }
}
package com.example.light

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var bt:BluetoothSocket
    lateinit var deviceName: String
    lateinit var deviceAddress: String
    private val STATUS: Int = 1 //Bluetooth connection status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (STATUS == 1){

        }
    }
}
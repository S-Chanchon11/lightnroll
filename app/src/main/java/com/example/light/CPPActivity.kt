package com.example.light

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CPPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cppactivity)

        // Checking permissions.
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO
        )
        for (s in permissions) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                // Some permissions are not granted, ask the user.
                ActivityCompat.requestPermissions(this, permissions, 0)
                return
            }
        }

        // Got all permissions, initialize.
        initialize()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Called when the user answers to the permission dialogs.
        if (requestCode != 0 || grantResults.size < 1 || grantResults.size != permissions.size) return
        var hasAllPermissions = true
        for (grantResult in grantResults) if (grantResult != PackageManager.PERMISSION_GRANTED) {
            hasAllPermissions = false
            Toast.makeText(
                applicationContext,
                "Please allow all permissions for the app.",
                Toast.LENGTH_LONG
            ).show()
        }
        if (hasAllPermissions) initialize()
    }

    private fun initialize() {
        // Get the device's sample rate and buffer size to enable
        // low-latency Android audio output, if available.
        var samplerateString: String? = null
        var buffersizeString: String? = null
        val audioManager = this.getSystemService(AUDIO_SERVICE) as AudioManager
        if (audioManager != null) {
            samplerateString = audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)
            buffersizeString =
                audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER)
        }
        if (samplerateString == null) samplerateString = "48000"
        if (buffersizeString == null) buffersizeString = "480"
        val samplerate = samplerateString.toInt()
        val buffersize = buffersizeString.toInt()
        System.loadLibrary("FrequencyDomainExample") // load native library
        FrequencyDomain(samplerate, buffersize) // start audio processing
    }

    // onDestroy - Cleanup resources.
    override fun onDestroy() {
        super.onDestroy()
        Cleanup()
    }

    // Functions implemented in the native library.
    private external fun FrequencyDomain(samplerate: Int, buffersize: Int)
    private external fun Cleanup()
}

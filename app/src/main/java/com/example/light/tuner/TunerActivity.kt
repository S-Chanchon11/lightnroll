package com.example.light.tuner

import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.light.R
import java.io.IOException

class TunerActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val REQUEST_RECORD_PERMISSION = 100
    private var permissionToRecordAccepted = false
    private lateinit var recorder: MediaRecorder
    private lateinit var textView: TextView
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var updateMicLevelRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuner)

        webView = findViewById(R.id.webView)
        textView = findViewById(R.id.micLevelTextView)
//        val perm = checkPermissionsRecord()
        requestPermissions(arrayOf("android.permission.RECORD_AUDIO"), REQUEST_RECORD_PERMISSION)
        // Load the webview
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show()

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
//        webView.webChromeClient = WebChromeClient()
        webView.settings.mediaPlaybackRequiresUserGesture = false
//        webview.settings.allowContentAccess
        webView.addJavascriptInterface(WebAppInterface(this), "Android")
        webView.loadData(
            """
            <html>
            <body>
                <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />
                <input type="button" value="Test Microphone" onClick="requestAudioRecording()" />
                <script type="text/javascript">
                    function showAndroidToast(toast) {
                        Android.showToast(toast);
                    }
                    
                    function requestAudioRecording() {
                        Android.testMicrophone();
                    }
                </script>
            </body>
            </html>
            """,
            "text/html",
            "UTF-8"
        )

        // Request audio recording permission
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_PERMISSION)
//        webview.webViewClient = WebViewClient()
//        webview.webChromeClient = WebChromeClient()
//        webview.settings.mediaPlaybackRequiresUserGesture = false; // Allow audio recording

//            webview.addJavascriptInterface(WebAppInterface(this), "Android")
//            webview.loadUrl("https://pmanikas.github.io/vue-guitar-tuner-demo/")
//            webview.loadUrl("http://192.168.1.37:5175")
//        webview.loadUrl("https://www.tonegym.co/tool/item?id=vocal-range-test-tool")
//            webview.loadUrl("http://localhost:5175")

//        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
//        webview.zoomBy(1.2F)

//        if(perm){
//            webview.settings.javaScriptEnabled = true
//            webview.loadUrl("http://192.168.1.37:5175")
//        } else {
//            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
//            return
//        }
//        webview.loadUrl("http://localhost:5175")
//        webview.loadUrl("http://192.168.1.37:5175")
//        webview.loadUrl("https://pmanikas.github.io/vue-guitar-tuner-demo/")

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
//        webView.setWebViewClient(webViewClient);
//
//        //webView.loadUrl("https://www.journaldev.com");
//        webView.loadData("<html><body>Hello, world!</body></html>", "text/html", "UTF-8");
    }

//    private fun checkPermissionsRecord(): Boolean {
//        // this method is used to check permission
//        val result =
//            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//        return result == PackageManager.PERMISSION_GRANTED
//    }
    override fun onRequestPermissionsResult(
        permsRequestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults)
        when (permsRequestCode) {
            100 -> {
                val audioAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                permissionToRecordAccepted = true
            }
        }
    }
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_PERMISSION) {
//            grantResults[0] == PackageManager.PERMISSION_GRANTED
//        } else {
//            false
//        }
//    }

    inner class WebAppInterface(private val context: TunerActivity) {
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun testMicrophone() {
            if (permissionToRecordAccepted) {
                recorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    setOutputFile("/dev/null")
                    try {
                        prepare()
                        start()
                        context.runOnUiThread {
                            Toast.makeText(context, "Microphone is working", Toast.LENGTH_SHORT).show()
                        }
                        handler.post(updateMicLevelRunnable)
                    } catch (e: IOException) {
                        context.runOnUiThread {
                            Toast.makeText(context, "Microphone test failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                updateMicLevelRunnable = object : Runnable {
                    override fun run() {
                        try {
                            val maxAmplitude = recorder.maxAmplitude
                            val micLevel = if (maxAmplitude > 0) (20 * Math.log10(maxAmplitude.toDouble())).toInt() else 0
                            textView.text = "Mic Level: $micLevel dB"
                            handler.postDelayed(this, 100)
                        } catch (e: IllegalStateException) {
                            Toast.makeText(context, "Error reading mic level", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                context.runOnUiThread {
                    Toast.makeText(context, "Permission to record audio not granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        recorder.release()
        handler.removeCallbacks(updateMicLevelRunnable)
    }
}

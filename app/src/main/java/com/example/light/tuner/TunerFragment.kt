package com.example.light.tuner

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.light.R
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class TunerFragment : Fragment() {

    private val TAG = "GuitarTuner"
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200
    private var permissionToRecordAccepted = false
    private var audioRecord: AudioRecord? = null
    private var bufferSize: Int = 0
    private var recordingThread: Thread? = null
    private var isRecording = false
    private var movingAverageBuffer: MutableList<Double> = mutableListOf()
    private val movingAverageWindowSize = 20
    private lateinit var pitchTextView: TextView
    private lateinit var frequencyTextView: TextView
    private lateinit var s1: TextView
    private lateinit var s2: TextView
    private lateinit var s3: TextView
    private lateinit var s4: TextView
    private lateinit var s5: TextView
    private lateinit var s6: TextView
    private lateinit var lineIndicatorView: LineIndicatorView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_tuner, container, false)

        pitchTextView = view.findViewById(R.id.pitchTextView)
        frequencyTextView = view.findViewById(R.id.frequencyTextView)
        lineIndicatorView = view.findViewById(R.id.lineIndicatorView)
        s1 = view.findViewById(R.id.first_string)
        s2 = view.findViewById(R.id.second_string)
        s3 = view.findViewById(R.id.third_string)
        s4 = view.findViewById(R.id.fourth_string)
        s5 = view.findViewById(R.id.fifth_string)
        s6 = view.findViewById(R.id.sixth_string)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val permission = Manifest.permission.RECORD_AUDIO
        if (ContextCompat.checkSelfPermission(requireActivity().applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), REQUEST_RECORD_AUDIO_PERMISSION)
        } else {
            permissionToRecordAccepted = true
            setupAudioRecorder()
        }
    }

    override fun onPause() {
        super.onPause()
        audioRecord?.stop()
        Log.d(TAG, "onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroy")
    }

    @SuppressLint("MissingPermission")
    private fun setupAudioRecorder() {
        bufferSize = AudioRecord.getMinBufferSize(
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )
        startRecording()
    }

    private fun startRecording() {
        isRecording = true
        recordingThread = Thread(
            Runnable {
                audioRecord?.startRecording()
                val buffer = ShortArray(bufferSize)
                while (isRecording) {
                    val read = audioRecord?.read(buffer, 0, bufferSize)

                    if (read != null && read > 0) {
                        processAudio(buffer)
                    }
                }
                audioRecord?.stop()
                Log.d(TAG, "Recording stopped")
            }
        )
        recordingThread?.start()
    }

    @SuppressLint("ResourceAsColor")
    private fun processAudio(buffer: ShortArray) {
        // Convert short array to double array for FFT
        val new_double = preEmphasis(buffer, 0.5)

        // note:ShortArray serve more efficient since it only hold small data ( in specific range)
//        val doubleBuffer = buffer.map { it.toDouble() }.toDoubleArray()
        val doubleBuffer = new_double.map { it }.toDoubleArray()

        val windowedBuffer = applyHammingWindow(doubleBuffer)
        // Pad buffer to the nearest power of 2
        val paddedBuffer = padToPowerOfTwo(windowedBuffer)

        val new_padded = applyWindow(paddedBuffer)

        // Perform FFT
        val fft = FastFourierTransformer(DftNormalization.STANDARD)
//        val transformed = fft.transform(paddedBuffer, TransformType.FORWARD)
        val transformed = fft.transform(new_padded, TransformType.FORWARD)

        // Calculate magnitude spectrum
        val magnitudes = transformed.map { sqrt(it.real.pow(2) + it.imaginary.pow(2)) }

        // Find peak magnitude and corresponding frequency
//        val maxIndex = magnitudes.indices.maxByOrNull { magnitudes[it] } ?: -1
        val noiseThreshold = 0.01
        val maxIndex = magnitudes.indices
            .filter { magnitudes[it] > noiseThreshold }
            .maxByOrNull { magnitudes[it] } ?: -1

        if (maxIndex == -1) {
            requireActivity().runOnUiThread {
                pitchTextView.text = "No pitch detected"
                frequencyTextView.text = "Frequency: - Hz"
            }
            return
        }

        val sampleRate = 44100

//        val frequency = maxIndex * sampleRate / paddedBuffer.size
//        val frequency = maxIndex * sampleRate / new_padded.size
        val frequency = interpolateFrequency(magnitudes, maxIndex, sampleRate, paddedBuffer.size)

//        // Apply moving average smoothing
        val smoothedFrequency = applyMovingAverage(frequency)

        // Convert frequency to note
        val note = frequencyToNote(smoothedFrequency)

        // Update UI on the main thread
        requireActivity().runOnUiThread {
            pitchTextView.text = "Detected Pitch: $note"
            frequencyTextView.text = "Frequency: " + BigDecimal(frequency).setScale(2, RoundingMode.HALF_EVEN)
            updateIndicator(frequency.toDouble().toFloat())
            when (note) {
                "E2" -> s6.setBackgroundColor(R.color.yellowN1)
                "A" -> s5.setBackgroundColor(R.color.yellowN1)
                "D" -> s4.setBackgroundColor(R.color.yellowN1)
                "G" -> s3.setBackgroundColor(R.color.yellowN1)
                "B" -> s2.setBackgroundColor(R.color.yellowN1)
                "E6" -> s1.setBackgroundColor(R.color.yellowN1)
            }
        }
    }

    private fun findClosestNoteFrequency(pitch: Float): Float {
        val closestNote = notes.minByOrNull { abs(it.frequency - pitch) }
        return closestNote?.frequency ?: pitch
    }

    private fun updateIndicator(pitch: Float) {
        val targetFrequency = findClosestNoteFrequency(pitch)
        val deviation = pitch - targetFrequency
        val position = (deviation / targetFrequency) / 2 + 0.5f
        lineIndicatorView.updatePosition(position.coerceIn(0.0f, 1.0f))
    }

    private fun padToPowerOfTwo(buffer: DoubleArray): DoubleArray {
        val newSize = nextPowerOfTwo(buffer.size)
        return buffer.copyOf(newSize)
    }

    private fun nextPowerOfTwo(size: Int): Int {
        var n = size
        n--
        n = n or (n shr 1)
        n = n or (n shr 2)
        n = n or (n shr 4)
        n = n or (n shr 8)
        n = n or (n shr 16)
        return n + 1
    }

    private fun parabolicInterpolation(
        y1: Double,
        y2: Double,
        y3: Double,
        x2: Int
    ): Int {
        val num = x2 * (y3 - y1)
        val den = 2 * (2 * y2 - y1 - y3)
        return (num / den + x2).toInt()
    }

    private fun preEmphasis(buffer: ShortArray, alpha: Double): DoubleArray {
        val doubleBuffer = buffer.map { it.toDouble() }.toDoubleArray()
        val preEmphasizedBuffer = DoubleArray(doubleBuffer.size)
        preEmphasizedBuffer[0] = doubleBuffer[0]
        for (i in 1 until doubleBuffer.size) {
            preEmphasizedBuffer[i] = doubleBuffer[i] + alpha * doubleBuffer[i - 1]
        }
        return preEmphasizedBuffer
    }

    private fun applyWindow(buffer: DoubleArray): DoubleArray {
        val windowedBuffer = DoubleArray(buffer.size)
        val windowSize = buffer.size
        for (i in 0 until windowSize) {
            val n = i.toDouble() / (windowSize - 1)
            windowedBuffer[i] = buffer[i] * (0.5 - 0.5 * Math.cos(Math.PI * n))
        }
        return windowedBuffer
    }

    private fun frequencyToNote(frequency: Double): String {
        val notes = arrayOf("E2", "A", "D", "G", "B", "E6")
        val frequencies = doubleArrayOf(82.41, 110.0, 146.83, 196.0, 246.94, 329.63)

        var minDiff = Double.MAX_VALUE
        var closestNote = ""

        for (i in notes.indices) {
            val diff = abs(frequency - frequencies[i])
            if (diff < minDiff) {
                minDiff = diff
                closestNote = notes[i]
            }
        }

        return closestNote
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> {
                permissionToRecordAccepted = if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupAudioRecorder()
                    true
                } else {
                    false
                }
            }
        }
        if (!permissionToRecordAccepted) requireActivity().finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRecording = false
        recordingThread?.join()
        audioRecord?.release()
    }
    private fun interpolateFrequency(magnitudes: List<Double>, index: Int, sampleRate: Int, bufferSize: Int): Double {
        if (index > 0 && index < magnitudes.size - 1) {
            val alpha = magnitudes[index - 1]
            val beta = magnitudes[index]
            val gamma = magnitudes[index + 1]

            val peakIndex = index + 0.5 * (alpha - gamma) / (alpha - 2 * beta + gamma)
            return peakIndex * sampleRate / bufferSize
        }
        return index * sampleRate / bufferSize.toDouble()
    }

    //
    private fun applyMovingAverage(frequency: Double): Double {
        movingAverageBuffer.add(frequency)
        if (movingAverageBuffer.size > movingAverageWindowSize) {
            movingAverageBuffer.removeAt(0)
        }
        return movingAverageBuffer.average()
    }
    private fun applyHammingWindow(buffer: DoubleArray): DoubleArray {
        val size = buffer.size
        val hammingWindow = DoubleArray(size) { 0.54 - 0.46 * kotlin.math.cos(2.0 * Math.PI * it / (size - 1)) }
        return buffer.mapIndexed { index, value -> value * hammingWindow[index] }.toDoubleArray()
    }
    val notes = listOf(
        TunerModel("D", 146.83f),
        TunerModel("G", 196.00f),
        TunerModel("A", 110.00f),
        TunerModel("B", 246.94f),
        TunerModel("E2", 82.41f),
        TunerModel("E6", 329.63f)
    )
}

package com.example.light.upload

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.light.R

class UploadFragment : Fragment() {

    private lateinit var pdfSelector: TextView
    private lateinit var pdfUri: Uri
    private lateinit var pdfviwer: com.github.barteksc.pdfviewer.PDFView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_upload, container, false)
        pdfSelector = view.findViewById(R.id.pdfTxt) as TextView
        pdfSelector.text = "Select a pdf file"
        pdfviwer = view.findViewById(R.id.pdfSect)
        pdfSelector.setOnClickListener {
            selectPdf()
        }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // For loading PDF
        when (requestCode) {
            12 -> if (resultCode == Activity.RESULT_OK) {
                pdfUri = data?.data!!
                val uri: Uri = data?.data!!
                val uriString: String = uri.toString()
                var pdfName: String? = null
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        // Setting the PDF to the TextView
                        myCursor = requireContext().contentResolver.query(uri, null, null, null, null)
                        if (myCursor != null && myCursor.moveToFirst()) {
                            pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                            pdfSelector.text = pdfName
                            pdfviwer.fromAsset(pdfName).load()
                        }
                    } finally {
                        myCursor?.close()
                    }
                }
            }
        }
    }

    private fun selectPdf() {
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 12)
        return
    }
}

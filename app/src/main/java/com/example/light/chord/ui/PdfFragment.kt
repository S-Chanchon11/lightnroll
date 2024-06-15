package com.example.light.chord.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.light.R
import com.example.light.expandableRecyclerview.ui.LessonFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.github.barteksc.pdfviewer.PDFView as PDFViewer

class PdfFragment : Fragment() {

    private lateinit var pdfView: PDFViewer
    private lateinit var nextTxt: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)

        pdfView = view.findViewById(R.id.pdfView)
        nextTxt = view.findViewById(R.id.nextText)

        var actionbar = (requireActivity() as AppCompatActivity).supportActionBar
        actionbar?.hide()
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.GONE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lessonFragment = LessonFragment()
        var pageNum = 1
        setFragmentResultListener("detailKey") { requestKey, bundle ->
            val resultFromFragment = bundle.getString("bundleKey")
            if (resultFromFragment != null) {
                pdfView.fromAsset(resultFromFragment).pages(0).load()
                nextTxt.setOnClickListener {
                    pdfView.fromAsset(resultFromFragment).pages(pageNum).load()
                    if (pageNum == pdfView.pageCount) {
                        activity?.run {
                            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, lessonFragment, tag)
                                .addToBackStack(tag)
                                .commit()
                        }
                    } else {
                        pageNum++
                    }
                }
            } else {
            }
        }
    }
}

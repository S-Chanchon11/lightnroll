package com.example.light.expandableList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import com.example.light.R
import com.example.light.expandableList.ui.adapter.PracticeExpandableListAdapter
import com.example.light.expandableList.viewmodel.PracticeViewModel
import com.github.barteksc.pdfviewer.PDFView as PDFViewer

class PracticeFragment : Fragment() {

    private lateinit var practiceViewModel: PracticeViewModel
    private var expandableListView: ExpandableListView? = null
    private lateinit var textChord: TextView
    private lateinit var expandableListDetail: HashMap<String, List<String>>
    private lateinit var expandableListDetail2: HashMap<String, List<String>>
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListAdapter: PracticeExpandableListAdapter
    private lateinit var pdfView: PDFViewer

//    private var detail: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)

        pdfView = view.findViewById(R.id.pdfView)

//        expandableListView = view.findViewById(R.id.expanded_menu)
//        practiceViewModel = ViewModelProvider(this).get(PracticeViewModel::class.java)
//        // observeData()
//        Log.d("Fragment", "Practice")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        pdfView.fromAsset("basic_1.pdf").pages(0).load()
//        pdfView.fromAsset("basic_1.pdf").load()

//        pdfView.fromAsset(filename).load()
//        Log.d("PracticeFragment", pdfView.tableOfContents.toString())
//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            resultFromFragment = bundle.getString("bundleKey")
//            Toast.makeText(context?.applicationContext, resultFromFragment, Toast.LENGTH_SHORT).show()
//        }
        setFragmentResultListener("detailKey") { requestKey, bundle ->
            val resultFromFragment = bundle.getString("bundleKey")
            if (resultFromFragment != null) {
                Toast.makeText(context, resultFromFragment, Toast.LENGTH_SHORT).show()
                pdfView.fromAsset(resultFromFragment).load()
            } else {
                Toast.makeText(context, "No filename received", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeData() {
        try {
            practiceViewModel.practiceDetail.observe(
                viewLifecycleOwner,
                Observer { it ->
                }
            )
        } catch (e: Exception) {
            print(e)
        }
    }

//    private fun showPractice(data: PracticeModel) {
//        try {
//            practiceViewModel.getPractice(
//                chord = data.chord,
//                s6 = data.position["s6"]!!,
//                s5 = data.position["s5"]!!,
//                s4 = data.position["s4"]!!,
//                s3 = data.position["s3"]!!,
//                s2 = data.position["s2"]!!,
//                s1 = data.position["s1"]!!,
//                f6 = data.fingerings["f6"]!!,
//                f5 = data.fingerings["f5"]!!,
//                f4 = data.fingerings["f4"]!!,
//                f3 = data.fingerings["f3"]!!,
//                f2 = data.fingerings["f2"]!!,
//                f1 = data.fingerings["f1"]!!
//            )
//            practiceViewModel.practiceDetail.observe(
//                this,
//                Observer { practiceDetail ->
// //                    textChord?.text = practiceDetail
//                }
//            )
//        } catch (e: Exception) {
//            print(e)
//        }
//    }
//    private fun observeChordData() {
//            practiceViewModel.chord.observe(this, Observer { chord ->
//            // Update the UI with the user data
//            textViewId.text = chord.chord
//            textViewName.text = chord.position
//            textViewEmail.text = chord.finger
//        })
//    }
//    private fun requestPractice(){
//        try {
//            practiceViewModel.loadChord()
//        } catch (e : Exception){
//            print(e.message)
//        }
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)
//        mRecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
//        val layoutManager = GridLayoutManager(activity, 2)
//        mRecyclerView!!.layoutManager = layoutManager
//        dataReference = FirebaseFirestore.getInstance()
//        readFirestore(dataList)
// //        data.add(ChordModel("C"))
// //        data.add(ChordModel("D"))
// //        data.add(ChordModel("E"))
// //        data.add(ChordModel("F"))
// //        data.add(ChordModel("G"))
// //        data.add(ChordModel("A"))
// //        data.add(ChordModel("B"))
//        mListadapter = PracticeAdapterController(dataList)
//        mRecyclerView!!.adapter = mListadapter
//
//        return view
//    }
//    private fun readFirestore(data: MutableList<ChordModel>){
//
//        var db = dataReference.collection("Chord")
//        db.orderBy("Variant").get()
//            .addOnSuccessListener { snapshot ->
//                if(snapshot!=null){
//                    dataList.clear()
//                    val userObj = snapshot.toObjects(ChordModel::class.java)
//                    for(dataObj in userObj){
//                        dataList.add(dataObj)
//                        }
//                    }
//                }
//            .addOnFailureListener {
//                Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
//            }
//
//    }
}

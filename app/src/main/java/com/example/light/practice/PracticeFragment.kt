package com.example.light.practice

import android.content.ClipData
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.ChordModel
import com.example.light.R
import com.google.firebase.firestore.FirebaseFirestore

class PracticeFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mListadapter: PracticeAdapterController? = null
    lateinit var dataReference: FirebaseFirestore
    lateinit var dataList: MutableList<ChordModel>
    /*
    In MVC arch, Fragment will act as VIEW
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)
        mRecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView!!.layoutManager = layoutManager
        dataReference = FirebaseFirestore.getInstance()
        readFirestore(dataList)
//        data.add(ChordModel("C"))
//        data.add(ChordModel("D"))
//        data.add(ChordModel("E"))
//        data.add(ChordModel("F"))
//        data.add(ChordModel("G"))
//        data.add(ChordModel("A"))
//        data.add(ChordModel("B"))
        mListadapter = PracticeAdapterController(dataList)
        mRecyclerView!!.adapter = mListadapter

        return view
    }
    private fun readFirestore(data: MutableList<ChordModel>){

        var db = dataReference.collection("Chord")
        db.orderBy("Variant").get()
            .addOnSuccessListener { snapshot ->
                if(snapshot!=null){
                    dataList.clear()
                    val userObj = snapshot.toObjects(ChordModel::class.java)
                    for(dataObj in userObj){
                        dataList.add(dataObj)
                        }
                    }
                }
            .addOnFailureListener {
                Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
            }

    }

}

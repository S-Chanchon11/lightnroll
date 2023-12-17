package com.example.light.practice

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView



/*
In MVC arch, Adapter will act as CONTROLLER
 */

class PracticeAdapterController(
    context: Context, expandableListTitle: List<String>,
    expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    private val context: Context
    private val expandableListTitle: List<String>
    private val expandableListDetail: HashMap<String, List<String>>

    init {
        this.context = context
        this.expandableListTitle = expandableListTitle
        this.expandableListDetail = expandableListDetail
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandableListDetail[expandableListTitle[listPosition]]
            .get(expandedListPosition)
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View, parent: ViewGroup
    ): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val expandedListTextView = convertView
            .findViewById<View>(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail[expandableListTitle[listPosition]]
            .size
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View, parent: ViewGroup
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView
            .findViewById<View>(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}

//class PracticeAdapterController(data: MutableList<ChordModel>) :
//    RecyclerView.Adapter<PracticeAdapterController.ViewHolder>() {
//    private val dataList: MutableList<ChordModel>
//
//    init {
//        dataList = data
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var textViewText: TextView
//
//        init {
//            textViewText = itemView.findViewById<View>(R.id.chordName) as TextView
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.chord_list, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textViewText.text = dataList[position].chord
//        Log.d(javaClass.toString(), dataList[position].chord)
//        holder.itemView.setOnClickListener {
////            Toast.makeText(holder,
////                "Item $position is clicked.",
////                Toast.LENGTH_SHORT
////            ).show()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//}

package com.example.light.practice.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.light.R

/*
In MVC arch, Adapter will act as CONTROLLER
 */

class PracticeExpandableListAdapter(
    context: Context,
    expandableListTitle: List<String>,
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
            ?.get(expandedListPosition) ?: ""
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    // items to show after expand
    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String?
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.chord_diagram, null)
        }
        val expandableText1 = convertView?.findViewById<View>(R.id.s6) as TextView
        expandableText1.text = expandedListText
//        val expandedListTextView = convertView
//            ?.findViewById<View>(R.id.chordDetail) as TextView
//        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail[expandableListTitle[listPosition]]?.size ?: 0
    }

    override fun getGroup(listPosition: Int): Any? {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    // items to before expand
    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String?
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.chord_list, null)
        }
        val listTitleTextView = convertView
            ?.findViewById<View>(R.id.chordHeader) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }
}

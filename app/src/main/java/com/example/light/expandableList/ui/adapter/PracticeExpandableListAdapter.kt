package com.example.light.expandableList.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.light.R

class PracticeExpandableListAdapter(
    context: Context,
    expandableListTitle: List<String>,
    expandableListDetail: HashMap<String, List<String>>,
    expandableListFinger: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    private val context: Context
    private val expandableListTitle: List<String>
    private val expandableListDetail: HashMap<String, List<String>>
    private val expandableListFinger: HashMap<String, List<String>>

    init {
        this.context = context
        this.expandableListTitle = expandableListTitle
        this.expandableListDetail = expandableListDetail
        this.expandableListFinger = expandableListFinger
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandableListDetail[expandableListTitle[listPosition]]!!.get(expandedListPosition)
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
        val expandedListText2 = getChild2(listPosition, expandedListPosition) as String?
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.lesson_chord_menu, null)
        }
//        val expandableText6 = convertView?.findViewById<View>(R.id.s6) as TextView
//        val expandableText5 = convertView?.findViewById<View>(R.id.s5) as TextView
//        val expandableText4 = convertView?.findViewById<View>(R.id.s4) as TextView
//        val expandableText3 = convertView?.findViewById<View>(R.id.s3) as TextView
//        val expandableText2 = convertView?.findViewById<View>(R.id.s2) as TextView
//        val expandableText1 = convertView?.findViewById<View>(R.id.s1) as TextView
//        val expandlist : List<TextView>
//        expandlist = ArrayList()
//
//        expandlist.add(expandableText6) // 0
//        expandlist.add(expandableText5) // 1
//        expandlist.add(expandableText4) // 2
//        expandlist.add(expandableText3) // 3
//        expandlist.add(expandableText2) // 4
//        expandlist.add(expandableText1) // 5

//        if (expandedListPosition == 0) {
//            expandableText6.text = expandedListText
//        }
//          if()
//        Log.d("test func pos",expandedListText.toString())
//        Log.d("test func fing",expandedListText2.toString())
//        if (expandedListText?.equals("x") == true) {
//            Log.d("test func","found x")
//        }
        // expandedListPosition = number of element in list
        // expandedListText2 = finger list
        // expandedListText = position list (show)

//        if (expandedListPosition == expandedListText2?.toInt()) {
//            expandlist[expandedListPosition].text = expandedListText
//        } else
//

//            if (expandedListPosition != expandedListText2?.toInt()) {
//            if (expandedListPosition == 3 && expandedListText2?.toInt()==3) {
//                expandlist[3].text = expandedListText
//            } else if (expandedListPosition == 2 && expandedListText2?.toInt()==2) {
//                expandlist[2].text = expandedListText
//            } else if (expandedListPosition == 1) {
//                val index = expandedListText2?.toInt()
//                when (index) {
// //                    1 -> expandlist[index].text = expandedListText
// //                    2 -> expandlist[index].text = expandedListText
//                    3 -> expandlist[expandedListPosition].text = expandedListText
// //                    4 -> expandlist[index].text = expandedListText
// //                    5 -> expandlist[index].text = expandedListText
//                }
//
//            } else if (expandedListPosition == 0 && expandedListText2?.toInt()==0) {
//                expandlist[0].text = expandedListText
//            }
//        }
//        if (expandedListPosition == 0 && expandedListText2?.equals("0") == true) {
//            expandableText6.text = expandedListText
//        } else if (expandedListPosition == 1 && expandedListText2?.equals("1") == true) {
//            expandableText5.text = expandedListText
//        } else if (expandedListPosition == 2 && expandedListText2?.equals("1") == true) {
//            expandableText4.text = expandedListText
//        } else if (expandedListPosition == 3 && expandedListText2?.equals("1") == true) {
//            expandableText3.text = expandedListText
//        } else if (expandedListPosition == 4 && expandedListText2?.equals("1") == true) {
//            expandableText2.text = expandedListText
//        } else if (expandedListPosition == 5 && expandedListText2?.equals("1") == true) {
//            expandableText1.text = expandedListText
//        }

//        if (expandedListPosition == 1) {
//            expandableText5.text = expandedListText
//        }
//        if (expandedListPosition == 2) {
//            expandableText4.text = expandedListText
//        }
//        if (expandedListPosition == 3) {
//            expandableText3.text = expandedListText
//        }
//        if (expandedListPosition == 4) {
//            expandableText2.text = expandedListText
//        }
//        if (expandedListPosition == 5)
//            expandableText1.text = expandedListText
//        val expandedListTextView = convertView
//            ?.findViewById<View>(R.id.chordDetail) as TextView
//        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail[expandableListTitle[listPosition]]!!.size
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

    private fun test(
        listPosition: Int,
        expandedListPosition: Int
    ) {
        // Log.d("Adapter",getChildrenCount(listPosition).toString())
    }
    private fun getChild2(listPosition: Int, expandedListPosition: Int): Any {
        return expandableListFinger[expandableListTitle[listPosition]]!!.get(expandedListPosition)
    }
}

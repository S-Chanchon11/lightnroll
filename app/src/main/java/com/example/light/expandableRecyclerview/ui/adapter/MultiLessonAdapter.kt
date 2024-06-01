// package com.example.light.expandableRecyclerview.ui.adapter
//
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import android.widget.TextView
// import androidx.recyclerview.widget.RecyclerView
// import com.example.light.R
// import com.example.light.expandableRecyclerview.model.BasicLessonModel
// import com.example.light.expandableRecyclerview.model.InterLessonModel
//
// class MultiLessonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val items = mutableListOf<Any>()
//
//    override fun getItemViewType(position: Int): Int {
//        return when (items[position]) {
//            is BasicLessonModel -> VIEW_TYPE_A
//            is InterLessonModel -> VIEW_TYPE_B
//            else -> throw IllegalArgumentException("Invalid type of data at position $position")
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            VIEW_TYPE_A -> {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_menu, parent, false)
//                ViewHolderA(view)
//            }
//            VIEW_TYPE_B -> {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_menu, parent, false)
//                ViewHolderB(view)
//            }
//            else -> throw IllegalArgumentException("Invalid view type")
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is ViewHolderA -> holder.bind(items[position] as BasicLessonModel)
//            is ViewHolderB -> holder.bind(items[position] as InterLessonModel)
//        }
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    fun setItems(newItems: List<Any>) {
//        items.clear()
//        items.addAll(newItems)
//        notifyDataSetChanged()
//    }
//
//    companion object {
//        const val VIEW_TYPE_A = 1
//        const val VIEW_TYPE_B = 2
//    }
// }
//
// class ViewHolderA(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    private val titleText: TextView = itemView.findViewById(R.id.lesson_h1)
//    private val descText: TextView = itemView.findViewById(R.id.lesson_h2)
//
//    fun bind(item: BasicLessonModel) {
//        titleText.text = item.title
//        descText.text = item.description
//    }
// }
//
// class ViewHolderB(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    private val titleText: TextView = itemView.findViewById(R.id.lesson_h1)
//    private val descText: TextView = itemView.findViewById(R.id.lesson_h2)
//
//    fun bind(item: InterLessonModel) {
//        titleText.text = item.title
//        descText.text = item.description
//    }
// }

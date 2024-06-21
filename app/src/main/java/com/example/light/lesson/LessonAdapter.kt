package com.example.light.lesson

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R

class LessonAdapter(
    lessonList: List<LessonModel>,
    context: Context
) :
//                    private val onItemClickedCallback: (LessonModel) -> Unit) :
        RecyclerView.Adapter<LessonAdapter.MyViewHolder>() {
        private var lessonList: List<LessonModel>
        private val items = mutableListOf<Any>()
        private val context: Context

//        private var drawables: Array<Drawable>
        private var onClickListener: OnClickListener? = null

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imageView: ImageView
            var titleTxt: TextView
            var descTxt: TextView

            init {
                imageView = itemView.findViewById(R.id.lesson_icon) as ImageView
                titleTxt = itemView.findViewById<View>(R.id.lesson_h1) as TextView
                descTxt = itemView.findViewById<View>(R.id.lesson_h2) as TextView
            }
        }

        init {
            this.lessonList = lessonList
            this.context = context
        }
        fun getDrawable(id: Int): Drawable {
            return ResourcesCompat.getDrawable(context.resources, id, context.theme)!!
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.lesson_menu, parent, false)

            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val lesson: LessonModel = lessonList[position]

            val lessonIcon = holder.imageView
            val lessonTitle = holder.titleTxt
            val lessonDesc = holder.descTxt

            lessonIcon.setImageResource(lesson.image)
            lessonTitle.setText(lesson.title)
            lessonDesc.setText(lesson.description)

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, lesson)
                }
            }
        }

        fun setOnClickListener(onClickListener: OnClickListener) {
            this.onClickListener = onClickListener
        }
        interface OnClickListener {
            fun onClick(position: Int, model: LessonModel)
        }

        override fun getItemCount(): Int {
            return lessonList.size
        }
    }

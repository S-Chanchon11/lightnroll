package com.example.light.expandableRecyclerview.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.light.MainActivity
import com.example.light.R
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.ui.RecyclerViewFragment


class LessonAdapter(lessonList: List<LessonModel>, context: Context) :
    RecyclerView.Adapter<LessonAdapter.MyViewHolder>() {
    private val lessonList: List<LessonModel>
    private val context: Context
    private var drawables: Array<Drawable>


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
        this.drawables = arrayOf(
            getDrawable(R.drawable.c_maj),
            getDrawable(R.drawable.d_maj),
            getDrawable(R.drawable.e_maj),
            getDrawable(R.drawable.birthday_cake_stroke_rounded)
        )
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
//        view.setOnClickListener(this)
        view.setOnClickListener {


        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lesson: LessonModel = lessonList[position]

        val lessonImage = holder.imageView
        val lessonTitle = holder.titleTxt
        val lessonDesc = holder.descTxt
        lessonImage.setImageDrawable(drawables[position])
        lessonTitle.setText(lesson.title)
        lessonDesc.setText(lesson.description)

//        holder.itemView.setOnClickListener {
//            Toast.makeText(context,position.toString(),Toast.LENGTH_SHORT).show()
//
//        }
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }
}

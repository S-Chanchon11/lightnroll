package com.example.light.tuner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LineIndicatorView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    var position: Float = 0.5f // 0.0 (left, tune up) to 1.0 (right, tune down)

    init {
        paint.color = Color.RED
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerY = height / 2
        val centerX = width / 2
        val xPosition = centerX + (width / 2 * (position - 0.5f)) // Center position is 0.5

        canvas.drawLine(xPosition, 0f, xPosition, height.toFloat(), paint)
    }

    fun updatePosition(newPosition: Float) {
        position = newPosition
        invalidate()
    }
}

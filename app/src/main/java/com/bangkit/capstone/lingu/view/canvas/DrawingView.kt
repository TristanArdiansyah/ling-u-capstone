package com.bangkit.capstone.lingu.view.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Stack

class DrawingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        style = Paint.Style.STROKE

        strokeWidth = 50f
    }

    private val path = android.graphics.Path()
    private val paths = Stack<android.graphics.Path>()
    private val undonePaths = Stack<android.graphics.Path>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (p in paths) {
            canvas.drawPath(p, paint)
        }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                paths.push(android.graphics.Path(path))
                path.reset()
            }
            else -> return false
        }

        // Indicate view should be redrawn
        invalidate()
        return true
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            undonePaths.push(paths.pop())
            invalidate()
        }
    }

    fun redo() {
        if (undonePaths.isNotEmpty()) {
            paths.push(undonePaths.pop())
            invalidate()
        }
    }

    fun clear() {
        paths.clear()
        undonePaths.clear()
        invalidate()
    }

    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }
}

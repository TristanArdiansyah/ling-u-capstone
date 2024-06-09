package com.bangkit.capstone.lingu.view.canvas

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.view.main.MainActivity

class CanvasActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        drawingView = findViewById(R.id.drawingView)

        findViewById<View>(R.id.undoButton).setOnClickListener {
            drawingView.undo()
        }

        findViewById<View>(R.id.redoButton).setOnClickListener {
            drawingView.redo()
        }

        findViewById<View>(R.id.clearButton).setOnClickListener {
            drawingView.clear()
        }

        findViewById<View>(R.id.saveButton).setOnClickListener {
            saveDrawing()
        }
    }

    private fun saveDrawing() {
        val bitmap: Bitmap = drawingView.getBitmap()
        // Here, you can save the bitmap to a file or send it to a model
        // For demonstration, we'll just show a toast
        Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // Example of sending the bitmap to a model (not implemented)
        // sendBitmapToModel(bitmap)
    }

    // Placeholder method for sending the bitmap to a model
    private fun sendBitmapToModel(bitmap: Bitmap) {
        // Implement the logic to send the bitmap to your model
    }
}

package com.bangkit.capstone.lingu.view.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.capstone.lingu.databinding.ActivityCanvasBinding
import com.bangkit.capstone.lingu.ml.Arithmetic
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CanvasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.undoButton.setOnClickListener {
            binding.drawingView.undo()
        }

        binding.redoButton.setOnClickListener {
            binding.drawingView.redo()
        }

        binding.clearButton.setOnClickListener {
            binding.drawingView.clear()
        }

        binding.saveButton.setOnClickListener {
            saveDrawing()
        }
    }

    private fun saveDrawing() {
        val bitmap: Bitmap = binding.drawingView.getBitmap()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)

        // Initialize the TensorFlow Lite model with the context
        val model = Arithmetic.newInstance(this)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        model.close()

        // Log the model output for debugging
        val outputArray = outputFeature0.floatArray
        Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")

        // Update the result TextView on the main thread
        runOnUiThread {
            binding.result.text = "Result: ${outputArray.joinToString()}"
            Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
        }

        // Navigate to MainActivity if needed
        // val intent = Intent(this, MainActivity::class.java)
        // startActivity(intent)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * 50 * 50 * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(50 * 50)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until 50) {
            for (j in 0 until 50) {
                val value = intValues[pixel++]

                // Normalize the pixel value
                byteBuffer.putFloat(((value shr 16 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value shr 8 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value and 0xFF) / 255.0f))
            }
        }
        return byteBuffer
    }
}

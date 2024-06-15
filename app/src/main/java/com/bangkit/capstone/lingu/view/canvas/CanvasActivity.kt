package com.bangkit.capstone.lingu.view.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.capstone.lingu.databinding.ActivityCanvasBinding
import com.bangkit.capstone.lingu.ml.Arithmetic
import com.bangkit.capstone.lingu.ml.Bodyparts
import com.bangkit.capstone.lingu.ml.Conversational
import com.bangkit.capstone.lingu.ml.Locations
import com.bangkit.capstone.lingu.ml.Nature
import com.bangkit.capstone.lingu.view.course.DetailCourseActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CanvasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasBinding
    companion object {
        const val EXTRA_COURSE_ID = "EXTRA_COURSE_ID"
    }

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
        val courseId = intent.getIntExtra(EXTRA_COURSE_ID, 0)
        val bitmap: Bitmap = binding.drawingView.getBitmap()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)

        if (courseId == 1){
            // Initialize the TensorFlow Lite model with the context
            val model = Locations.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex+1

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                binding.result.text = "Result: $maxIndex"
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }

        }

        if (courseId == 2){
            // Initialize the TensorFlow Lite model with the context
            val model = Bodyparts.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex+1

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                binding.result.text = "Result: $maxIndex"
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }

        }

        if (courseId == 3){
            // Initialize the TensorFlow Lite model with the context
            val model = Arithmetic.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex+1

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                binding.result.text = "Result: $maxIndex"
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }

        }

        if (courseId == 4){
            // Initialize the TensorFlow Lite model with the context
            val model = Nature.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex+1

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                binding.result.text = "Result: $maxIndex"
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }

        }

        if (courseId == 5){
            // Initialize the TensorFlow Lite model with the context
            val model = Conversational.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex+1

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                binding.result.text = "Result: $maxIndex"
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }

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

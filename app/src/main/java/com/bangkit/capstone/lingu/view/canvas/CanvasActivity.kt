package com.bangkit.capstone.lingu.view.canvas

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bangkit.capstone.lingu.data.RetrofitClient
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.progress.ProgressUpdateRequest
import com.bangkit.capstone.lingu.databinding.ActivityCanvasBinding
import com.bangkit.capstone.lingu.ml.Arithmetic
import com.bangkit.capstone.lingu.ml.Bodyparts
import com.bangkit.capstone.lingu.ml.Conversational
import com.bangkit.capstone.lingu.ml.Location
import com.bangkit.capstone.lingu.ml.Nature
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.characters.CharactersViewModel
import com.bangkit.capstone.lingu.view.characters.DetailCharactersActivity
import com.bangkit.capstone.lingu.view.course.DetailCourseActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CanvasActivity : AppCompatActivity() {

    private val viewModel by viewModels<CharactersViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityCanvasBinding
    private var dialogShown = false
    private var finalScore = 0.0f

    companion object {
        const val EXTRA_COURSE_ID = "EXTRA_COURSE_ID"
        const val EXTRA_CHARACTERS_ID = "EXTRA_CHARACTERS_ID"
        const val EXTRA_CHARACTERS_ID_ON_COURSE = "EXTRA_CHARACTERS_ID_ON_COURSE"
        const val HANZI_CHAR = "HANZI_CHAR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvHanzi.text = intent.getStringExtra(HANZI_CHAR)

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
        val charIdOnCourse = intent.getIntExtra(CanvasActivity.EXTRA_CHARACTERS_ID_ON_COURSE, 0)
        val bitmap: Bitmap = binding.drawingView.getBitmap()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, false)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)
        // saveBitmapToGallery(this@CanvasActivity, scaledBitmap, "canvas_${courseId}_${charIdOnCourse}" )
        if (courseId == 1){
            // Initialize the TensorFlow Lite model with the context
            val model = Location.newInstance(this)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 50, 50, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            model.close()

            // Get the model output as a float array
            val outputArray = outputFeature0.floatArray

            // Find the index of the highest value in the output array
            var maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            maxIndex = maxIndex

            // Log the model output and the index of the highest value for debugging
            Log.d("ModelOutput", "Output: ${outputArray.joinToString()}")
            Log.d("ModelOutput", "Index of highest value: $maxIndex")

            // Update the result TextView on the main thread
            runOnUiThread {
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }
            finalScore = outputArray[charIdOnCourse]
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
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }
            finalScore = outputArray[charIdOnCourse]

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
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }
            finalScore = outputArray[charIdOnCourse]

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
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }
            finalScore = outputArray[charIdOnCourse]

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
                Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
            }
            finalScore = outputArray[charIdOnCourse]

        }


        viewModel.getSession().observe(this) { user ->
            val token = user.token
            val charId = intent.getIntExtra(CanvasActivity.EXTRA_CHARACTERS_ID, 0)

            viewModel.getCharacterById(charId).observe(this@CanvasActivity) { characters ->
                val charactersUpdated = checkIsCharacterDone(characters, finalScore)
                if (!dialogShown) {
                    lifecycleScope.launch {
                        viewModel.update(charactersUpdated)
                        showDialog(finalScore/charactersUpdated.treshold)
                        dialogShown = true
                    }
                }

            }

            viewModel.getCharactersAndCourseById(charId).observe(this@CanvasActivity) { characters ->
                runBlocking {
                    val coursename = characters.course?.slug ?: ""
                    val hanzi = characters.characters.hanzi
                    val bestscore = characters.characters.bestScore
                    val requestBody = ProgressUpdateRequest(coursename, hanzi, bestscore.toDouble())
                    RetrofitClient.instance.predict(token, requestBody)

                    viewModel.getCharactersAndCourseById(charId).removeObservers(this@CanvasActivity)
                }
            }
        }

        dialogShown = false

    }

    private fun checkIsCharacterDone(characters: Characters, score: Float): Characters{
        // Rescale score to treshold
        if (score > characters.treshold){
            // score -> 100
            characters.bestScore = 1.0f
        } else {
            // 0.3/0.4 = 0.75f -> 75
            val scoreRescaled = score/characters.treshold

            if (scoreRescaled > characters.bestScore){
                characters.bestScore = scoreRescaled
            }
        }
        if (characters.bestScore>=0.85f){
            characters.isDone = true
        }
        return characters
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

    private fun showDialog(finalScore: Float) {
        var showScore = (finalScore*100).toInt()
        if (showScore>100)
            showScore = 100
        AlertDialog.Builder(this@CanvasActivity).apply {
            setTitle("Result")
            setMessage("${showScore}/100")
            setPositiveButton("Finish") { _, _ ->
                finish()
            }
            setNegativeButton("Try Again") { _, _ ->
                //Clear Canvas
                binding.drawingView.clear()
            }
            create()
            show()
        }
    }

}

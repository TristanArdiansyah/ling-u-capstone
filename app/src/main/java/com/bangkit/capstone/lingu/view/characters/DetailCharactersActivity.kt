package com.bangkit.capstone.lingu.view.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.databinding.ActivityDetailCharactersBinding
import com.bangkit.capstone.lingu.databinding.ActivityDetailCourseBinding
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.canvas.CanvasActivity
import com.bangkit.capstone.lingu.view.course.CharactersAdapter
import com.bangkit.capstone.lingu.view.course.CourseViewModel
import com.bangkit.capstone.lingu.view.course.DetailCourseActivity

class DetailCharactersActivity : AppCompatActivity() {
    private val viewModel by viewModels<CharactersViewModel> {
        ViewModelFactory.getInstance(this)
    }
    companion object {
        const val EXTRA_CHARACTERS_ID = "EXTRA_CHARACTERS_ID"
        const val EXTRA_COURSE_ID = "EXTRA_COURSE_ID"
    }
    private lateinit var binding: ActivityDetailCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailCharactersBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupAction()
    }
    private fun setupView(){
        val charId = intent.getIntExtra(EXTRA_CHARACTERS_ID, 0)
        viewModel.getCharacterById(charId).observe(this) { characters ->
            binding.tvHanzi.text = characters.hanzi
            binding.tvCharactersName.text = characters.pinyin
            binding.tvEnglishMeaning.text = characters.english
            binding.tvHanziExample.text = characters.example_hanzi
            binding.tvPinyinExample.text = characters.example_pinyin
            binding.tvEnglishExample.text = characters.english
        }
    }

    private fun setupAction(){
        val courseId = intent.getIntExtra(EXTRA_COURSE_ID, 0)
        var charId = intent.getIntExtra(EXTRA_CHARACTERS_ID, 0)
        viewModel.getCharacterById(charId).observe(this) { characters ->
            val charIdOnCourse = characters.idOnCourse
            binding.canvasPage.setOnClickListener {
                val intent = Intent(this, CanvasActivity::class.java)
                intent.putExtra(CanvasActivity.EXTRA_COURSE_ID, courseId)
                intent.putExtra(CanvasActivity.EXTRA_CHARACTERS_ID, charIdOnCourse)
                intent.putExtra(CanvasActivity.HANZI_CHAR, characters.hanzi)
                startActivity(intent)

            }
        }

        binding.backButtonText.setOnClickListener{
            finish()
        }

        binding.backButton.setOnClickListener{
            finish()
        }

    }

}
package com.bangkit.capstone.lingu.view.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.databinding.ItemAllCharactersBinding
import com.bangkit.capstone.lingu.databinding.ItemAllCourseBinding

class CharactersAdapter(private val courses: List<Characters>, private val onCharacterClick: (Characters) -> Unit) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemAllCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    inner class CharactersViewHolder(private val binding: ItemAllCharactersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(charactersModel: Characters) {
            binding.charactersNameTextView.text = charactersModel.pinyin
            binding.charactersDisplay.text = charactersModel.hanzi
            binding.charactersMeaningTextView.text = charactersModel.english
            binding.root.setOnClickListener() { onCharacterClick(charactersModel) }
        }
    }
}
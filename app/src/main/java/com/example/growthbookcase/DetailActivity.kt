package com.example.growthbookcase

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_BOOK = "key_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataBook = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_BOOK)
        }

        if (dataBook != null) {
            val tvImage: ImageView = findViewById(R.id.detail_image)
            val tvDetailTitle: TextView = findViewById(R.id.detail_title)
            val tvDetailDescription: TextView = findViewById(R.id.detail_description)

            tvImage.setImageResource(dataBook.image)
            tvDetailTitle.text = dataBook.name
            tvDetailDescription.text = dataBook.description
        }
    }
}
package com.example.growthbookcase

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_BOOK = "key_book"
    }

    private var book: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)

        book = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_BOOK)
        }

        if (book != null) {
            val tvImage: ImageView = findViewById(R.id.detail_image)
            val tvDetailTitle: TextView = findViewById(R.id.detail_title)
            val tvDetailDescription: TextView = findViewById(R.id.detail_description)

            book?.image?.let { tvImage.setImageResource(it) }
            tvDetailTitle.text = book?.name
            tvDetailDescription.text = book?.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            val shareText = "Book: ${book?.name}\n\nDescription: ${book?.description}"

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        }
        return super.onOptionsItemSelected(item)
    }
}
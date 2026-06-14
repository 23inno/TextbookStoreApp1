package com.innocent.textbookstoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innocent.textbookstoreapp.R
import com.innocent.textbookstoreapp.adapter.BookAdapter
import com.innocent.textbookstoreapp.model.Book

class MainActivity : AppCompatActivity() {

    private val bookList = mutableListOf(
        Book(
            "Maths 101",
            "J. Smith",
            "2nd Edition",
            "R250",
            "A mathematics textbook suitable for first-year students.",
            R.drawable.ic_launcher_background
        ),
        Book(
            "Physics 201",
            "L. Nkosi",
            "1st Edition",
            "R300",
            "A physics textbook covering basic mechanics and electricity.",
            R.drawable.ic_launcher_background
        ),
        Book(
            "IT Essentials",
            "P. Mokoena",
            "3rd Edition",
            "R200",
            "An introductory information technology textbook for students.",
            R.drawable.ic_launcher_background
        )
    )

    private val filteredList = mutableListOf<Book>()
    private lateinit var adapter: BookAdapter

    private val sellBookLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {

                val title = result.data?.getStringExtra("bookTitle")
                val author = result.data?.getStringExtra("bookAuthor")
                val edition = result.data?.getStringExtra("bookEdition")
                val price = result.data?.getStringExtra("bookPrice")
                val description = result.data?.getStringExtra("bookDescription")

                if (
                    !title.isNullOrEmpty() &&
                    !author.isNullOrEmpty() &&
                    !edition.isNullOrEmpty() &&
                    !price.isNullOrEmpty() &&
                    !description.isNullOrEmpty()
                ) {

                    val newBook = Book(
                        title,
                        author,
                        edition,
                        price,
                        description,
                        R.drawable.ic_launcher_background
                    )

                    bookList.add(newBook)
                    filteredList.add(newBook)

                    adapter.notifyItemInserted(filteredList.size - 1)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSellBook = findViewById<Button>(R.id.btnSellBook)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val recyclerBooks = findViewById<RecyclerView>(R.id.recyclerBooks)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        filteredList.addAll(bookList)

        recyclerBooks.layoutManager = LinearLayoutManager(this)
        adapter = BookAdapter(filteredList)
        recyclerBooks.adapter = adapter

        btnSellBook.setOnClickListener {
            val intent = Intent(this, SellBookActivity::class.java)
            sellBookLauncher.launch(intent)
        }

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                filterBooks(s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })
    }

    private fun filterBooks(query: String) {

        filteredList.clear()

        if (query.isEmpty()) {
            filteredList.addAll(bookList)
        } else {
            filteredList.addAll(
                bookList.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.author.contains(query, ignoreCase = true) ||
                            it.edition.contains(query, ignoreCase = true)
                }
            )
        }

        adapter.notifyDataSetChanged()
    }
}
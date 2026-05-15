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
        Book("Maths 101", "R250"),
        Book("Physics 201", "R300"),
        Book("IT Essentials", "R200")
    )

    private val filteredList = mutableListOf<Book>()
    private lateinit var adapter: BookAdapter

    private val sellBookLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {

                val title = result.data?.getStringExtra("bookTitle")
                val price = result.data?.getStringExtra("bookPrice")

                if (!title.isNullOrEmpty() && !price.isNullOrEmpty()) {

                    val newBook = Book(title, price)

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
                    it.title.contains(query, ignoreCase = true)
                }
            )
        }

        adapter.notifyDataSetChanged()
    }
}
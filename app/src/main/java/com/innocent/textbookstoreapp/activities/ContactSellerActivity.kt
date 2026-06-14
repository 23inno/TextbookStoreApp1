package com.innocent.textbookstoreapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innocent.textbookstoreapp.R

class ContactSellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_seller)

        val tvContactTitle = findViewById<TextView>(R.id.tvContactTitle)
        val etBuyerName = findViewById<EditText>(R.id.etBuyerName)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSendMessage = findViewById<Button>(R.id.btnSendMessage)

        val bookTitle = intent.getStringExtra("bookTitle")

        tvContactTitle.text = "Contact Seller: $bookTitle"

        btnSendMessage.setOnClickListener {
            val name = etBuyerName.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (name.isEmpty()) {
                etBuyerName.error = "Enter your name"
                return@setOnClickListener
            }

            if (message.isEmpty()) {
                etMessage.error = "Enter your message"
                return@setOnClickListener
            }

            Toast.makeText(this, "Message Sent Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
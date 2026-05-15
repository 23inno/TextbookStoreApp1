package com.innocent.textbookstoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innocent.textbookstoreapp.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etStudentNumber = findViewById<EditText>(R.id.etStudentNumber)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val studentNumber = etStudentNumber.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Enter your name"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                etEmail.error = "Enter your email"
                return@setOnClickListener
            }

            if (!email.contains("@")) {
                etEmail.error = "Enter a valid email"
                return@setOnClickListener
            }

            if (studentNumber.isEmpty()) {
                etStudentNumber.error = "Enter student number"
                return@setOnClickListener
            }

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
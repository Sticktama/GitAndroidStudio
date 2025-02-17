package com.example.eventmanagersystem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button_register = findViewById<Button>(R.id.button_register)

        val editTextIds = listOf(R.id.edittext_fullname, R.id.edittext_username, R.id.edittext_email, R.id.edittext_password)
        val editTexts = editTextIds.map { findViewById<EditText>(it) }

        button_register.setOnClickListener {

            val email = editTexts[2].text.toString().trim()
            val password = editTexts[3].text.toString().trim()

            /*0 - fullname
            * 1 - username
            * 2 - email
            * 3 - password
            * */
            for(i in 0..3){
                if (editTexts[i].text.toString().trim().isEmpty()) {
                    // Show errors for each empty field
                    editTexts[i].error = "Field cannot be empty"

                } else {
                    // Validate email format
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        editTexts[2].error = "Invalid email address"
                        return@setOnClickListener
                    }

                    if (password.length < 6) {
                        editTexts[3].error = "Password must be at least 6 characters"
                        return@setOnClickListener
                    }
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        val textView = findViewById<TextView>(R.id.link_login)

        textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
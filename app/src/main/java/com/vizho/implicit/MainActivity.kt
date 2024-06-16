package com.vizho.implicit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var enterYourEmail: EditText
    private lateinit var enterYourMessage: EditText
    private lateinit var btnRedirect: Button
    private lateinit var btnSendText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterYourEmail = findViewById(R.id.enterYourEmail)
        enterYourMessage = findViewById(R.id.enterYourMessage)
        btnRedirect = findViewById(R.id.btnRedirect)
        btnSendText = findViewById(R.id.btnSendText)

        btnRedirect.setOnClickListener {
            val email = enterYourEmail.text.toString()
            if (email.isNotEmpty()) {
                sendEmail(email)
            } else {
                Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show()
            }
        }

        btnSendText.setOnClickListener {
            val message = enterYourMessage.text.toString()
            if (message.isNotEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, "Subject of the email")
            putExtra(Intent.EXTRA_TEXT, "Body of the email")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.dicerollergame_20211483

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class OpenActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Find the About button view
        var aboutButton = findViewById<Button>(R.id.button2)
        aboutButton.setOnClickListener {
            // Create a new AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("About")

            // Set the dialog message
            builder.setMessage("Student ID: 20211483\nName: Mohammed Saifudeen Safa\n\n" +
                    "I confirm that I understand what plagiarism is and have read and " +
                    "understood the section on Assessment Offences in the Essential " +
                    "Information for Students. The work that I have submitted is " +
                    "entirely my own. Any work from other authors is duly referenced " +
                    "and acknowledged.")

            // Add an OK button to dismiss the dialog
            builder.setPositiveButton("OK") { dialog, which -> dialog.dismiss() }

            // Show the dialog
            builder.show()
        }





    }
}
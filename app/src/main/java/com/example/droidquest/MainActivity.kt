package com.example.droidquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, R.string.correct_toast,
                Toast.LENGTH_SHORT).show()
        })

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener{
            Toast.makeText(this, R.string.incorrect_toast,
                Toast.LENGTH_SHORT).show()
        }
    }
}

package com.example.droidquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG = "QuestActivity"
        private val KEY_INDEX = "index"
    }

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mPrevButton: ImageButton
    private lateinit var mQuestionTextView: TextView
    private val mQuestionBank = listOf(
        Question(R.string.question_android, true),
        Question(R.string.question_linear, false),
        Question(R.string.question_service, false),
        Question(R.string.question_res, true),
        Question(R.string.question_manifest, true),
        Question(R.string.new1, true),
        Question(R.string.new2, true),
        Question(R.string.new3, true),
        Question(R.string.new4, true),
        Question(R.string.new5, true)
    )
    private var mCurrentIndex = 0

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() вызван")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() вызван")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() вызван")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() вызван")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() вызван")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle) вызван")
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener(View.OnClickListener {
            checkAnswer(true)
        })

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener{
            checkAnswer(false)
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }
        mPrevButton = findViewById(R.id.prev_button)
        mPrevButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.size
            updateQuestion()
        }
        updateQuestion()

    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState!!.putInt(KEY_INDEX, mCurrentIndex)
    }

    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].textResId
        mQuestionTextView.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].answerTrue
        val messageResId = if (userPressedTrue == answerIsTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }


}

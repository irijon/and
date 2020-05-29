package com.example.droidquest

import android.content.Intent
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
        private val REQUEST_CODE_DECEIT = 0
    }

    private var mIsDeceiter = false

    override fun onActivityResult(
        requestCode: Int, resultCode:
        Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_DECEIT) {
            if (data == null) {
                return;
            }
            mQuestionBank[mCurrentIndex].wasAnswer = DeceitActivity.wasAnswerShown(result = data);
        }
    }

    private lateinit var mDeceitButton: Button
    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mPrevButton: ImageButton
    private lateinit var mQuestionTextView: TextView
    private val mQuestionBank = listOf(
        Question(R.string.question_android, true,false),
        Question(R.string.question_linear, false,false),
        Question(R.string.question_service, false,false),
        Question(R.string.question_res, true,false),
        Question(R.string.question_manifest, true,false),
        Question(R.string.new1, true,false),
        Question(R.string.new2, true,false),
        Question(R.string.new3, true,false),
        Question(R.string.new4, true,false),
        Question(R.string.new5, true,false)
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

        mDeceitButton = findViewById(R.id.deceit_button)
        mDeceitButton.setOnClickListener{
            val answerIsTrue = mQuestionBank[mCurrentIndex].answerTrue
            val intent = DeceitActivity.newIntent(this,
                answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_DECEIT)
        }

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener{
            checkAnswer(false)
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            mIsDeceiter = false
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
            val messageResId = if (mQuestionBank[mCurrentIndex].wasAnswer==true) { R.string.judgment_toast }
            else if (userPressedTrue == answerIsTrue) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }


}

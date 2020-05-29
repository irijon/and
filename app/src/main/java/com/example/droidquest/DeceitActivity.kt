package com.example.droidquest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

public class DeceitActivity : AppCompatActivity() {
    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswer: Button

    companion object {
        public fun newIntent(packageContext: Context?, answerIsTrue: Boolean):
                Intent? {
            val intent = Intent(packageContext,
                DeceitActivity::class.java)
            return intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
        }
        fun wasAnswerShown(result: Intent) =
            result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        val EXTRA_ANSWER_IS_TRUE =
            "com.example.droidquest.answer_is_true"
        val EXTRA_ANSWER_SHOWN =
            "com.example.droidquest.answer_shown"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deceit)
        val mAnswerIsTrue = getIntent().getBooleanExtra(
            EXTRA_ANSWER_IS_TRUE, false)
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener {
            mAnswerTextView.setText(
                if (mAnswerIsTrue) R.string.true_button
                else R.string.false_button)
            setAnswerShownResult(true)
        }

    }
    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }
}

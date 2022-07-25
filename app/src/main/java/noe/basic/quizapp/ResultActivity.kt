package noe.basic.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import noe.basic.model.Constants

class ResultActivity : AppCompatActivity() {
    private lateinit var userNameTextView: TextView
    private lateinit var scoreRelatedTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        this.userNameTextView = findViewById(R.id.textView_username)
        this.scoreRelatedTextView = findViewById(R.id.textView_score)
        val userName = intent.getStringExtra(Constants.USER_NAME)
        val totalQ = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val correctAnswer = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        this.userNameTextView.text = userName
        this.scoreRelatedTextView.text = "Your score is $correctAnswer out of $totalQ"
    }
}
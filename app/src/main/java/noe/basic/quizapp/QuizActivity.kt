package noe.basic.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import noe.basic.model.Question
import noe.basic.model.QuestionArchive

class QuizActivity : AppCompatActivity() {
    private lateinit var questions :ArrayList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questions = QuestionArchive.getQuestions(this)
        Log.i("Preguntas",questions.toString())

    }
}
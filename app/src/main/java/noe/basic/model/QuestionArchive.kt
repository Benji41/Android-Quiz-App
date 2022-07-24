package noe.basic.model

import android.provider.Settings.Global.getString
import noe.basic.quizapp.QuizActivity
import noe.basic.quizapp.R

object QuestionArchive {
    fun getQuestions (context : QuizActivity) : ArrayList<Question>{
        val questions = ArrayList<Question>()
        questions.add(
            Question(0,
                context.getString(R.string.question),
                R.drawable.ic_flag_of_australia,
                "Mexico",
                "Uruguay",
                "Australia",
                "Australia")
        )
        questions.add(
            Question(1,
                context.getString(R.string.question),
                R.drawable.ic_flag_of_argentina,
                "Urugay",
                "Argenia",
                "Argentina",
                "Argentina")
        )
        questions.add(
            Question(2,
                context.getString(R.string.question),
                R.drawable.ic_flag_of_belgium,
                "Belgica",
                "Alemania",
                "Francia",
                "Belgica")
        )
        questions.add(
            Question(3,
                context.getString(R.string.question),
                R.drawable.ic_flag_of_brazil,
                "Brazil",
                "Estados unidos",
                "Polonia",
                "Brazil")
        )
        return questions
    }
}
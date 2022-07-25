package noe.basic.quizapp

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import noe.basic.model.Question
import noe.basic.model.Constants
import noe.basic.quizapp.R.color.custom_purple

class QuizActivity : AppCompatActivity() {
    private lateinit var questions :ArrayList<Question>
    private lateinit var pg_progress : ProgressBar
    private lateinit var txt_opt1 : TextView
    private lateinit var txt_opt2 : TextView
    private lateinit var txt_opt3 : TextView
    private lateinit var txt_progress : TextView
    private lateinit var  btn_submit_answer : Button
    private lateinit var  img_flag : ImageView
    private lateinit var txt_question : TextView
    private var quizProgress: Int = 0
    private lateinit var currentQuestion : Question
    private lateinit var answer : String
    private var optionPressed : Int? =0
    private var rightAnswers : Int =0
    private lateinit var userName :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        userName = intent.getStringExtra(Constants.USER_NAME)!!
        questions = Constants.getQuestions(this)
        Log.i("Preguntas",questions.toString())
        pg_progress = findViewById(R.id.progressBar)
        txt_opt1 = findViewById(R.id.textView_option1)
        txt_opt2 = findViewById(R.id.textView_option2)
        txt_opt3 = findViewById(R.id.textView_option3)
        btn_submit_answer = findViewById(R.id.button_submit_name)
        img_flag = findViewById(R.id.imageView_country)
        txt_progress = findViewById(R.id.textView_progress)
        quizProgress = 0
        txt_question = findViewById(R.id.textView_question)
        txt_progress.text="$quizProgress / ${questions.size}"
        pg_progress.max = questions.size
        pg_progress.progress = 0
        currentQuestion = questions[0]
        setQuestionView(currentQuestion)
        btn_submit_answer.setOnClickListener {
            if(btn_submit_answer.text == getString(R.string.change_question)){
                btn_submit_answer.text = getString(R.string.submit_answer)
                setQuestionView(currentQuestion)
                optionPressed = null
                answer=""
                txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
            }else{
                if(quizProgress < questions.size-1) {
                    if (answer == currentQuestion.answer) {
                        rightAnswers++
                        currentQuestion = questions[++quizProgress]
                        pg_progress.progress = quizProgress
                        txt_progress.text = "$quizProgress / ${questions.size}"
                        btn_submit_answer.text = getString(R.string.change_question)
                        when(optionPressed){
                            0-> txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                            1-> txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                            2-> txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                        }
                    }else{
                        Toast.makeText(this,"Wrong answer my dude, bet next time you'll be right",Toast.LENGTH_SHORT).show()
                        when(optionPressed){
                            0->{
                                txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_wrong_option_border_bg,null)
                                txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                                txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                            }
                            1-> {
                            txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                            txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_wrong_option_border_bg,null)
                            txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                            }
                            2->{
                            txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                            txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
                            txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_wrong_option_border_bg,null)
                            }
                        }
                        if(quizProgress < questions.size-1) {
                            currentQuestion = questions[++quizProgress]
                            pg_progress.progress = quizProgress
                            txt_progress.text = "$quizProgress / ${questions.size}"
                            btn_submit_answer.text = getString(R.string.change_question)
                        }
                    }
                }else{
                    pg_progress.progress = questions.size
                    txt_progress.text="${questions.size} / ${questions.size}"
                    Toast.makeText(this,"Ez PZZZ well done my duder!",Toast.LENGTH_SHORT).show()
                    when(optionPressed){
                        0-> txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                        1-> txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                        2-> txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_right_option_border_bg,null)
                    }
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME,userName)
                    intent.putExtra(Constants.TOTAL_QUESTIONS,questions.size.toString())
                    intent.putExtra(Constants.CORRECT_ANSWERS,rightAnswers.toString())
                    startActivity(intent)
                    finish()
                }
            }
        }
        val progressDrawable: Drawable = pg_progress.progressDrawable.mutate()
        progressDrawable.setColorFilter(super.getResources().getColor(custom_purple), PorterDuff.Mode.SRC_IN)
        pg_progress.progressDrawable = progressDrawable
    }
   private fun setQuestionView(q:Question){
        txt_question.text = q.question
        txt_opt1.text = q.option1
        txt_opt2.text = q.option2
        txt_opt3.text = q.option3
        img_flag.setImageResource(q.image)
       txt_opt1.setOnClickListener {
           if(btn_submit_answer.text != getString(R.string.change_question)){
               txt_opt1.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.selected_option_border_bg,null)
               txt_opt2.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
               txt_opt3.background = ResourcesCompat.getDrawable(super.getResources(),R.drawable.option_border_bg,null)
               answer = txt_opt1.text as String
               optionPressed =0
           }

       }
       txt_opt2.setOnClickListener {
           if(btn_submit_answer.text != getString(R.string.change_question)) {
               txt_opt2.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.selected_option_border_bg,
                   null
               )
               txt_opt1.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.option_border_bg,
                   null
               )
               txt_opt3.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.option_border_bg,
                   null
               )
               answer = txt_opt2.text as String
               optionPressed = 1
           }
       }
       txt_opt3.setOnClickListener {
           if(btn_submit_answer.text != getString(R.string.change_question)) {
               txt_opt3.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.selected_option_border_bg,
                   null
               )
               txt_opt1.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.option_border_bg,
                   null
               )
               txt_opt2.background = ResourcesCompat.getDrawable(
                   super.getResources(),
                   R.drawable.option_border_bg,
                   null
               )
               answer = txt_opt3.text as String
               optionPressed = 2
           }
       }
    }
}
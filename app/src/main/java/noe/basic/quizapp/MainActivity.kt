package noe.basic.quizapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editVName = findViewById<EditText>(R.id.edittext_name)
        //ALLOW THAT THE FIRST Letter IS CAP
        editVName.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        val btnVRegisterName = findViewById<Button>(R.id.button_submit_name)
        btnVRegisterName.setOnClickListener {
            //Iterate throw a lambda expression with .all all the characters of a string validating if it is a letter
            if(editVName.text.isNotEmpty() && editVName.text.all{
                it.isLetter()
                }){
                val intent = Intent(this,QuizActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
package noe.basic.model

data class Question(
    val id: Int,
    val question: String,
    val image: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val answer: String
)

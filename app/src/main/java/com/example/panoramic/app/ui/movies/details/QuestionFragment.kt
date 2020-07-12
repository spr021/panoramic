package com.example.panoramic.app.ui.movies.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.data.entity.QuestionEntity
import com.example.panoramic.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(R.layout.fragment_question) {
    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val questions: MutableList<QuestionEntity> = mutableListOf(
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴")),
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴")),
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴")),
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴")),
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴")),
        QuestionEntity(text = "تفاوت HD و UHD چیست",
            answers = listOf("۱", "۲", "۳", "۴"))
    )


    private var fragmentQuestionBinding: FragmentQuestionBinding? = null
    lateinit var currentQuestion: QuestionEntity
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentQuestionBinding.bind(view)
        fragmentQuestionBinding = binding

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.question = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener{
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        CustomToast(requireActivity(), "همه ی سوال ها را درست جواب دادید. امتیاز ویدو برای شما ثبت گردید", R.color.green)
                        view.findNavController().navigate(R.id.action_questionFragment_to_moviesFragment)
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    CustomToast(requireActivity(), "تعداد خطا های پاسخگویی شما بیش از حد مجاز بود. لطفا مجددا اقدام به مشاهده فیلم و پاسخگویی سوالات نمایید", R.color.red)
                    view.findNavController().navigate(R.id.action_questionFragment_to_moviesFragment)
                }
            }
        }
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentQuestionBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }
}
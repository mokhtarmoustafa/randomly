package com.twoam.randomly

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.twoam.randomly.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var randomNumber: Int = 0
    private var attemptsLeft: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.instructionsTextView.text = "Guess the number between 1 and 10 in 3 attempts!"

        randomNumber = Random.nextInt(1, 11)

        binding.guessButton.setOnClickListener {
            handleGuess()
        }
    }

    private fun handleGuess() {
        val enteredNumber = binding.guessEditText.text.toString().toIntOrNull()
        if (enteredNumber != null) {
            if (enteredNumber == randomNumber) {
                showMessage("Congratulations! You guessed it right!")
                disableGuessing()
            } else {
                attemptsLeft--
                if (attemptsLeft > 0) {
                    showMessage("Wrong guess! Try again. Attempts left: $attemptsLeft")
                } else {
                    showMessage("Out of attempts! The correct number was: $randomNumber")
                    disableGuessing()
                }
            }
        } else {
            showMessage("Please enter a valid number.")
        }
        binding.guessEditText.text.clear() // Clear the EditText after each guess
    }

    private fun showMessage(message: String) {
        binding.messageTextView.text = message
    }

    private fun disableGuessing() {
        binding.guessButton.isEnabled = false
        binding.guessEditText.isEnabled = false
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.guessEditText.windowToken, 0)
    }
}
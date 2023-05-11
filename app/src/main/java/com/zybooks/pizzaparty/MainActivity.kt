package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.math.ceil

/**
 * Main activity
 *
 * This class calculates the number of slices for
 * each person.
 * @author Fahri Yildiz
 */
class MainActivity : ComponentActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * Creates the view
     * @property savedInstanceState is the instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Stores the Edit Text in a variable
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        // Stores the Text View in a variable
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        // Stores the Radio Group in a variable
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

    }

    /**
     * This function is activated when the user clicks on
     * the "calculate" button
     * @property view the actual view
     */
    fun calculateClick(view: View) {
        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.setText(totalText)
    }
}


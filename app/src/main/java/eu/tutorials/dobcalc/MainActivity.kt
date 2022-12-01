package eu.tutorials.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        btnDatePicker.setOnClickListener{
            onClickDatePicker()
        }
    }

    fun onClickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, selectedYear, selectedMonth, dayOfMonth ->
            Toast.makeText(this, "Year was $selectedYear, " +
                    "month was ${selectedMonth +1}, day was $dayOfMonth", Toast.LENGTH_LONG).show()

                       //val selectedDate = "$dayOfMonth/"
                               }
            ,year
            ,month
            ,day).show()

    }
}
package eu.tutorials.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener{
            onClickDatePicker()
        }
    }

    private fun onClickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, dayOfMonth ->
            Toast.makeText(this, "Year was $selectedYear, " +
                    "month was ${selectedMonth +1}, day was $dayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            theDate?.let{
                //this checks how much time has passed since selected date
                val selectedDateInMinutes = theDate.time / 60000

                //this gets how much time has passed from the date chosen to now
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let{
                    val currentDateInMinutes = currentDate.time / 60000

                    val differenceInTime = currentDateInMinutes - selectedDateInMinutes
                    tvAgeInMinutes?.text = differenceInTime.toString()
                }
            }
        }
            ,year
            ,month
            ,day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}
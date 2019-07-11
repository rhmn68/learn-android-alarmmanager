package coffeecode.co.learnalarmmanager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private var alarmReceiver: AlarmReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmReceiver = AlarmReceiver()
        onClick()
    }

    private fun onClick() {
        btnOnceDate.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
        }

        btnOnceTime.setOnClickListener {
            val timePickerFragment = TimePickerFragment()
            timePickerFragment.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
        }

        btnSetOnceAlarm.setOnClickListener {
            val onceDate = tvOnceDate.text.toString()
            val onceTime = tvOnceTime.text.toString()
            val onceMessage = edtOnceMessage.text.toString()

            alarmReceiver?.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    onceDate,
                    onceTime,
                    onceMessage)
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        tvOnceDate.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            TIME_PICKER_ONCE_TAG -> tvOnceTime.text = dateFormat.format(calendar.time)
            else -> {
            }
        }
    }

    companion object{
       const val DATE_PICKER_TAG = "DatePicker"
       const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
       const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }
}

package qau.campos.timelogger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Date;

import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.utils.DateFormatHelper;
import qau.campos.timelogger.utils.NumberPickerType;
import qau.campos.timelogger.utils.NumericDate;

public class LoggerView extends AppCompatActivity{

    NumberPicker hoursPicker;
    NumberPicker minutesPicker;
    Button dateButton;
    Button addTimeButton;
    NumericDate selectedDate;
    int hours, minutes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger_view);

        initPicker(NumberPickerType.HOURS,hoursPicker,R.id.hoursPicker, 0,24);
        initPicker(NumberPickerType.MINUTES,minutesPicker,R.id.minutesPicker, 0,60);

        dateButton = findViewById(R.id.dateButton);
        addTimeButton = findViewById(R.id.addTimeButton);

        NumericDate selectedDate = DateFormatHelper.getNumericDate();
        dateButton.setText(selectedDate.getDay() + "." + selectedDate.getMonth()+1 + "." +selectedDate.getYear());
    }

    private void initPicker(NumberPickerType type, NumberPicker picker, int pickerId, int min, int max){
        picker = findViewById(pickerId);
        picker.setMinValue(min);
        picker.setMaxValue(max);

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(type == NumberPickerType.HOURS){
                    hours = newVal;
                    return;
                }
                minutes = newVal;
            }
        });
    }

    public void showDatePickerDialog(View v) {
       DialogFragment newFragment = new DatePickerFragment();
       newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onDataSet (NumericDate date){
        selectedDate = date;
    }
    public void addTime(View v){
        int totalTimeInMinutes = (hours * 60) + minutes;
        Minutes loggedTime = new Minutes("cuauhtli", selectedDate,totalTimeInMinutes);
        Toast toast = Toast.makeText(getApplicationContext(), totalTimeInMinutes+" logged minutes", Toast.LENGTH_LONG);
        toast.show();
    }

}

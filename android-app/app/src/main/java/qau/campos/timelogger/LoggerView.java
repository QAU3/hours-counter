package qau.campos.timelogger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import qau.campos.timelogger.models.AggregatedTime;
import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.models.NumericDate;
import qau.campos.timelogger.utils.DateFormatHelper;
import qau.campos.timelogger.utils.NumberPickerType;
import qau.campos.timelogger.utils.Utils;

public class LoggerView extends AppCompatActivity {

    NumberPicker hoursPicker;
    NumberPicker minutesPicker;
    Button dateButton;
    Button addTimeButton;
    String URL;
    NumericDate selectedDate = DateFormatHelper.getNumericDate();
    int hours=0;
    int minutes=0;
    TextView[] timeViews;
    TextView[] timeHeadersViews;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger_view);

        initPicker(NumberPickerType.HOURS,hoursPicker,R.id.hoursPicker, 0,24);
        initPicker(NumberPickerType.MINUTES,minutesPicker,R.id.minutesPicker, 0,60);

        dateButton = findViewById(R.id.dateButton);
        addTimeButton = findViewById(R.id.addTimeButton);
        dateButton.setText(selectedDate.toString());
        URL = getString(R.string.host) + getString(R.string.api_logger);

        timeViews = new TextView[] {findViewById(R.id.minutesYear), findViewById(R.id.minutesMonth), findViewById(R.id.minutesWeek)};
        timeHeadersViews = new TextView[] {findViewById(R.id.yearHeader), findViewById(R.id.monthHeader), findViewById(R.id.weekHeader)};

        Utils.getData(this, URL);
}

    public void showDatePickerDialog(View v) {
       DialogFragment newFragment = new DatePickerFragment();
       newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onDataSet (@NonNull NumericDate date){
        selectedDate = date;
        dateButton.setText(date.toString());
    }

    public void  onGetResponse(AggregatedTime[] responses){
        for (int i =0; i < responses.length; i++){
            timeViews[i].setText(responses[i].getMinutes()+"");
        }
    }

    public void onPostedData(){
        Utils.getData(this, URL);
    }

    public void addTime(View v){
        int totalTimeInMinutes = (hours * 60) + minutes;

        Minutes loggedTime = new Minutes("cuauhtli",
                DateFormatHelper.getTimeStampFromNumericDate(selectedDate),
                totalTimeInMinutes);
        Utils.postData(this, URL, loggedTime);
    }

    private void initPicker(NumberPickerType type, NumberPicker picker, int pickerId, int min, int max){
        picker = findViewById(pickerId);
        picker.setMinValue(min);
        picker.setMaxValue(max);

        picker.setOnValueChangedListener((picker1, oldVal, newVal) -> {
            if(type == NumberPickerType.HOURS){
                hours = newVal;
                return;
            }
            minutes = newVal;
        });
    }
}

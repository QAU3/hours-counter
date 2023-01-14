package qau.campos.timelogger;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import qau.campos.timelogger.utils.TimeLogger;
import qau.campos.timelogger.utils.Utils;

public class LoggerView extends AppCompatActivity {

    NumericDate selectedDate = DateFormatHelper.getNumericDate();
    NumberPicker hoursPicker;
    NumberPicker minutesPicker;
    Button dateButton;
    String URL;
    int hours=0;
    int minutes=0;
    TextView[] timeViews;
    TextView[] timeHeadersViews;
    TimeLogger timeLogger;
    String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger_view);

        hoursPicker = initPicker(NumberPickerType.HOURS,R.id.hoursPicker, 0,24);
        minutesPicker= initPicker(NumberPickerType.MINUTES,R.id.minutesPicker, 0,59);

        dateButton = findViewById(R.id.dateButton);
        dateButton.setText(selectedDate.toString());

        URL = getString(R.string.host) + getString(R.string.api_logger);

        timeViews = new TextView[] {findViewById(R.id.minutesYear), findViewById(R.id.minutesMonth), findViewById(R.id.minutesWeek)};
        timeHeadersViews = new TextView[] {findViewById(R.id.yearHeader), findViewById(R.id.monthHeader), findViewById(R.id.weekHeader)};

        timeLogger = new TimeLogger(this);
        timeLogger.startTimer();

        username =  getIntent().getStringExtra("email");
        Utils.getData(this, URL + "/" +username);

    }

    @Override
    protected void onResume() {
        super.onResume();
        timeLogger.startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeLogger.pauseTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeLogger.pauseTimer();
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
            timeViews[i].setText(responses[i].getMinutes()/60+"");
        }
    }

    public void onPostedData(){
        Utils.getData(this, URL + "/" +username);
    }

    public void onTick(int hours, int minutes){
        hoursPicker.setValue(hours);
        minutesPicker.setValue(minutes);
    }

    public  void enterManualTime(View v){
        timeLogger.pauseTimer();
    }

    public void addTime(View v){
        int totalTimeInMinutes = (hours * 60) + minutes;
        Minutes loggedTime = new Minutes(username,
                DateFormatHelper.getTimeStampFromNumericDate(selectedDate),
                totalTimeInMinutes);
        Utils.postData(this, URL, loggedTime);
    }

    private NumberPicker initPicker(NumberPickerType type, int pickerId, int min, int max){
        NumberPicker picker = findViewById(pickerId);
        picker.setMinValue(min);
        picker.setMaxValue(max);

        picker.setOnValueChangedListener((picker1, oldVal, newVal) -> {
            if(type == NumberPickerType.HOURS){
                hours = newVal;
                return;
            }
            minutes = newVal;
        });
        return picker;
    }
}

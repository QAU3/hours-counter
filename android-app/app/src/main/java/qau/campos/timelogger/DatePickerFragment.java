package qau.campos.timelogger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.logging.Logger;

import qau.campos.timelogger.utils.DateFormatHelper;
import qau.campos.timelogger.utils.NumericDate;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        NumericDate date = DateFormatHelper.getNumericDate();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(
                requireContext(),
                this,
                date.getYear(),
                date.getMonth(),
                date.getDay());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        NumericDate date = new NumericDate(year, month, day);
        ((LoggerView) getActivity()).onDataSet(date);
    }
}

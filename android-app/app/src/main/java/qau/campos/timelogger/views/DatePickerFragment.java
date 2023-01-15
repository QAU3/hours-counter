package qau.campos.timelogger.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import qau.campos.timelogger.utils.DateFormatHelper;
import qau.campos.timelogger.models.NumericDate;
import qau.campos.timelogger.views.LoggerView;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        NumericDate date = DateFormatHelper.getNumericDate();
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

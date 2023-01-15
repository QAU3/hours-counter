package qau.campos.timelogger.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import qau.campos.timelogger.R;
import qau.campos.timelogger.interfaces.IComfirmationHandler;
import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.utils.DateFormatHelper;
import qau.campos.timelogger.utils.Utils;
import qau.campos.timelogger.views.SingleEntryView;

public class MinutesListViewAdapter extends ArrayAdapter<Minutes> {
    ArrayList<Minutes> minutesArrayList;
    Context context;

    public MinutesListViewAdapter(Context context, ArrayList<Minutes> minutesArrayList){
        super(context, R.layout.single_entry_view, minutesArrayList);
        this.minutesArrayList = minutesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.single_item, null, true);
        holder = new ViewHolder();
        //getting text views
        holder.entryTitle = convertView.findViewById(R.id.entryTitle);
        holder.minutesValue = convertView.findViewById(R.id.minutesValues);
        holder.deleteButton = convertView.findViewById(R.id.deleteButtton);

        convertView.setTag(holder);

        Minutes minutes = minutesArrayList.get(position);
        String title = "Entry: " + minutes.getDate();
        String time = minutes.getMinutes()/60 + "";

        holder.entryTitle.setText(title);
        holder.minutesValue.setText(time);

        holder.deleteButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure you want to delete this entry?");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                Utils.showMessage(context, "Entry deleted");
                IComfirmationHandler customActivity = (IComfirmationHandler) context;
                customActivity.onDeleteRequest(minutes.getId());
                    });

            builder.setNegativeButton(android.R.string.no, null).show();
        });

        return convertView;
    }

    static class ViewHolder {
        TextView entryTitle;
        TextView minutesValue;
        Button deleteButton;
    }

}

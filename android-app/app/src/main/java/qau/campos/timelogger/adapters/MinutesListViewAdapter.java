package qau.campos.timelogger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import qau.campos.timelogger.R;
import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.utils.DateFormatHelper;

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

        convertView.setTag(holder);

        Minutes minutes = minutesArrayList.get(position);
        String title = "Entry: " + minutes.getDate();
        String time = minutes.getMinutes() + "";

        holder.entryTitle.setText(title);
        holder.minutesValue.setText(time);

        return convertView;
    }

    static class ViewHolder {
        TextView entryTitle;
        TextView minutesValue;

    }

}

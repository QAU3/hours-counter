package qau.campos.timelogger.views;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import qau.campos.timelogger.R;
import qau.campos.timelogger.adapters.MinutesListViewAdapter;
import qau.campos.timelogger.interfaces.IResponseHandler;
import qau.campos.timelogger.models.AggregatedTime;
import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.utils.ServerRequest;


public class SingleEntryView extends AppCompatActivity implements IResponseHandler {
    ListView entriesView;
    String username;
    String URL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_entry_view);
        entriesView = (ListView) findViewById(R.id.minutesListView);
        ServerRequest serverRequest = new ServerRequest(this);
        username =  getIntent().getStringExtra("email");
        URL = getString(R.string.host) + getString(R.string.api_logger) + "/all/"+ username;

        serverRequest.getAllUserMinutes(URL);
    }


    @Override
    public void onGetAggregatedTimeResponse(AggregatedTime[] responses) {

    }

    @Override
    public void onGetAllUserMinutesResponse(ArrayList<Minutes> response) {
        MinutesListViewAdapter adapter = new MinutesListViewAdapter(this, response);
        entriesView.setAdapter(adapter);
    }


    @Override
    public void onPostedData() {

    }
}


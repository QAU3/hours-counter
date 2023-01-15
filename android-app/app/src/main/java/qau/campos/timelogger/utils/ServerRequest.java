package qau.campos.timelogger.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import qau.campos.timelogger.interfaces.IResponseHandler;
import qau.campos.timelogger.models.AggregatedTime;
import qau.campos.timelogger.models.Minutes;

public class ServerRequest {
    Context context;
    RequestQueue volleyQueue;



    public ServerRequest(Context context){
        this.context = context;
        volleyQueue = Volley.newRequestQueue(context);
    }

    public void getAllUserMinutes(String url){
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                res -> {
                    try {
                        ArrayList<Minutes> minutesArrayList = new ArrayList<>();
                        for (int i = 0; i < res.length(); i++){
                            JSONObject minutesObject = res.getJSONObject(i);
                            Minutes minutes = new Minutes(
                                    minutesObject.getString("_id"),
                                    minutesObject.getString("username"),
                                    minutesObject.getString("date"),
                                    minutesObject.getInt("minutes"));
                            minutesArrayList.add(minutes);
                        }

                        IResponseHandler loggerView = (IResponseHandler) context;
                        loggerView.onGetAllUserMinutesResponse(minutesArrayList);
                        Log.d("volley","parsing error?");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context,
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }, error -> Log.e("VOLLEY ERROR", error.toString()));
        volleyQueue.add(jsonObjectRequest);
    }

    public void getAggregatedData(String url){
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                res -> {
                    try {
                         JSONObject obj = res.getJSONObject(0);
                         JSONObject y = obj.getJSONObject("year");
                         JSONObject m = obj.getJSONObject("month");
                         JSONObject w = obj.getJSONObject("week");

                         AggregatedTime year = new AggregatedTime(
                                 y.getInt("_id"),
                                 y.getInt("minutes"));

                         AggregatedTime month = new AggregatedTime(
                                m.getInt("_id"),
                                m.getInt("minutes"));

                         AggregatedTime week = new AggregatedTime(
                                w.getInt("_id"),
                                w.getInt("minutes"));

                        AggregatedTime[] responses =  new AggregatedTime[]{year,month,week};

                        IResponseHandler loggerView = (IResponseHandler) context;
                        loggerView.onGetAggregatedTimeResponse(responses);

                        Log.d("volley", year.getMinutes()+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context,
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }, error -> Log.e("VOLLEY ERROR", error.toString()));
        volleyQueue.add(jsonObjectRequest);
    }

    public void postData(String url, Minutes minutes){
        JSONObject postData = new JSONObject();
        try{
            postData.put("username", minutes.getUsername());
            postData.put("minutes", minutes.getMinutes());
            postData.put("date", minutes.getDate());
        }catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                res -> {
                    try {
                        Utils.showMessage(context,"Response:" + res.getString("response"));
                        IResponseHandler loggerView = (IResponseHandler) context;
                        loggerView.onPostedData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Utils.showMessage(context, "Error: " + e.getMessage());
                    }
                }, error -> Utils.showMessage(context, "Volley error: " + error.getMessage()));

        volleyQueue.add(jsonObjectRequest);
    }
}

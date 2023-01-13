package qau.campos.timelogger.utils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import qau.campos.timelogger.LoggerView;
import qau.campos.timelogger.models.AggregatedTime;
import qau.campos.timelogger.models.Minutes;
import qau.campos.timelogger.models.NumericDate;

public class Utils {
    public static void showMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void getData(Context ctx, String url){
        RequestQueue volleyQueue = Volley.newRequestQueue(ctx);

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
                        LoggerView loggerView = (LoggerView) ctx;
                        loggerView.onGetResponse(responses);
                        Log.d("volley", year.getMinutes()+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ctx,
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }, error -> Log.e("VOLLEY ERROR", error.toString()));
        volleyQueue.add(jsonObjectRequest);
    }

    public static void postData(Context ctx, String url, Minutes minutes){
        RequestQueue volleyQueue = Volley.newRequestQueue(ctx);

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
                        Utils.showMessage(ctx,"Response:" + res.getString("response"));
                        LoggerView loggerView = (LoggerView) ctx;
                        loggerView.onPostedData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Utils.showMessage(ctx, "Error: " + e.getMessage());
                    }
                }, error -> Utils.showMessage(ctx, "Volley error: " + error.getMessage()));

        volleyQueue.add(jsonObjectRequest);
    }
}

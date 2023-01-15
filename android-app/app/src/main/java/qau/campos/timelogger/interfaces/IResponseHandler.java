package qau.campos.timelogger.interfaces;

import java.util.ArrayList;

import qau.campos.timelogger.models.AggregatedTime;
import qau.campos.timelogger.models.Minutes;

public interface IResponseHandler {
     void onGetAggregatedTimeResponse(AggregatedTime[] responses);
     void onGetAllUserMinutesResponse(ArrayList<Minutes> response);
     void onPostedData();
     void onDeletedData();
}

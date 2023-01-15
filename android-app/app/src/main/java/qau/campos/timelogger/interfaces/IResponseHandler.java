package qau.campos.timelogger.interfaces;

import qau.campos.timelogger.models.AggregatedTime;

public interface IResponseHandler {
     void  onGetResponse(AggregatedTime[] responses);
     void onPostedData();
}

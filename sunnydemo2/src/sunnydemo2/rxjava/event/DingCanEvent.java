package sunnydemo2.rxjava.event;

/**
 * Created by sunny on 2015/12/30.
 * 用Event来进行数据的传递
 */
public class DingCanEvent extends BaseEvent{
    public Object mObject;
    public ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public DingCanEvent() {
    }

    public DingCanEvent(Object mObject) {
        this.mObject = mObject;
    }

    public Object getmObject() {
        return mObject;
    }

    public void setmObject(Object mObject) {
        this.mObject = mObject;
    }
}

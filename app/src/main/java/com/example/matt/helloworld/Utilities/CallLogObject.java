package com.example.matt.helloworld.Utilities;

import android.provider.CallLog;

import java.util.Date;

public class CallLogObject {

    private String callNumber;
    private Date callDate;
    private String callDuration;
    private int callType;

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }


    public void CallLogObject() {}
}

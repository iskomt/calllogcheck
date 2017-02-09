package com.example.matt.helloworld.Utilities;

public class CallTypeHelper {

    public static String getCallType(int callType) {
        String callTypeToReturn;
        switch(callType) {
            case 1:
                callTypeToReturn = "Incoming";
                break;
            case 2:
                callTypeToReturn = "Outgoing";
                break;
            case 3:
                callTypeToReturn = "Missed";
                break;
            default:
                callTypeToReturn = "";
                break;
        }

        return callTypeToReturn;
    }

}

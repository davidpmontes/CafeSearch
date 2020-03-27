package com.example.myapplication;

import org.json.JSONArray;

// https://stackoverflow.com/questions/23311629/how-send-message-from-fragment-to-activity-and-received-and-use-in-activity
public interface CommunicationInterface {
    public void onResultsArray(JSONArray resultsArray);
    public void clearAll();
}

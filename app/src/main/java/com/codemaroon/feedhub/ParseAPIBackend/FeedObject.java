package com.codemaroon.feedhub.ParseAPIBackend;

import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

public class FeedObject extends ParseObject {

    public FeedObject() {}

    public String getTime(){
        return getString("time");
    }
    public boolean isComplete(){
        return getBoolean("complete");
    }
    public String getVenue(){
        return getString("venue");
    }
    public String getUserId(){
        return getString("userId");
    }
    public void setTime(String time) {
        put("time", time);
    }
    public void setVenue(String venue){
        put("venue", venue);
    }
    public void setUserId(String userId){
        put("userId", userId);
    }
    public void setComplete(Boolean complete){
        put("complete", complete);
    }

}

package com.codemaroon.feedhub.ParseAPIBackend;


public class FeedModel {
    String email;
    String team;
    String venue;

    public FeedModel(String email, String team, String venue) {
        this.email = email;
        this.team = team;
        this.venue = venue;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTeam() {
        return this.team;
    }

    public String getVenue() {
        return this.venue;
    }

}

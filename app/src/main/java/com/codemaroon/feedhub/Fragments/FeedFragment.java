package com.codemaroon.feedhub.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.codemaroon.feedhub.Adapters.AdapterRecentFeedsList;
import com.codemaroon.feedhub.ParseAPIBackend.FeedModel;
import com.codemaroon.feedhub.ParseAPIBackend.FeedObject;
import com.codemaroon.feedhub.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FeedFragment extends Fragment {

    List<FeedModel> feeds = new ArrayList<>();
    RecyclerView rv;
    AdapterRecentFeedsList mAdapter;
    Button refresh;

    //String emailOfCurrentFeedCardUser = "";
    String currentFeedCardUserId = "";

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        refresh = (Button) v.findViewById(R.id.button2);
        rv = (RecyclerView) v.findViewById(R.id.feedsList);
        //rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        //initializeData();
        //addSampleFeed();

        mAdapter = new AdapterRecentFeedsList(getActivity());
        rv.setAdapter(mAdapter);
        refreshFeedList();
        //getFeedUserEmails();
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                mAdapter = new AdapterRecentFeedsList(getActivity());
//                rv.setAdapter(mAdapter);
                feeds.clear();
                refreshFeedList();
                mAdapter.setRecentFeedsList(feeds);
            }
        });
        return v;
    }


    public void refreshFeedList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("feeds");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> feedList, ParseException e) {
                if (e == null) {
                    for (ParseObject feed : feedList) {
                        addToFeedList(feed.getString("email"), feed.getString("time"), feed.getString("venue"));
                        Log.d("score", "Retrieved " + feed.getString("time") + " | " + feed.getString("venue") + " scores");
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                mAdapter.setRecentFeedsList(feeds);
            }
        });
    }

//    public void getFeedUserEmails() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        query.getInBackground("aC7LkH9Koo", new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    Log.e("user", "User " + object.getString("foo"));
//
//                } else {
//                    Log.e("use2r", "nothing");
//                }
//            }
//        });
////        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
////        //query2.whereEqualTo("objectId", currentFeedCardUserId);
////        //query2.selectKeys(Arrays.asList("email"));
//////                        query2.findInBackground(new FindCallback<ParseObject>() {
//////                            public void done(List<ParseObject> userList, ParseException e) {
//////                                if (e == null) {
////////                                    for (ParseObject feed : userList) {
////////                                        Log.d("score", "Retrieveduser " + currentFeedCardUserId + " " + userList + " scores");
////////                                    }
//////                                    List<String> postTexts = new ArrayList<>();
//////                                    for(ParseObject post : userList){
//////                                        //postTexts.add(post.getString("email"));
//////                                        Log.d("score", "Retrieveduser " + post.getString("email") + " scores");
//////                                    }
//////                                    //Toast.makeText(getActivity(), "query error: " + e, Toast.LENGTH_LONG).show();
//////
//////                                } else {
//////                                    Log.d("score", "Error: " + e.getMessage());
//////                                }
//////                            }
//////                        });
////        query.getInBackground(currentFeedCardUserId, new GetCallback<ParseObject>() {
////            public void done(ParseObject object, ParseException e) {
////                if (e == null) {
////                    Log.d("score2", "User " + object.getString("username") + " scores");
////                } else {
////                    // something went wrong
////                }
////            }
////        });
//    }

    public void addToFeedList(String email, String time, String venue) {
        feeds.add(new FeedModel(email, time, venue));
    }

}

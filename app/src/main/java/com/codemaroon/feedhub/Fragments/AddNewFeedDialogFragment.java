package com.codemaroon.feedhub.Fragments;


import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

import com.codemaroon.feedhub.ParseAPIBackend.FeedObject;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.codemaroon.feedhub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewFeedDialogFragment extends DialogFragment {

    CheckBox bfast, lunch, dinner;
    Button mAddNew;
    EditText mVenue;

    String currentUserUsername;

    public AddNewFeedDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences feedMeUser;
        feedMeUser = getActivity().getSharedPreferences("feedMeUser", getActivity().MODE_PRIVATE); //1
        currentUserUsername = feedMeUser.getString("username", "null"); //2

        View v = inflater.inflate(R.layout.fragment_add_new_feed_dialog, null);
        bfast = (CheckBox) v.findViewById(R.id.bfastCheck);
        lunch = (CheckBox) v.findViewById(R.id.lunchCheck);
        dinner = (CheckBox) v.findViewById(R.id.dinnerCheck);
        mVenue = (EditText) v.findViewById(R.id.addVenue);
        mAddNew = (Button) v.findViewById(R.id.addNewButton);
        mAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timeReturnCode(bfast, lunch, dinner).equals("") || mVenue.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "You can't leave anything blank", Toast.LENGTH_LONG).show();
                } else {
                    String time = timeReturnCode(bfast, lunch, dinner);
                    String venue = mVenue.getText().toString();
                    addFeedsRow(currentUserUsername, time, venue);
                    Toast.makeText(getActivity(), "Meal request has been added.", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }
        });
        return v;
    }

    public void addFeedsRow(String email, String time, String venue) {
        ParseObject feed = new ParseObject("feeds");
        //feed.put("user_id", userId);
        //feed.put("user_id", ParseObject.createWithoutData("User", "4zvXpBClLb"));
        feed.put("email", email);
        feed.put("time", time);
        feed.put("venue", venue);
        feed.put("completed", false);
        feed.saveEventually();
    }

    private String timeReturnCode(CheckBox bfast, CheckBox lunch, CheckBox dinner) {
        boolean b = bfast.isChecked();
        boolean l = lunch.isChecked();
        boolean d = dinner.isChecked();
        if (b && !l && !d) {
            return "Breakfast";
        } else if (!b && l && !d) {
            return "Lunch";
        } else if (!b && !l && d) {
            return "Dinner";
        } else if (b && l && !d) {
            return "Breakfast and Lunch";
        } else if (b && !l && d) {
            return "Breakfast and Dinner";
        } else if (!b && l && d) {
            return "Lunch and Dinner";
        } else if (b && l && d) {
            return "Anytime";
        } else {
            return "";
        }
    }


}

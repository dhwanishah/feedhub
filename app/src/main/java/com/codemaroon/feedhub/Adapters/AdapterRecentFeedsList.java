package com.codemaroon.feedhub.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codemaroon.feedhub.ParseAPIBackend.FeedModel;
import com.codemaroon.feedhub.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecentFeedsList extends RecyclerView.Adapter<AdapterRecentFeedsList.FeedViewHolder>{

    LayoutInflater layoutInflater;
    List<FeedModel> feeds = new ArrayList<>();
    final Context context;

    public AdapterRecentFeedsList(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        //this.feeds = feeds;
    }

    public void setRecentFeedsList(List<FeedModel> list) {
        this.feeds = list;
        notifyItemRangeChanged(0, list.size());
        notifyDataSetChanged();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.feed_cardview, parent, false);
        FeedViewHolder pvh = new FeedViewHolder(this.context, v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.email.setText(feeds.get(position).getEmail());
        holder.team.setText(feeds.get(position).getTeam());
        holder.venue.setText(feeds.get(position).getVenue());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView email, team, venue;
        //final Context mContext;

        FeedViewHolder (final Context context, View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.feedEmail);
            team = (TextView)itemView.findViewById(R.id.feedTime);
            venue = (TextView)itemView.findViewById(R.id.feedVenue);
            cv = (CardView) itemView.findViewById(R.id.cv);
            cv.setCardBackgroundColor(Color.parseColor("#ffffff"));
            cv.setRadius(10);
            cv.setMaxCardElevation(5);
            cv.setContentPadding(7, 7, 7, 7);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email.getText().toString()});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Can you feed me?");
                    i.putExtra(Intent.EXTRA_TEXT, "Hey I saw your post...can you feed me maybe? My contact info is: ");
                    try {
                        context.startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("cardClick", email.getText().toString());
                }
            });

        }
    }
}

package com.hgc.learnandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by harshit on 18/12/2016.
 */

public class mainTopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
   private List<mainTopicsMembers> data = Collections.emptyList();
   private String result;

    public mainTopicsAdapter(Context context, List<mainTopicsMembers> data,String result) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.result=result;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_topics_card, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyHolder myHolder = (MyHolder) holder;
        mainTopicsMembers current = data.get(position);
        myHolder.title.setText(current.title);
        myHolder.body.setText(current.body);
        myHolder.starbar.setRating(4);
        //myHolder.starbar.setRating(current.rating);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;
        RatingBar starbar;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,SubActivity.class);
                    int pos=getAdapterPosition();
                    intent.putExtra("position",pos);
                    intent.putExtra("jsonresult",result);
                    context.startActivity(intent);
                }
            });
            title = (TextView) itemView.findViewById(R.id.textTitle);
            body = (TextView) itemView.findViewById(R.id.textdata);
            starbar=(RatingBar) itemView.findViewById(R.id.ratingBar);

        }

    }

}


package com.hgc.learnandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by harshit on 18/12/2016.
 */

public class mainTopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<mainTopicsMembers> data = Collections.emptyList();

    public mainTopicsAdapter(Context context, List<mainTopicsMembers> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
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
        myHolder.starone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.starone.setImageResource(R.drawable.ic_star_fill);
                myHolder.startwo.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starthree.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starfour.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starfive.setImageResource(R.drawable.ic_star_unfill);
            }
        });
        myHolder.startwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.starone.setImageResource(R.drawable.ic_star_fill);
                myHolder.startwo.setImageResource(R.drawable.ic_star_fill);
                myHolder.starthree.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starfour.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starfive.setImageResource(R.drawable.ic_star_unfill);
            }
        });
        myHolder.starthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.starone.setImageResource(R.drawable.ic_star_fill);
                myHolder.startwo.setImageResource(R.drawable.ic_star_fill);
                myHolder.starthree.setImageResource(R.drawable.ic_star_fill);
                myHolder.starfour.setImageResource(R.drawable.ic_star_unfill);
                myHolder.starfive.setImageResource(R.drawable.ic_star_unfill);
            }
        });
        myHolder.starfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.starone.setImageResource(R.drawable.ic_star_fill);
                myHolder.startwo.setImageResource(R.drawable.ic_star_fill);
                myHolder.starthree.setImageResource(R.drawable.ic_star_fill);
                myHolder.starfour.setImageResource(R.drawable.ic_star_fill);
                myHolder.starfive.setImageResource(R.drawable.ic_star_unfill);
            }
        });
        myHolder.starfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.starone.setImageResource(R.drawable.ic_star_fill);
                myHolder.startwo.setImageResource(R.drawable.ic_star_fill);
                myHolder.starthree.setImageResource(R.drawable.ic_star_fill);
                myHolder.starfour.setImageResource(R.drawable.ic_star_fill);
                myHolder.starfive.setImageResource(R.drawable.ic_star_fill);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;
        ImageView starone,startwo,starthree,starfour,starfive;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicled at pos "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
            title = (TextView) itemView.findViewById(R.id.textTitle);
            body = (TextView) itemView.findViewById(R.id.textdata);
            starone=(ImageView)itemView.findViewById(R.id.star1);
            startwo=(ImageView)itemView.findViewById(R.id.star2);
            starthree=(ImageView)itemView.findViewById(R.id.star3);
            starfour=(ImageView)itemView.findViewById(R.id.star4);
            starfive=(ImageView)itemView.findViewById(R.id.star5);

        }

    }

}


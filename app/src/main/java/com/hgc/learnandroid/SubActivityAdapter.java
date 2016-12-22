package com.hgc.learnandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by hp on 21/12/2016.
 */

public class SubActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
   private List<SubActivityMembers> data=Collections.emptyList();
    private String result;

    SubActivityAdapter(Context context,List<SubActivityMembers> data,String result)
    {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data=data;
        this.result=result;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.sub_topics_card,parent,false);
        SubHolder subHolder=new SubHolder(view);
        return subHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SubHolder subHolder = (SubHolder) holder;
        SubActivityMembers current = data.get(position);
        int pos=position+1;
        subHolder.subtitle.setText(pos+". "+current.subtitle);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class SubHolder extends RecyclerView.ViewHolder {

        TextView subtitle;

        // create constructor to get widget reference
        public SubHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent=new Intent(context,SubActivity.class);
//                    int pos=getAdapterPosition();
//                    intent.putExtra("position",pos);
//                    context.startActivity(intent);
                    Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
                }
            });
            subtitle = (TextView) itemView.findViewById(R.id.textsubtitle);
        }

    }

}

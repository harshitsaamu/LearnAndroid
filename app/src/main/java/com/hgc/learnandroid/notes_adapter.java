package com.hgc.learnandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by harshit on 29-03-2017.
 */

public class notes_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> note;
    private LayoutInflater inflater;

    public notes_adapter(Context context, List<String> note) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.note=note;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view=inflater.inflate(R.layout.single_note,parent,false);
        ThisHolder holder=new ThisHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
   final ThisHolder myHolder=(ThisHolder) holder;
        String notee=note.get(position);
        myHolder.noteView.setText(notee);
    }

    @Override
    public int getItemCount() {
        return note.size();
    }
    class ThisHolder extends RecyclerView.ViewHolder{
     TextView noteView;
        public ThisHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent note_view_to_edit=new Intent(context,edit_note.class);
                    note_view_to_edit.putExtra("Note",note.get(getPosition()));
                    context.startActivity(note_view_to_edit);
                }
            });
            noteView=(TextView)itemView.findViewById(R.id.note_text);
        }
    }
}

package com.heloworld.hp.getnotes;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView textView;
    public MyViewHolder( View v) {
        super(v);
        textView=v.findViewById(R.id.textview);





    }



}

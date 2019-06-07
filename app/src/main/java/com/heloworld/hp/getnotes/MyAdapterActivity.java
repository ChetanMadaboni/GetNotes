package com.heloworld.hp.getnotes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;




public class MyAdapterActivity extends RecyclerView.Adapter<MyViewHolder> {
     private ArrayList<Subject> subjects;
     public Context context;
     private String chetan;
     MyAdapterActivity( Context context,ArrayList<Subject> arrayList,String subname){

         subjects = arrayList;
         this.context = context ;
         chetan = subname;
     }

    public void refresh(ArrayList<Subject> arrayList){
        subjects=arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v =(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_subjectslayout,parent,false);

        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder view, int position) {
      final Subject subject =subjects.get(position);
      final String subname = subject.subjects;
      view.textView.setText(subname);
      view.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,UnitListActivity.class);
              Bundle bundle = new Bundle();
              bundle.putString("subject",subname);
              bundle.putString("section",chetan);
              intent.putExtras(bundle);
              Log.d("bundle", String.valueOf(bundle));
              view.itemView.getContext().startActivity(intent);
          }
      });

      }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}

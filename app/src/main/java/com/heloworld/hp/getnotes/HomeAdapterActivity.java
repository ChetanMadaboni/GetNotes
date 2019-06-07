package com.heloworld.hp.getnotes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class HomeAdapterActivity extends RecyclerView.Adapter<HomeViewHolder> {
    private Context context;
    private ArrayList<Section> sections;

    HomeAdapterActivity(Context context, ArrayList<Section> arrayList) {
        sections = arrayList;
        this.context = context;

    }

    public void refresh(ArrayList<Section> arrayList) {
        sections = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View s = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_homelayout, parent, false);
        return new HomeViewHolder(s);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder homeViewHolder, int position) {
        final Section section = sections.get(position);
        final String sname = section.section_name;
        homeViewHolder.textViewh.setText(sname);
        homeViewHolder.textViewh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SubjectListActivity.class).putExtra("section", String.valueOf(sname));
                //Log.d("section", String.valueOf(sections));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return sections.size();
    }
}

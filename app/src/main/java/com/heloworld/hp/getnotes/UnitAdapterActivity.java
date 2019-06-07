package com.heloworld.hp.getnotes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UnitAdapterActivity extends RecyclerView.Adapter<UnitViewHolder> {
    private ArrayList<Unit> units;
    private Context context;
    private String subject;
    private String section;

    UnitAdapterActivity(Context ctx, ArrayList<Unit> arrayList, String section, String subject) {
        units = arrayList;
        context = ctx;
        this.section = section;
        this.subject = subject;
    }

    public void refresh(ArrayList<Unit> arrayList) {
        units = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.acitivity_unitslayout, parent, false);
        UnitViewHolder viewHolder = new UnitViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UnitViewHolder holder, final int position) {
        final Unit unit = units.get(position);
        final String unitno = unit.unit_num;
        holder.textView1.setText(unitno);
        Log.d("log",unitno);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LinkListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("unit",unitno);
                bundle.putString("subject",subject);
                bundle.putString("section",section);
                intent.putExtras(bundle);
                Log.d("bundle", String.valueOf(bundle));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return units.size();
    }


    }


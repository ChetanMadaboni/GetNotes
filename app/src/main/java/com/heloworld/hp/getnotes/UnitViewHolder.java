package com.heloworld.hp.getnotes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class UnitViewHolder extends RecyclerView.ViewHolder {
    TextView textView1;
    public UnitViewHolder(@NonNull View v) {
        super(v);
        textView1=v.findViewById(R.id.textviewunit);


    }


}

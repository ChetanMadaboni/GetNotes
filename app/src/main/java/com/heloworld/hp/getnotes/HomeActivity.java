package com.heloworld.hp.getnotes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.common.collect.Lists;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Section> sections = new ArrayList<>();
    private HomeAdapterActivity hadapter;
    private LinearLayoutManager hlayoutmanager;
    private RecyclerView hrecylerview;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ComputerScience");
        setSupportActionBar(toolbar);

        hrecylerview = findViewById(R.id.recycleviewh);
        hrecylerview.setHasFixedSize(true);
        LinearLayoutManager hlayoutmanager = new LinearLayoutManager(this);
        hrecylerview.setLayoutManager(hlayoutmanager);

        Resources resources = getResources();
        InputStream i = resources.openRawResource(R.raw.getnotes);
        Scanner scanner = new Scanner(i);
        StringBuilder stringBuilder = new StringBuilder();
        String list = null;
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        JSONObject mainobject = null;
        try {
            mainobject = new JSONObject(String.valueOf(stringBuilder));
            JSONObject getnotes = mainobject.getJSONObject("getnotes");
           // Log.d("string", String.valueOf(getnotes));
            Iterator<String> iterator = getnotes.keys();
            while (iterator.hasNext()){
                Section section = new Section();
                section.section_name = iterator.next();
                sections.add(section);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hadapter = new HomeAdapterActivity(this,sections);
        hrecylerview.setAdapter(hadapter);
        hadapter.refresh(sections);
    }


}

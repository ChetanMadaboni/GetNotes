package com.heloworld.hp.getnotes;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SubjectListActivity extends AppCompatActivity {
    private RecyclerView mrecyclerview;
    private MyAdapterActivity madapter;
    private LinearLayoutManager mlayoutmanager;
    private ArrayList<Subject> arrayList = new ArrayList<>();
    //Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects");
        toolbar.setNavigationIcon(R.drawable.leftarraow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mrecyclerview = findViewById(R.id.recycleview);
        mrecyclerview.setHasFixedSize(true);
        LinearLayoutManager mlayoutmanager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(mlayoutmanager);
        Bundle bundle = getIntent().getExtras();
        String chetan =  String.valueOf(bundle.getString("section"));
        Log.d("Chetan", chetan);


        Resources resources = getResources();
        InputStream i = resources.openRawResource(R.raw.getnotes);
        Scanner scanner = new Scanner(i);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        JSONObject classobject = null;
        try {
            classobject = new JSONObject(String.valueOf(stringBuilder));
            JSONObject getnotes = classobject.getJSONObject("getnotes");
           // Log.d("getnotes", String.valueOf(getnotes));
            JSONObject jsonObject = getnotes.getJSONObject(chetan);
            Log.d("bundle", String.valueOf(bundle));
            Iterator<String> stringIterator = jsonObject.keys();
            while (stringIterator.hasNext()){
                Subject subject = new Subject();
                subject.subjects = stringIterator.next();
                arrayList.add(subject);
                Log.d("subjects", String.valueOf(subject));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        madapter = new MyAdapterActivity(this,arrayList,chetan);
        mrecyclerview.setAdapter(madapter);
        madapter.refresh(arrayList);
    }
}


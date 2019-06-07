package com.heloworld.hp.getnotes;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UnitListActivity extends Activity {
    private RecyclerView recyclerView;
    private UnitAdapterActivity adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Unit> arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);
        Toolbar toolbar =findViewById(R.id.toolbar) ;
        toolbar.setTitle("units");
        toolbar.setNavigationIcon(R.drawable.leftarraow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.recycleview1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getIntent().getExtras();
        Log.d("Bundle", String.valueOf(bundle));
        String section = String.valueOf(bundle.getString("section"));
        String subject = String.valueOf(bundle.getString("subject"));
        Log.d("section",section);
        Log.d("subject",subject);
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
            JSONObject getnotes1 = getnotes.getJSONObject(section);
            JSONObject getnotes2 = getnotes1.getJSONObject(subject);
            Iterator<String> iterator = getnotes2.keys();
            while (iterator.hasNext()){
                Unit unit = new Unit();
                unit.unit_num = iterator.next();
                arrayList.add(unit);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new UnitAdapterActivity(getBaseContext(),arrayList,section,subject);
        recyclerView.setAdapter(adapter);
        adapter.refresh(arrayList);

    }

}
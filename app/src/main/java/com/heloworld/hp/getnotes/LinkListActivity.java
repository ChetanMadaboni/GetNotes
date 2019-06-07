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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LinkListActivity extends Activity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LinkAdapterActivity adapterActivity;
    ArrayList<Link> arraylink = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ReferenceLinks");
        toolbar.setNavigationIcon(R.drawable.leftarraow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.recycleviewlink);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getIntent().getExtras();
     //   Log.d("bundle", String.valueOf(bundle));
        String section = String.valueOf(bundle.getString("section"));
        String subject = String.valueOf(bundle.getString("subject"));
        String unitno = String.valueOf(bundle.getString("unit"));
        Resources resources = getResources();
        InputStream is = resources.openRawResource(R.raw.getnotes);
        Scanner scanner = new Scanner(is);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        JSONObject classobject = null;
        try {
            classobject = new JSONObject(String.valueOf(stringBuilder));
            JSONObject getnotes = classobject.getJSONObject("getnotes");
            JSONObject jsonObject = getnotes.getJSONObject(section);
            Log.d("section", String.valueOf(jsonObject));
            JSONObject jsonObject1 = jsonObject.getJSONObject(subject);
            Log.d("subject", String.valueOf(jsonObject1));
           // JSONObject jsonObject2 = jsonObject1.getJSONObject(unitno);
            //Log.d("unitno", String.valueOf(jsonObject2));
            JSONArray jsonArray = jsonObject1.getJSONArray(unitno);
            Log.d("jsonarray", String.valueOf(jsonArray));
            String array = jsonArray.toString();
            Log.d("array",array);
            for (int i = 0;i<jsonArray.length();i++){
                //JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Link link = new Link();
                link.link = jsonArray.getString(i);
                arraylink.add(link);
                Log.d("link",String.valueOf(link));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapterActivity = new LinkAdapterActivity(getBaseContext(),arraylink);
        recyclerView.setAdapter(adapterActivity);

    }


}

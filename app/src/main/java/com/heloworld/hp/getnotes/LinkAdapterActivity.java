package com.heloworld.hp.getnotes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LinkAdapterActivity extends RecyclerView.Adapter<LinkViewHolder> {
    private ArrayList<Link> links1;
    private Context context;

    LinkAdapterActivity(Context ctx ,ArrayList<Link> arrayList){
        context = ctx;
        links1 = arrayList;

    }
    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_linklayout,viewGroup,false);
        LinkViewHolder viewHolder = new LinkViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final LinkViewHolder linkViewHolder, int position) {
        final Link link = links1.get(position);
        final String lname = link.link;
        linkViewHolder.linkview.setText(lname);
        linkViewHolder.linkview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(lname);
                Intent intent = new Intent(Intent.ACTION_VIEW,link);
                linkViewHolder.linkview.getContext().startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return links1.size();
    }
}

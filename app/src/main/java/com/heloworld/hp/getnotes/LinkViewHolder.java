package com.heloworld.hp.getnotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    TextView linkview;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        linkview = itemView.findViewById(R.id.linktextview);
    }
}

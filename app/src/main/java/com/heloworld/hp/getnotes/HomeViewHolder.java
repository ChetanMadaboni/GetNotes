package com.heloworld.hp.getnotes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    TextView textViewh;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewh = itemView.findViewById(R.id.textviewmain);
    }
}

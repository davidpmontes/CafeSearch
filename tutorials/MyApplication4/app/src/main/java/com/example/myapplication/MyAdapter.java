package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private ArrayList<String> myList = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context, ArrayList<String> myList) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.listItem.setText(myList.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, myList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}

class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView listItem;
    RelativeLayout parentLayout;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        listItem = itemView.findViewById(R.id.item_name);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }
}
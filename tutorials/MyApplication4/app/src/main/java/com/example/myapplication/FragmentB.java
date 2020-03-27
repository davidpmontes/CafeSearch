package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FragmentB extends Fragment {
    Context mainActivityContext;

    private ArrayList<String> myNames = new ArrayList<>();
    private ArrayList<NameAddressRating> nameAddressRatings = new ArrayList<>();

    private View view;

    public FragmentB(Context context) {
        mainActivityContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
    }

    public void clearRecyclerView()
    {
        myNames.clear();
        nameAddressRatings.clear();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.Adapter adapter = new MyAdapter(getContext(), myNames, nameAddressRatings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateRecyclerView(JSONArray resultsArray)
    {
        myNames.clear();
        nameAddressRatings.clear();

        try{
            for (int i = 0; i < resultsArray.length(); i++)
            {
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String address = jsonObject.getString("vicinity");
                float rating = Float.valueOf(jsonObject.getString("rating"));

                myNames.add(name);
                nameAddressRatings.add(new NameAddressRating(name, address, rating));
            }

            Collections.sort(nameAddressRatings);
            Collections.reverse(nameAddressRatings);

            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            RecyclerView.Adapter adapter = new MyAdapter(getContext(), myNames, nameAddressRatings);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }
}

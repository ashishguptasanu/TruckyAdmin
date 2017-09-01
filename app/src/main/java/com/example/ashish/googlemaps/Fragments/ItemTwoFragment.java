package com.example.ashish.googlemaps.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.googlemaps.Adapters.DeliveryAdapter;
import com.example.ashish.googlemaps.Adapters.NewTaskAdapter;
import com.example.ashish.googlemaps.Models.InProgress;
import com.example.ashish.googlemaps.Models.Task;
import com.example.ashish.googlemaps.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 31-08-2017.
 */

public class ItemTwoFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DeliveryAdapter mAdapter;
    View view;
    List<InProgress> taskList = new ArrayList<>();
    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_two, container, false);
        InProgress task1 = new InProgress("3569","Out For Delivery", "09-09-2017", "10-11 AM",28.4560583,77.0737073,28.5570583,77.4040073,"Ashish Kumar","9451133507");
        InProgress task2 = new InProgress("3569","Out For Delivery", "12-09-2017", "07-11 AM",28.4560583,77.0737073,28.5570583,77.4040073,"Vinay Gupta","7586953426");
        InProgress task3 = new InProgress("3569","Assigned", "18-09-2017", "03-11 PM",28.4560583,77.0737073,28.4570583,77.4037073,"Deepak Ranolia","8566532635");
        InProgress task4 = new InProgress("3569","Out For Delivery", "16-09-2017", "10-11 PM",28.4560583,77.0737073,28.4560583,77.0737073,"Jitendra Ram","9999697915");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DeliveryAdapter(getContext(), taskList);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}

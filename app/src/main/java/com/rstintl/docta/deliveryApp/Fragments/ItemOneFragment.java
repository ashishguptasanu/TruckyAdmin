package com.rstintl.docta.deliveryApp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rstintl.docta.deliveryApp.Adapters.NewTaskAdapter;
import com.rstintl.docta.deliveryApp.Models.Task;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 31-08-2017.
 */

public class ItemOneFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewTaskAdapter mAdapter;
    View view;
    List<Task> taskList = new ArrayList<>();
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_one, container, false);
        Task task1 = new Task("3569","Not Assigned", "09-09-2017", "10-11 AM", "B-25, Industrial Area, Sector-32, Gurgaon","K9/101, Uppal Southend, Sector-48, Gurgaon","9958808464","9451133507");
        Task task2 = new Task("3570","Not Assigned", "10-09-2017", "4-6 PM", "D-455, Artimis Hospital, Sector-53, Gurgaon","S-111, Vatika City Homes, Sector-83, Gurgaon","9958808464","9451133507");
        Task task3 = new Task("3571","Not Assigned", "12-09-2017", "2-6 PM", "A13/202, Vipul Greens, Sector-84, Gurgaon","G1/2-543, Malibu Town, Sector-51, Gurgaon","9958808464","9451133507");
        Task task4 = new Task("3572","Not Assigned", "17-09-2017", "8-11 AM", "C-505, Bestech Tower, Sector-72, Gurgaon","H8, Industrial Area, Sector-32, Gurgaon","9958808464","9451133507");
        Task task5 = new Task("3573","Not Assigned", "11-09-2017", "3-9 PM", "H1-606, Vatika Business Park, Sector-12, Gurgaon","P-19, Sushant Lok, Sector-9, Gurgaon","9958808464","9451133507");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewTaskAdapter(getContext(), taskList);
        recyclerView.setAdapter(mAdapter);
        return view;
    }


}
package com.rstintl.docta.deliveryApp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rstintl.docta.deliveryApp.Adapters.CompletedAdapter;
import com.rstintl.docta.deliveryApp.Adapters.DeliveryAdapter;
import com.rstintl.docta.deliveryApp.Models.AssignedTask;
import com.rstintl.docta.deliveryApp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Ashish on 31-08-2017.
 */

public class ItemThreeFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OkHttpClient client = new OkHttpClient();
    List<AssignedTask> taskList = new ArrayList<>();
    CompletedAdapter mAdapter;
    View view;
    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
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
        getTaskList();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CompletedAdapter(getContext(), taskList);
        return view;
    }
    private void getTaskList(){
        /*final ProgressDialog progressDialog = ProgressDialog.show(this, "Adding New Driver", "Please wait while we are adding a new profile to our database");
        progressDialog.show();*/
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", "")
                .build();
        Request request = new Request.Builder().url(getResources().getString(R.string.base_url)+"/trucky/task/get_all_assign").addHeader("token","d75542712c868c1690110db641ba01a").post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
                /*showToast("Failed");
                progressDialog.dismiss();*/
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    String resp = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        JSONObject jsonResponse = jsonObject.getJSONObject("Response");
                        JSONObject dataObject = jsonResponse.getJSONObject("data");
                        JSONArray dataArray = dataObject.getJSONArray("assigned_task");
                        for(int i=0; i< dataArray.length();i++){

                            JSONObject jsonObject1 = dataArray.getJSONObject(i);
                            if(Objects.equals(jsonObject1.getString("task_status"), "3")){
                                String taskId = jsonObject1.getString("task_id");
                                String taskPickupAddress = jsonObject1.getString("task_pickup_address");
                                double taskPickupLattitude = jsonObject1.getDouble("task_pickup_latitude");
                                double taskPickupLongitude = jsonObject1.getDouble("task_pickup_longitude");
                                String taskDropoffAddress = jsonObject1.getString("task_dropoff_address");
                                double taskDropoffLatitute = jsonObject1.getDouble("task_dropoff_latitude");
                                double taskDropoffLongitude = jsonObject1.getDouble("task_dropoff_longitude");
                                String taskStartDateTime = jsonObject1.getString("task_start_datetime");
                                String taskEndDateTime = jsonObject1.getString("task_end_datetime");
                                String taskStatus = jsonObject1.getString("task_status");
                                String driverName = jsonObject1.getString("driver_name");
                                String driverContact = jsonObject1.getString("driver_contact");
                                String nameDropoff =jsonObject1.getString("task_delivery_person_name");
                                        AssignedTask assignedTask = new AssignedTask(taskId,taskPickupAddress,taskPickupLattitude,taskPickupLongitude,taskDropoffAddress, taskDropoffLatitute, taskDropoffLongitude, taskStartDateTime, taskEndDateTime, taskStatus, driverName, driverContact, nameDropoff);
                                taskList.add(assignedTask);
                            }

                        }
                        Log.d("Completed List", String.valueOf(taskList.size()));
                        if(getActivity() != null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(mAdapter);
                                }
                            });
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /*progressDialog.dismiss();
                    showToast("Success");*/
                    Log.d("response",resp);
                } catch (IOException e) {
                   /* progressDialog.dismiss();
                    showToast("Failed");*/
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
}

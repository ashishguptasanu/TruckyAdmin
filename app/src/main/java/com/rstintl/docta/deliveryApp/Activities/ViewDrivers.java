package com.rstintl.docta.deliveryApp.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rstintl.docta.deliveryApp.Adapters.DeliveryAdapter;
import com.rstintl.docta.deliveryApp.Adapters.DriverAdapter;
import com.rstintl.docta.deliveryApp.Models.DriverInfo;
import com.rstintl.docta.deliveryApp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ViewDrivers extends AppCompatActivity {
    List<DriverInfo> driverList = new ArrayList<>();
    List<String> driverData = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();
    DriverAdapter driverAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drivers);
        getDriverList();
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void getDriverList(){
        /*final ProgressDialog progressDialog = ProgressDialog.show(this, "Adding New Driver", "Please wait while we are adding a new profile to our database");
        progressDialog.show();*/
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", "")
                .build();
        Request request = new Request.Builder().url(getResources().getString(R.string.base_url)+"/trucky/driver/get_driver").addHeader("token","d75542712c868c1690110db641ba01a").post(requestBody).build();
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
                        JSONArray dataArray = dataObject.getJSONArray("driver_list");
                        for(int i=0; i< dataArray.length();i++){
                            JSONObject jsonObject1 = dataArray.getJSONObject(i);
                            String driverName = jsonObject1.getString("driver_name");
                            String driverId = jsonObject1.getString("driver_id");
                            String driverVehicleType = jsonObject1.getString("driver_vehicle_type");
                            String driverDutyStatus = jsonObject1.getString("driver_duty_status");
                            DriverInfo driverInfo = new DriverInfo(driverId, driverName, driverVehicleType, driverDutyStatus);
                            driverList.add(driverInfo);
                        }
                        for(int k=0; k<driverList.size();k++){
                            driverData.add(driverList.get(k).getDriverName());
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                driverAdapter = new DriverAdapter(getApplicationContext(), driverList);
                                recyclerView.setAdapter(driverAdapter);
                            }
                        });


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

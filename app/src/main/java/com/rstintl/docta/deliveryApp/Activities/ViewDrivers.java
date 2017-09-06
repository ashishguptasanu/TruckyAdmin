package com.rstintl.docta.deliveryApp.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rstintl.docta.deliveryApp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ViewDrivers extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drivers);
        getDriverList();
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

package com.rstintl.docta.deliveryApp.Activities;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rstintl.docta.deliveryApp.R;

import java.io.IOException;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddDriver extends AppCompatActivity implements VerticalStepperForm{
    VerticalStepperFormLayout verticalStepperForm;
    EditText name, contactNumber, email, areaCode, vehicleNumber;
    Spinner vehicleType;
    String selectedvehicleType;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        String[] mySteps = {"Driver Name", "Driver Contact Number","Email", "Area Code","Vehicle Number", "Vehicle Type"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(false) // It is true by default, so in this case this line is not necessary
                .init();
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createContactStep();
                break;
            case 2:
                view = createEmailStep();
                break;
            case 3:
                view = createAreaCodeStep();
                break;
            case 4:
                view = createVehicleNumberStep();
                break;
            case 5:
                view = createVehicleTypeStep();
                break;
        }
        return view;
    }

    private View createVehicleTypeStep() {
        vehicleType = new Spinner(this);
        final String[] vehicleTypeData = new String[]{"Select One","Motorcycle", "Light Motor Vehicle", "Heavy Truck", "Mini Bus","Heavy Bus","Fork Lift","Shovel"};
        ArrayAdapter<String> vehicleDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vehicleTypeData);
        vehicleDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleType.setAdapter(vehicleDataAdapter);
        vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedvehicleType = vehicleTypeData[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return vehicleType;
    }

    private View createVehicleNumberStep() {
        vehicleNumber = new EditText(this);
        vehicleNumber.setHint("Vehicle Number                  ");
        return vehicleNumber;
    }

    private View createAreaCodeStep() {
        areaCode = new EditText(this);
        areaCode.setHint("Driver's Area Code                  ");
        areaCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        return areaCode;
    }

    private View createEmailStep() {
        email = new EditText(this);
        email.setHint("Driver's Email                  ");
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        return email;
    }

    private View createContactStep() {
        contactNumber = new EditText(this);
        contactNumber.setHint("Driver's Contact                  ");
        contactNumber.setInputType(InputType.TYPE_CLASS_PHONE);
        return contactNumber;
    }

    private View createNameStep() {
        name = new EditText(this);
        name.setHint("Driver's Name                  ");
        return name;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 1:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 2:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setActiveStepAsCompleted();
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 3:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 4:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 5:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
        }
    }

    @Override
    public void sendData() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Adding New Driver", "Please wait while we are adding a new profile to our database");
        progressDialog.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name.getText().toString())
                .addFormDataPart("email", email.getText().toString())
                .addFormDataPart("contact", contactNumber.getText().toString())
                .addFormDataPart("vehicle_type", selectedvehicleType)
                .addFormDataPart("vehicle_no", vehicleNumber.getText().toString())
                .addFormDataPart("area_code", areaCode.getText().toString())
                .addFormDataPart("rating", "5")

                .build();
        Request request = new Request.Builder().url(getResources().getString(R.string.base_url)+"/trucky/driver/add_driver").addHeader("token","d75542712c868c1690110db641ba01a").post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
                showToast("Failed");
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    String resp = response.body().string();
                    progressDialog.dismiss();
                    showToast("Success");
                    Log.d("response",resp);
                } catch (IOException e) {
                    progressDialog.dismiss();
                    showToast("Failed");
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void showToast(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

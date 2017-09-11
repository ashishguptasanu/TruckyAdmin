package com.rstintl.docta.deliveryApp.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rstintl.docta.deliveryApp.R;

import java.io.IOException;
import java.util.Calendar;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddVehicle extends AppCompatActivity implements VerticalStepperForm{
    VerticalStepperFormLayout verticalStepperForm;
    EditText vehicleName, vehicleNumber, vehicleMaximumLoad, vehicleLastServiceDate, vehicleNextServiceDate, vehicleInsuranceEndDate;
    Spinner vehicleType;
    RadioGroup rgRefrigerated, rgPestControlled, rgWaterProof;
    RadioButton refrigerated, notRefregerated, pestControlled, notPestControlled, waterProof, notWaterProof;
    String selectedvehicleType;
    String selectedPestControlled, selectedRefregirated, selectedWaterproof;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        String[] mySteps = {"Vehicle Name", "Vehicle Number","Vehicle Type", "Vehicle Maximum Load","Vehicle Last Service Date", "Vehicle Next Service Date","Vehicle Insurance End Date","Is vehicle refrigerated?","Is vehicle pest-controlled?","Is vehicle waterproof?"};
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
                view = createVehicleNameStep();
                break;
            case 1:
                view = createVehicleNumberStep();
                break;
            case 2:
                view = createVehicleTypeStep();
                break;
            case 3:
                view = createVehicleMaxLoadStep();
                break;
            case 4:
                view = createLastServiceDateStep();
                break;
            case 5:
                view = createNextServiceStep();
                break;
            case 6:
                view = createVehicleInsurenceEndStep();
                break;
            case 7:
                view = createVehicleRefrigratedStep();
                break;
            case 8:
                view = createVehiclePestControlStep();
                break;
            case 9:
                view = createVehicleWaterProofStep();
                break;
        }
        return view;
    }
    private View createVehicleNumberStep() {
        vehicleNumber = new EditText(this);
        vehicleNumber.setHint("Vehicle Registration Number");
        return vehicleNumber;
    }

    private View createVehicleNameStep() {
        vehicleName = new EditText(this);
        vehicleName.setHint("Enter Vehicle Name");
        return vehicleName;
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

    private View createVehicleMaxLoadStep() {
        vehicleMaximumLoad = new EditText(this);
        vehicleMaximumLoad.setHint("Enter max. Load(In tons)");
        vehicleMaximumLoad.setInputType(InputType.TYPE_CLASS_NUMBER);
        return vehicleMaximumLoad;
    }

    private View createLastServiceDateStep() {
        vehicleLastServiceDate = new EditText(this);
        vehicleLastServiceDate.setFocusable(false);
        vehicleLastServiceDate.setHint("Last Service Date");
        vehicleLastServiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pastDatePicker(vehicleLastServiceDate);
            }
        });
        return vehicleLastServiceDate;
    }

    private View createNextServiceStep() {
        vehicleNextServiceDate = new EditText(this);
        vehicleNextServiceDate.setHint("Next Service Date");
        vehicleNextServiceDate.setFocusable(false);
        vehicleNextServiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDatePicker(vehicleNextServiceDate);
            }
        });
        return vehicleNextServiceDate;
    }

    private View createVehicleInsurenceEndStep() {
        vehicleInsuranceEndDate = new EditText(this);
        vehicleInsuranceEndDate.setHint("Insurance End Date");
        vehicleInsuranceEndDate.setFocusable(false);
        vehicleInsuranceEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDatePicker(vehicleInsuranceEndDate);
            }
        });
        return vehicleInsuranceEndDate;
    }

    private View createVehicleRefrigratedStep() {
        rgRefrigerated = new RadioGroup(this);
        refrigerated = new RadioButton(this);
        notRefregerated = new RadioButton(this);
        refrigerated.setHint("Yes");
        notRefregerated.setHint("No");
        rgRefrigerated.addView(refrigerated);
        rgRefrigerated.addView(notRefregerated);

        rgRefrigerated.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectedRefregirated = String.valueOf(rgRefrigerated.getCheckedRadioButtonId());
            }
        });
        return rgRefrigerated;
    }

    private View createVehiclePestControlStep() {
        rgPestControlled = new RadioGroup(this);
        pestControlled = new RadioButton(this);
        notPestControlled = new RadioButton(this);
        rgPestControlled.addView(pestControlled);
        rgPestControlled.addView(notPestControlled);
        pestControlled.setHint("Yes");
        notPestControlled.setHint("No");
        rgPestControlled.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectedPestControlled = String.valueOf(rgPestControlled.getCheckedRadioButtonId());
            }
        });
        return rgPestControlled;
    }

    private View createVehicleWaterProofStep() {
        rgWaterProof = new RadioGroup(this);
        waterProof = new RadioButton(this);
        notWaterProof = new RadioButton(this);
        waterProof.setHint("Yes");
        notWaterProof.setHint("No");
        rgWaterProof.addView(waterProof);
        rgWaterProof.addView(notWaterProof);
        rgWaterProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectedWaterproof = String.valueOf(rgWaterProof.getCheckedRadioButtonId());
            }
        });
        return rgWaterProof;
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
                verticalStepperForm.setActiveStepAsCompleted();
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
            case 6:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 7:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 8:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 9:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

        }
    }
    private void nextDatePicker(final EditText edtText){
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final String[] timeStamp = {""};
        DatePickerDialog mDatePicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, final int selectedyear, final int selectedmonth, final int selectedday) {
                edtText.setText(selectedyear+ "-" + selectedmonth + "-" + selectedday);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePicker.show();
    }
    private void pastDatePicker(final EditText edtText){
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final String[] timeStamp = {""};
        DatePickerDialog mDatePicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, final int selectedyear, final int selectedmonth, final int selectedday) {
                edtText.setText(selectedyear+ "-" + selectedmonth + "-" + selectedday);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        mDatePicker.show();
    }

    @Override
    public void sendData() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Adding New Vehicle", "Please wait while we are adding a new vehicle to our database");
        progressDialog.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("vehicle_name", vehicleName.getText().toString())
                .addFormDataPart("vehicle_number", vehicleNumber.getText().toString())
                .addFormDataPart("vehicle_type", selectedvehicleType)
                .addFormDataPart("vehicle_maximum_load", vehicleMaximumLoad.getText().toString())
                .addFormDataPart("vehicle_last_service_date", vehicleLastServiceDate.getText().toString())
                .addFormDataPart("vehicle_next_service_date", vehicleNextServiceDate.getText().toString())
                .addFormDataPart("vehicle_insurance_end_date", vehicleInsuranceEndDate.getText().toString())
                .addFormDataPart("vehicle_refrigerated", selectedRefregirated)
                .addFormDataPart("vehicle_pest_control", selectedPestControlled)
                .addFormDataPart("vehicle_water_proof", selectedWaterproof)
                .build();
        Request request = new Request.Builder().url(getResources().getString(R.string.base_url)+"/trucky/vehicle/add").addHeader("token","d75542712c868c1690110db641ba01a").post(requestBody).build();
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
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }private void showToast(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

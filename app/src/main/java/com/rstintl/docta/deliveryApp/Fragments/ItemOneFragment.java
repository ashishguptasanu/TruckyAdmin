package com.rstintl.docta.deliveryApp.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.rstintl.docta.deliveryApp.Activities.MainActivity;
import com.rstintl.docta.deliveryApp.Adapters.NewTaskAdapter;
import com.rstintl.docta.deliveryApp.Models.DriverInfo;
import com.rstintl.docta.deliveryApp.Models.Task;
import com.rstintl.docta.deliveryApp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.cacheColorHint;
import static android.R.attr.name;
import static android.R.attr.theme;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Ashish on 31-08-2017.
 */

public class ItemOneFragment extends Fragment implements VerticalStepperForm {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewTaskAdapter mAdapter;
    View view;
    OkHttpClient client = new OkHttpClient();
    List<Task> taskList = new ArrayList<>();
    TextView tvPickUp, tvDropOff, tvStartDate, tvEndDate;
    int PLACE_PICKER_REQUEST = 1;
    int PLACE_PICKER_REQUEST2 = 2;
    String startHour, startMinute, startTimeStamp, endTimestamp, selectedVehicleType,selectedDriverID;
    Spinner vehicleType, deliveryAgent;
    List<DriverInfo> driverDetails = new ArrayList<>();
    List<String> driverData = new ArrayList<>();
    ArrayAdapter<String> agentDataAdapter;
    String pickupLat, pickupLang, dropoffLat, dropoffLang;


    String[] vehicleTypeData = new String[]{"Select One","Motorcycle", "Light Motor Vehicle", "Heavy Truck", "Mini Bus","Heavy Bus","Fork Lift","Shovel"};
    EditText pickUp, dropOff, startDateTime, endDateTime, deliverToName, deliverToContact, deliverToAdditionalDetails;
    private VerticalStepperFormLayout verticalStepperForm;
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_one, container, false);
        String[] mySteps = {"Pickup Address", "Dropoff Address", "Start Date & Time", "End Date & Time","Delivery Details", "Vehicle Type", "Delivery Agent"};
        int colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);
        getDriverList();
        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) view.findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, getActivity())
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(false) // It is true by default, so in this case this line is not necessary
                .init();
        /*tvPickUp = (TextView)view.findViewById(R.id.tv_pickup);
        tvDropOff = (TextView)view.findViewById(R.id.tv_Dropoff);
        tvStartDate = (TextView)view.findViewById(R.id.tv_start_date);
        tvEndDate = (TextView)view.findViewById(R.id.tv_end_date);
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate=Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, final int selectedyear, final int selectedmonth, final int selectedday) {
                        final String[] selectedHourFinal = new String[1];
                        final String[] selectedMinuteFinal = new String[1];
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                Log.d("Time",selectedHour + ":"+ selectedMinute);
                                if(selectedHour < 10){
                                    selectedHourFinal[0] = "0"+ selectedHour;
                                }
                                if(selectedMinute < 10){
                                    selectedMinuteFinal[0] = "0"+selectedMinute;
                                }
                                if(selectedHour >= 10){
                                    selectedHourFinal[0] = String.valueOf(selectedHour);
                                }
                                if(selectedMinute >= 10){
                                    selectedMinuteFinal[0] = String.valueOf(selectedMinute);
                                }
                                startHour = selectedHourFinal[0];
                                startMinute = selectedMinuteFinal[0];
                                tvStartDate.setText((selectedyear +"-"+(selectedmonth+1)+"-"+selectedday) + " " +  startHour + ":" + startMinute);
                            }
                        }, hour, minute, false);
                        mTimePicker.setTitle("Select Start Time");
                        mTimePicker.show();

                    }
                },mYear, mMonth, mDay);
                //mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDatePicker.setTitle("Select Start Date");
                mDatePicker.show();
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        tvDropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST2);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });*/
        /*Task task1 = new Task("3569","Not Assigned", "09-09-2017", "10-11 AM", "B-25, Industrial Area, Sector-32, Gurgaon","K9/101, Uppal Southend, Sector-48, Gurgaon","9958808464","9451133507");
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
        recyclerView.setAdapter(mAdapter);*/
        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getName());
                pickUp.setText(place.getAddress());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
                pickupLat = String.valueOf(place.getLatLng().latitude);
                pickupLang = String.valueOf(place.getLatLng().longitude);
            }
        }else if (requestCode == PLACE_PICKER_REQUEST2) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getName());
                dropOff.setText(place.getAddress());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
                dropoffLat = String.valueOf(place.getLatLng().latitude);
                dropoffLang = String.valueOf(place.getLatLng().longitude);
            }
        }
    }

    private void dateTimePicker(final EditText edtDate1) {
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final String[] timeStamp = {""};
        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, final int selectedyear, final int selectedmonth, final int selectedday) {
                final String[] selectedHourFinal = new String[1];
                final String[] selectedMinuteFinal = new String[1];
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour < 10){
                            selectedHourFinal[0] = "0"+ selectedHour;
                        }
                        if(selectedMinute < 10){
                            selectedMinuteFinal[0] = "0"+selectedMinute;
                        }
                        if(selectedHour >= 10){
                            selectedHourFinal[0] = String.valueOf(selectedHour);
                        }
                        if(selectedMinute >= 10){
                            selectedMinuteFinal[0] = String.valueOf(selectedMinute);
                        }
                        startHour = selectedHourFinal[0];
                        startMinute = selectedMinuteFinal[0];
                        //timeStamp[0] = String.valueOf(new SimpleDateFormat(selectedyear +"-"+selectedmonth+"-"+selectedday+"'T'"+startHour+":"+startMinute+":00'Z'"));
                        //Log.d("TimeStamp",timeStamp[0]);
                        edtDate1.setText((selectedyear +"-"+(selectedmonth+1)+"-"+selectedday) + " " +  startHour + ":" + startMinute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();

            }
        },mYear, mMonth, mDay);
        //mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDatePicker.setTitle("Select Start Date");
        mDatePicker.show();
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createPickUpAddress();
                break;
            case 1:
                view = createDropoffAddress();
                break;
            case 2:
                view = createStartDateTimePicker();
                break;
            case 3:
                view = createEndDateTimePicker();
                break;
            case 4:
                view = createDeliveryDetailsView();
                break;
            case 5:
                view = createVehicleTypeStep();
                break;
            case 6:
                view = createDeliveryAgentStep();
                break;

        }
        return view;
    }

    private View createDeliveryAgentStep() {
        deliveryAgent = new Spinner(getContext());
        //vehicleType.setOnItemSelectedListener();
        agentDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, driverData);
        agentDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deliveryAgent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDriverID = driverDetails.get(i).getDriverId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return deliveryAgent;
    }

    private View createVehicleTypeStep() {
        vehicleType = new Spinner(getContext());
        //vehicleType.setOnItemSelectedListener();
        ArrayAdapter<String> vehicleDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, vehicleTypeData);
        vehicleDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleType.setAdapter(vehicleDataAdapter);
        vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedVehicleType = vehicleTypeData[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return  vehicleType;

    }

    private View createDeliveryDetailsView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout deliveryLayout = (LinearLayout) inflater.inflate(R.layout.delivery_details, null, false);
        deliverToName = (EditText)deliveryLayout.findViewById(R.id.deliver_person_name);
        deliverToContact = (EditText)deliveryLayout.findViewById(R.id.deliver_person_contact);
        deliverToAdditionalDetails = (EditText)deliveryLayout.findViewById(R.id.deliver_additional_info);
        return deliveryLayout;
    }

    private View createEndDateTimePicker() {
        endDateTime = new EditText(getContext());
        endDateTime.setSingleLine(false);
        endDateTime.setHint("Select End Date & Time");
        endDateTime.setFocusable(false);
        endDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimePicker(endDateTime);
            }
        });

        return endDateTime;
    }

    private View createStartDateTimePicker() {
        startDateTime = new EditText(getContext());
        startDateTime.setSingleLine(false);
        startDateTime.setHint("Select Start Date & Time");
        startDateTime.setFocusable(false);
        startDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimePicker(startDateTime);
                //Log.d("Start TimeStamp", startTimeStamp);
            }
        });

        return startDateTime;
    }

    private View createDropoffAddress() {
        dropOff = new EditText(getContext());
        dropOff.setSingleLine(false);
        dropOff.setFocusable(false);
        dropOff.setHint("Select Dropoff Address");
        dropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST2);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        return dropOff;
    }

    private View createPickUpAddress() {
        pickUp = new EditText(getContext());
        pickUp.setSingleLine(false);
        pickUp.setHint("Select Pickup Address");
        pickUp.setFocusable(false);
        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        return pickUp;

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

        }
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
                            DriverInfo driverInfo = new DriverInfo(driverId, driverName, driverVehicleType);
                            driverDetails.add(driverInfo);
                        }
                        for(int k=0; k<driverDetails.size();k++){
                            driverData.add(driverDetails.get(k).getDriverName());
                        }
                        deliveryAgent.setAdapter(agentDataAdapter);

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

    @Override
    public void sendData() {
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Assigning New Task", "Please Wait, Assigning new task");
        progressDialog.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("task_pickup_address", pickUp.getText().toString())
                .addFormDataPart("task_pickup_latitude", pickupLat)
                .addFormDataPart("task_pickup_longitude", pickupLang)
                .addFormDataPart("task_dropoff_address", dropOff.getText().toString())
                .addFormDataPart("task_dropoff_latitude", dropoffLat)
                .addFormDataPart("task_dropoff_longitude", dropoffLang)
                .addFormDataPart("task_start_datetime", startDateTime.getText().toString())
                .addFormDataPart("task_start_timestamp", "")
                .addFormDataPart("task_end_datetime", endDateTime.getText().toString())
                .addFormDataPart("task_end_timestamp", "")
                .addFormDataPart("task_delivery_person_name", deliverToName.getText().toString())
                .addFormDataPart("task_delivery_person_contact", deliverToContact.getText().toString())
                .addFormDataPart("task_delivery_person_address", deliverToAdditionalDetails.getText().toString())
                .addFormDataPart("task_vehicle_type", selectedVehicleType)
                .addFormDataPart("task_driver_id", selectedDriverID)
                .build();
        Request request = new Request.Builder().url(getResources().getString(R.string.base_url)+"/trucky/task/assign").addHeader("token","d75542712c868c1690110db641ba01a").post(requestBody).build();
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
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
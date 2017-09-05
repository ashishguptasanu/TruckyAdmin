package com.rstintl.docta.deliveryApp.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.rstintl.docta.deliveryApp.Adapters.NewTaskAdapter;
import com.rstintl.docta.deliveryApp.Models.Task;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.cacheColorHint;
import static android.R.attr.name;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Ashish on 31-08-2017.
 */

public class ItemOneFragment extends Fragment implements VerticalStepperForm {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewTaskAdapter mAdapter;
    View view;
    List<Task> taskList = new ArrayList<>();
    TextView tvPickUp, tvDropOff, tvStartDate, tvEndDate;
    int PLACE_PICKER_REQUEST = 1;
    int PLACE_PICKER_REQUEST2 = 2;
    String startHour, startMinute;
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
        String[] mySteps = {"Pickup Address", "Dropoff Address", "Start Date & Time", "End Date & Time","Delivery Details"};
        int colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);

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
            }
        }else if (requestCode == PLACE_PICKER_REQUEST2) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getName());
                dropOff.setText(place.getAddress());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void dateTimePicker(final EditText edtDate1) {
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

        }
        return view;
    }

    private View createDeliveryDetailsView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout deliveryLayout = (LinearLayout) inflater.inflate(R.layout.delivery_details, null, false);
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
            }
        });

        return startDateTime;
    }

    private View createDropoffAddress() {
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

    private View createPickUpAddress() {
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

        }
    }

    @Override
    public void sendData() {

    }
}
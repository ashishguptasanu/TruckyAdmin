package com.rstintl.docta.deliveryApp.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.rstintl.docta.deliveryApp.R;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class AddVehicle extends AppCompatActivity implements VerticalStepperForm{
    VerticalStepperFormLayout verticalStepperForm;
    EditText vehicleName, vehicleNumber, vehicleMaximumLoad, vehicleLastServiceDate, vehicleNextServiceDate, vehicleInsuranceEndDate;
    Spinner vehicleType;
    RadioGroup rgRefrigerated, rgPestControlled, rgWaterProof;
    RadioButton refrigerated, notRefregerated;
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
        vehicleNumber = new EditText(getApplicationContext());
        vehicleNumber.setHint("Enter Vehicle Registration Number");
        return vehicleNumber;
    }

    private View createVehicleNameStep() {
        vehicleName = new EditText(getApplicationContext());
        vehicleName.setHint("Enter Vehicle Name");
        return vehicleName;
    }
    private View createVehicleTypeStep() {
        vehicleType = new Spinner(getApplicationContext());

        return vehicleType;
    }

    private View createVehicleMaxLoadStep() {
        vehicleMaximumLoad = new EditText(getApplicationContext());
        vehicleMaximumLoad.setHint("Enter max. Load(In tons)");
        vehicleMaximumLoad.setInputType(InputType.TYPE_CLASS_NUMBER);
        return vehicleMaximumLoad;
    }

    private View createLastServiceDateStep() {
        vehicleLastServiceDate = new EditText(getApplicationContext());
        vehicleLastServiceDate.setFocusable(false);
        vehicleLastServiceDate.setHint("Last Service Date");
        return vehicleLastServiceDate;
    }

    private View createNextServiceStep() {
        vehicleNextServiceDate = new EditText(getApplicationContext());
        vehicleNextServiceDate.setHint("Next Service Date");
        vehicleNextServiceDate.setFocusable(false);
        return vehicleNextServiceDate;
    }

    private View createVehicleInsurenceEndStep() {
        vehicleInsuranceEndDate = new EditText(getApplicationContext());
        vehicleInsuranceEndDate.setHint("Insurance End Date");
        vehicleInsuranceEndDate.setFocusable(false);
        return vehicleInsuranceEndDate;
    }

    private View createVehicleRefrigratedStep() {
        rgRefrigerated = new RadioGroup(getApplicationContext());
        rgRefrigerated.addView(refrigerated);
        rgRefrigerated.addView(notRefregerated);
        return rgRefrigerated;
    }

    private View createVehiclePestControlStep() {
        return null;
    }

    private View createVehicleWaterProofStep() {
        return null;
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

    }
}

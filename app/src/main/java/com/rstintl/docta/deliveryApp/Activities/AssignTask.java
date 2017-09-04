package com.rstintl.docta.deliveryApp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rstintl.docta.deliveryApp.R;
public class AssignTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] city = new String[]{"Select One","New Delhi", "Gurgaon","Noida", "Gaziabaad", "Mumbai"};
    String[] area_newDelhi = new String[]{"Select One","Rajiv Chauk", "Rohini East", "Rohini West", "Dwarka Sector-32", "Hauz Khas"};
    String[] area_newGurgaon = new String[]{"Select One","Subhash Chauk", "Sushant Lok", "Sector-14", "Sector 32", "Sohna Road"};
    String[] area_newGaziabaad = new String[]{"Select One","Sector-16", "Sector-18", "Sector-19", "Sector-21", "Sector-25"};
    String[] area_mumbai = new String[]{"Select One","Navi Mumbai", "Andheri East", "Andheri West", "Vikhroli West", "Kandiwali East"};
    String[] area_noida = new String[]{"Select One","Pari Chauk", "Bambura Chauk", "Atta Market", "Sector-16", "Sector-14"};
    String[] delivery_agent = new String[]{"Select One", "Amit Kumar","Ashish Gupta", "Vinay", "Himanshu Singh", "Akash Yadav"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        populateCitySpinner();
    }

    private void populateAgentSpinner() {
        Spinner agentSpinner = (Spinner)findViewById(R.id.spinner_delivery_agent);
        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, delivery_agent);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agentSpinner.setOnItemSelectedListener(this);
        agentSpinner.setAdapter(gameKindArray);
    }

    private void populateAreaSpinner(String[] area) {
        Spinner areaSpinner = (Spinner)findViewById(R.id.spinner_area);
        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, area);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setOnItemSelectedListener(this);
        areaSpinner.setAdapter(gameKindArray);
    }

    private void populateCitySpinner() {
        Spinner citySpinner = (Spinner)findViewById(R.id.spinner_city);
        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, city);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setOnItemSelectedListener(this);
        citySpinner.setAdapter(gameKindArray);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spinner_city:
                if(i == 1){
                    populateAreaSpinner(area_newDelhi);
                }else if(i==2){
                    populateAreaSpinner(area_newGurgaon);
                }else if(i==3){
                    populateAreaSpinner(area_noida);
                }else if(i==4){
                    populateAreaSpinner(area_newGaziabaad);
                }else if(i==5){
                    populateAreaSpinner(area_mumbai);
                }
                break;
            case R.id.spinner_area:
                populateAgentSpinner();
                break;
            case R.id.spinner_delivery_agent:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

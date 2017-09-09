package com.rstintl.docta.deliveryApp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rstintl.docta.deliveryApp.Models.DriverInfo;
import com.rstintl.docta.deliveryApp.Models.InProgress;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ashish on 06-09-2017.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.MyViewHolder>  {
    private List<DriverInfo> driverList = new ArrayList<>();
    private Context context;
    public DriverAdapter(Context context, List<DriverInfo> driverList){
        this.context = context;
        this.driverList = driverList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver_view_type,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDriverName.setText(driverList.get(position).getDriverName());
        holder.tvvehicleType.setText("Vehicle Type: "+driverList.get(position).getDriverVehicleType());
        if(Objects.equals(driverList.get(position).getDriverDutyStatus(), "1")){
            holder.imgDutyStatus.setImageResource(R.drawable.online);
        }else{
            holder.imgDutyStatus.setImageResource(R.drawable.offline);
        }
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDriverName, tvvehicleType;
        ImageView imgDutyStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvDriverName = (TextView)itemView.findViewById(R.id.tv_driver_name);
            tvvehicleType = (TextView)itemView.findViewById(R.id.tv_driver_vehicle_type);
            imgDutyStatus = (ImageView)itemView.findViewById(R.id.img_duty_status);
        }
    }
}

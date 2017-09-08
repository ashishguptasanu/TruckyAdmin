package com.rstintl.docta.deliveryApp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rstintl.docta.deliveryApp.Models.VehicleList;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 08-09-2017.
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {
    private List<VehicleList> vehicleList = new ArrayList<>();
    private Context context;

    public VehicleAdapter(Context context, List<VehicleList> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_inventory, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvVehicleName.setText(vehicleList.get(position).getVehicleName());
        holder.tvvehicleType.setText("Vehicle Type: " + vehicleList.get(position).getVehicleType());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvVehicleName, tvvehicleType;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvVehicleName = (TextView) itemView.findViewById(R.id.tv_vehicle_name);
            tvvehicleType = (TextView) itemView.findViewById(R.id.tv_vehicle_type);
        }
    }
}

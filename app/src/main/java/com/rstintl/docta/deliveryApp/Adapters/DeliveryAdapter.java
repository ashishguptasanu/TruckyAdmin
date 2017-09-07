package com.rstintl.docta.deliveryApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rstintl.docta.deliveryApp.Activities.MapsActivity;
import com.rstintl.docta.deliveryApp.Models.AssignedTask;
import com.rstintl.docta.deliveryApp.Models.InProgress;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ashish on 01-09-2017.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {
    private List<AssignedTask> taskList = new ArrayList<>();
    private Context context;
    public DeliveryAdapter(Context context, List<AssignedTask> taskList){
        this.context = context;
        this.taskList = taskList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_progress,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(Objects.equals(taskList.get(position).getTaskStatus(), "1")){
            holder.tvStatus.setText("Assigned");
            holder.tvGoogleMap.setVisibility(View.VISIBLE);
        }else if(Objects.equals(taskList.get(position).getTaskStatus(), "2")){
            holder.tvStatus.setText("Out for delivery");
            holder.tvGoogleMap.setVisibility(View.VISIBLE);
        }
        holder.tvAssignedto.setText(taskList.get(position).getDriverName());
        holder.tvDateTime.setText(taskList.get(position).getTaskStartDatetime());
        holder.tvContact.setText(taskList.get(position).getTaskDeliveryPersonContact());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvStatus, tvAssignedto, tvDateTime, tvContact;
        ImageView tvGoogleMap;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvStatus = (TextView)itemView.findViewById(R.id.tv_status_progress);
            tvAssignedto = (TextView)itemView.findViewById(R.id.tv_assigned_to_progress);
            tvDateTime = (TextView)itemView.findViewById(R.id.tv_date_time_progress);
            tvContact = (TextView)itemView.findViewById(R.id.contact_progress);
            tvGoogleMap = (ImageView)itemView.findViewById(R.id.view_on_map);
            tvGoogleMap.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("lat1", taskList.get(getAdapterPosition()).getTaskPickupLatitude());
            intent.putExtra("lat2", taskList.get(getAdapterPosition()).getTaskDropoffLatitude());
            intent.putExtra("lang1", taskList.get(getAdapterPosition()).getTaskPickupLongitude());
            intent.putExtra("lang2", taskList.get(getAdapterPosition()).getTaskDropoffLongitude());
            intent.putExtra("driver_contact",taskList.get(getAdapterPosition()).getDriverContact());
            context.startActivity(intent);
        }
    }
}

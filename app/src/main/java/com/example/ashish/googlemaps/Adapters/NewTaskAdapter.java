package com.example.ashish.googlemaps.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashish.googlemaps.Activities.MapsActivity;
import com.example.ashish.googlemaps.Models.Task;
import com.example.ashish.googlemaps.R;

import java.util.ArrayList;
import java.util.List;

public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskAdapter.MyViewHolder>{
    private List<Task> taskList = new ArrayList<>();
    private Context context;
    public NewTaskAdapter(Context context, List<Task> taskList){
        this.context = context;
        this.taskList = taskList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDateTime, tvPickup, tvDropoff, tvStatus;
        CardView cardViewTask;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvDateTime = (TextView)itemView.findViewById(R.id.tv_date_time);
            tvPickup = (TextView)itemView.findViewById(R.id.tv_pickup);
            tvDropoff = (TextView)itemView.findViewById(R.id.tv_dropoff);
            tvStatus = (TextView)itemView.findViewById(R.id.tv_status);
            cardViewTask = (CardView)itemView.findViewById(R.id.cardview_task);
            cardViewTask.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("date",taskList.get(getAdapterPosition()).deliveryDate);
            context.startActivity(intent);
        }
    }
    @Override
    public NewTaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewTaskAdapter.MyViewHolder holder, int position) {
        holder.tvDateTime.setText(taskList.get(position).getDeliveryDate() + " " + taskList.get(position).getDeliveryTime());
        holder.tvPickup.setText("Pickup From: "+ taskList.get(position).pickUpLocation);
        holder.tvDropoff.setText("Drop Off: "+ taskList.get(position).dropOffLocation);
        holder.tvStatus.setText(taskList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


}

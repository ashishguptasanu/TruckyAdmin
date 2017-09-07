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
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ashish on 07-09-2017.
 */

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.MyViewHolder> {
private List<AssignedTask> taskList = new ArrayList<>();
private Context context;
public CompletedAdapter(Context context, List<AssignedTask> taskList){
        this.context = context;
        this.taskList = taskList;
        }
@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_completed,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
        }

@Override
public void onBindViewHolder(MyViewHolder holder, int position) {

    holder.tvDeliveredBy.setText(taskList.get(position).getDriverName());
    holder.tvDeliveredTo.setText(taskList.get(position).getTaskDeliveryPersonName());
    holder.tvDeliveredOn.setText(taskList.get(position).getTaskEndDatetime());
}

@Override
public int getItemCount() {
        return taskList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tvDeliveredBy, tvDeliveredTo, tvDeliveredOn;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvDeliveredBy = (TextView)itemView.findViewById(R.id.tv_delivered_by_completed);
        tvDeliveredTo = (TextView)itemView.findViewById(R.id.tv_delivered_to_completed);
        tvDeliveredOn = (TextView)itemView.findViewById(R.id.tv_delivered_on);

    }
}
}


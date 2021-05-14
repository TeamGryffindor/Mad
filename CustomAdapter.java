package com.example.madproject2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList employee_id, employee_name, employee_dob, employee_address;

    CustomAdapter(Activity activity, Context context, ArrayList employee_id, ArrayList employee_name, ArrayList employee_dob, ArrayList employee_address){
        this.activity = activity;
        this.context = context;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_dob = employee_dob;
        this.employee_address = employee_address;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.employee_id_txt.setText(String.valueOf(employee_id.get(position)));
        holder.employee_name_txt.setText(String.valueOf(employee_name.get(position)));
        holder.employee_dob_txt.setText(String.valueOf(employee_dob.get(position)));
        holder.employee_address_txt.setText(String.valueOf(employee_address.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(employee_id.get(position)));
                intent.putExtra("name", String.valueOf(employee_name.get(position)));
                intent.putExtra("dob", String.valueOf(employee_dob.get(position)));
                intent.putExtra("address", String.valueOf(employee_address.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return employee_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView employee_id_txt, employee_name_txt, employee_dob_txt, employee_address_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            employee_id_txt = itemView.findViewById(R.id.employee_id_txt);
            employee_name_txt = itemView.findViewById(R.id.employee_name_txt);
            employee_dob_txt = itemView.findViewById(R.id.employee_dob_txt);
            employee_address_txt = itemView.findViewById(R.id.employee_address_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}

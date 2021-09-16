package com.example.app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Devices_Adaptor extends RecyclerView.Adapter<Devices_Adaptor.My_view_holder> {
    ArrayList Device_name ;
    ArrayList Device_ip ;
    ArrayList Device_mac ;




    Context context;

       Devices_Adaptor(ArrayList Device_name ,
                       ArrayList Device_ip ,
                       ArrayList Device_mac ,
                       Context context){

                      this.context = context;
                      this.Device_mac = Device_mac ;
                      this.Device_ip = Device_ip;
                      this.Device_name = Device_name; }


    @NonNull
    @Override
    public Devices_Adaptor.My_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_work,parent,false);


        return new My_view_holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Devices_Adaptor.My_view_holder holder, int position) {
        holder.device_name.setText(String.valueOf(Device_name.get(position)));
        holder.device_mac.setText(String.valueOf("MAC Address: "+Device_mac.get(position)));
        holder.device_ip.setText(String.valueOf("IP Address"+Device_ip.get(position)));


    }

    @Override
    public int getItemCount() {
        return Device_name.size() ;
    }




    public class My_view_holder extends RecyclerView.ViewHolder {
            TextView device_name , device_ip ,device_mac;




        public My_view_holder(@NonNull View itemView) {
            super(itemView);
            device_name = itemView.findViewById(R.id.device_name);
            device_ip = itemView.findViewById(R.id.ip_add_devices);
            device_mac = itemView.findViewById(R.id.mac_add_devices);

        }
    }
}

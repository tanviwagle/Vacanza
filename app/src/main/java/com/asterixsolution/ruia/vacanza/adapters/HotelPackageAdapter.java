package com.asterixsolution.ruia.vacanza.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.models.HotelModel;

import java.util.List;

/**
 * Created by Wagle on 06-02-2018.
 */

public class HotelPackageAdapter extends RecyclerView.Adapter<HotelPackageAdapter.MyViewHolder>
{

    public interface onClickListener {
        void onButtonClicked(View v, int position, String hotelid);
    }

    private onClickListener listener = null;

    List<HotelModel> list;

    public HotelPackageAdapter(List<HotelModel> list,HotelPackageAdapter.onClickListener listener) {
        this.list= list;
        this.listener = listener;
    }

    @Override
    public HotelPackageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_package_hotel,parent,false);
        return new HotelPackageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotelPackageAdapter.MyViewHolder holder, int position) {
        HotelModel hotelModel = list.get(position);
        holder.Name.setText(hotelModel.getName());
        holder.Address.setText(hotelModel.getAddress());
        holder.Contact.setText(hotelModel.getContact());
        holder.Type.setText(hotelModel.getType());
        holder.Cost.setText(hotelModel.getCost());
        holder.Email.setText(hotelModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name, Address, Contact, Type, Cost, Email;
        Button btnShow;

        public MyViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Address = itemView.findViewById(R.id.Address);
            Contact = itemView.findViewById(R.id.Contact);
            Type = itemView.findViewById(R.id.type);
            Cost = itemView.findViewById(R.id.Cost);
            Email = itemView.findViewById(R.id.Email);
            btnShow = itemView.findViewById(R.id.Select);

            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClicked(view,getAdapterPosition(),list.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
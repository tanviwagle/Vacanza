package com.asterixsolution.ruia.vacanza.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.models.SightModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wagle on 01-02-2018.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.MyViewHolder>
{
    List<PackageModel> list;
    Context con;

    private onClickListener listener = null;

    public PackageAdapter(List<PackageModel> list, Context con, onClickListener listener)
    {
        this.list = list;
        this.con=con;
        this.listener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_package_details,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PackageModel packageModel = list.get(position);
        holder.Duration.setText(packageModel.getDuration()+" days");
        holder.Cost.setText(packageModel.getCost()+" per person");
        ArrayList<SightModel> sightList = packageModel.getSight();
        ArrayList<String> sights = new ArrayList();
        for (SightModel sight :sightList) {
            sights.add(sight.getSightName());
        }
        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(holder.itemView.getContext(),R.layout.support_simple_spinner_dropdown_item,sights);
        holder.listView.setAdapter(listadapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Duration,Cost;
        ListView listView;
        Button btnShow;
        public MyViewHolder(final View itemView) {
            super(itemView);
            Duration = itemView.findViewById(R.id.Duration);
            Cost = itemView.findViewById(R.id.Cost);
            listView = itemView.findViewById(R.id.list);
            btnShow = itemView.findViewById(R.id.book);
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClicked(view, getAdapterPosition(),list.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onClickListener {
        void onButtonClicked(View v, int position, String packageId);
    }

}

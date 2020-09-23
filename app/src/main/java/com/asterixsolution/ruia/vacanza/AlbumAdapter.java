package com.asterixsolution.ruia.vacanza;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder>
{
    List<PackageModel> list;
    Context context;

    public AlbumAdapter(List<PackageModel> list, Context context)
    {
        this.list = list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tvName.setText(list.get(position).getPackageName());
        Log.e("ImageUrl : ", RetrofitUtils.BASE_URL + list.get(position).getImageUrl());
        Picasso.with(context)
                .load(RetrofitUtils.BASE_URL + list.get(position).getImageUrl())
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        ImageView imgCover;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            imgCover = itemView.findViewById(R.id.imgCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context,PackageDetailActivity.class));
                }
            });
        }


    }
}

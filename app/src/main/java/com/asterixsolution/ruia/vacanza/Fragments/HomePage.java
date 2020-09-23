package com.asterixsolution.ruia.vacanza.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.adapters.AlbumAdapter;
import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends Fragment{

    public HomePage()
    {
    }

    ImageView iv;
    RecyclerView recycle;
    AlbumAdapter adapter;
    List<PackageModel> list = new ArrayList<>();
    Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        iv=getActivity().findViewById(R.id.imgCover);
        recycle=getActivity().findViewById(R.id.rv);
        RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();

        Call<List<PackageModel>> call = service.getData();
        call.enqueue(new Callback<List<PackageModel>>()
        {

            @Override
            public void onResponse(Call<List<PackageModel>> call, Response<List<PackageModel>> response)
            {
                list = response.body();
                setList(list,context);
            }

            @Override
            public void onFailure(Call<List<PackageModel>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private void setList(List<PackageModel> list, Context context) {
        adapter=new AlbumAdapter(list,context);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle.setAdapter(adapter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page,container,false);

    }
}

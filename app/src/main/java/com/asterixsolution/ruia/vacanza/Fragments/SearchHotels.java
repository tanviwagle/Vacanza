package com.asterixsolution.ruia.vacanza.Fragments;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.asterixsolution.ruia.vacanza.Activities.Login;
import com.asterixsolution.ruia.vacanza.Activities.NavigationDrawer;
import com.asterixsolution.ruia.vacanza.Activities.PackageDetailActivity;
import com.asterixsolution.ruia.vacanza.Activities.PackageHotel;
import com.asterixsolution.ruia.vacanza.Activities.Splashscreen;
import com.asterixsolution.ruia.vacanza.PreferenceManager;
import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.adapters.HotelAdapter;
import com.asterixsolution.ruia.vacanza.adapters.PackageAdapter;
import com.asterixsolution.ruia.vacanza.models.HotelModel;
import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.models.PlaceModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchHotels extends Fragment implements HotelAdapter.onClickListener
{
    Spinner place;
    RecyclerView rvHotel;
    HotelAdapter adapter;
    String packageID;
    String userId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        place=getActivity().findViewById(R.id.spnPlace);
        rvHotel = getActivity().findViewById(R.id.rvHotel);

        packageID = getActivity().getIntent().getStringExtra("packageId");
        userId = new PreferenceManager(getActivity()).getUserId();

        RetrofitUtils.RetrofitService service= RetrofitUtils.getClient();
        service.names().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lst = response.body();
                ArrayAdapter adapt = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, lst);
                place.setAdapter(adapt);

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("Onfailure",t.toString());
            }
        });


        place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = place.getSelectedItem().toString();
                Log.e( "onItemSelected: ", name);

                RetrofitUtils.RetrofitService service1=RetrofitUtils.getClient();
                service1.getHotelDet(name).enqueue(new Callback<List<HotelModel>>() {
                    @Override
                    public void onResponse(Call<List<HotelModel>> call, Response<List<HotelModel>> response) {
                        adapter = new HotelAdapter(response.body(),SearchHotels.this);
                        rvHotel.setLayoutManager(new LinearLayoutManager(SearchHotels.this.getActivity()));
                        rvHotel.setAdapter(adapter);
                        Log.e("Error",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<HotelModel>> call, Throwable t) {
                        Log.e("SearchHotel","OnFailure",t);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_hotels, container, false);
    }

    @Override
    public void onButtonClicked(View v, int position, String hotelId) {
        RetrofitUtils.getClient().addRequest(packageID,hotelId, userId).enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("OnResponse","Response" + response);
                String res = response.body();
                if(res.equals("0")){
                    Toast.makeText(getActivity(), "Request could not be placed", Toast.LENGTH_SHORT).show();
                }else{
                    getActivity().setContentView(R.layout.splashcreen__request);
                    ImageView iv=getActivity().findViewById(R.id.iv);
                    Animation an_iv= AnimationUtils.loadAnimation(getActivity(),R.anim.request);
                    iv.startAnimation(an_iv);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent i =new Intent(getActivity(),NavigationDrawer.class);
                            startActivity(i);
                            getActivity().finish();
                        }
                    },3000);

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("PackageHotel","OnFailure",t);
            }
        });

    }


}

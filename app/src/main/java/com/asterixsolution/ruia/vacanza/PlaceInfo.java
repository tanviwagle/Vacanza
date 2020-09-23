package com.asterixsolution.ruia.vacanza;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceInfo extends Fragment
{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Spinner place;
        final TextView desc;

        place=getActivity().findViewById(R.id.spnPlace);
        desc=getActivity().findViewById(R.id.Desc);

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
                Call<String> call = service1.info(name);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String descr=response.body().toString();
                        Log.e("onResponse: ",descr );
                        desc.setText(descr);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("OnFailure", t.toString());
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

        return inflater.inflate(R.layout.fragment_place_info, container, false);

    }


}

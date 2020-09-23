package com.asterixsolution.ruia.vacanza;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.asterixsolution.ruia.vacanza.adapters.PackageAdapter;
import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageDetailActivity extends AppCompatActivity {
    RecyclerView rvPackage;
    PackageAdapter adapter;
    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);

        con = getApplicationContext();
        getSupportActionBar().setTitle("Package Details");
        rvPackage = findViewById(R.id.rvPackage);
        String placeId = getIntent().getStringExtra("placeid");
        Log.e("PlaceId",placeId + "");
        RetrofitUtils.RetrofitService service= RetrofitUtils.getClient();
        service.getPackDet(placeId).enqueue(new Callback<List<PackageModel>>() {
            @Override
            public void onResponse(Call<List<PackageModel>> call, Response<List<PackageModel>> response) {
                adapter = new PackageAdapter(response.body(), con, (PackageAdapter.onClickListener) PackageDetailActivity.this);
                rvPackage.setLayoutManager(new LinearLayoutManager(PackageDetailActivity.this));
                rvPackage.setAdapter(adapter);
                Log.e("Error",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<PackageModel>> call, Throwable t) {
                Log.e("PackageDetailActivity","OnFailure",t);
            }
        });
    }
}

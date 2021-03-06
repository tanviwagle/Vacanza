package com.asterixsolution.ruia.vacanza.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.asterixsolution.ruia.vacanza.Fragments.HomePage;
import com.asterixsolution.ruia.vacanza.PreferenceManager;
import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.adapters.HotelPackageAdapter;
import com.asterixsolution.ruia.vacanza.models.HotelModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageHotel extends AppCompatActivity implements HotelPackageAdapter.onClickListener {

    RecyclerView rvPackHotel;
    HotelPackageAdapter adapter;
    Button btnBook;
    String packageID;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_hotel);

        getSupportActionBar().setTitle("Choose hotel");

        packageID = getIntent().getStringExtra("packageId");
        userId = new PreferenceManager(this).getUserId();
        rvPackHotel = findViewById(R.id.rvPackHotel);
        btnBook = findViewById(R.id.book);
        final String placeId = getIntent().getStringExtra("placeid");
        Log.e("PlaceId Hotel", placeId + "");
        RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();
        service.Details(placeId).enqueue(new Callback<List<HotelModel>>() {
            @Override
            public void onResponse(Call<List<HotelModel>> call, Response<List<HotelModel>> response) {
                adapter = new HotelPackageAdapter(response.body(),PackageHotel.this);
                rvPackHotel.setLayoutManager(new LinearLayoutManager(PackageHotel.this));
                rvPackHotel.setAdapter(adapter);
                Log.e("Error",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<HotelModel>> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }

    @Override
    public void onButtonClicked(View v, int position, String hotelId) {
        RetrofitUtils.getClient().addRequest(packageID,hotelId, userId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("OnResponse","Response" + response);
                String res = response.body();
                if(res.equals("0")){
                    Toast.makeText(PackageHotel.this, "Request could not be placed", Toast.LENGTH_SHORT).show();
                }else{
                    setContentView(R.layout.splashcreen__request);
                    getSupportActionBar().setTitle("Request");
                    ImageView iv=findViewById(R.id.iv);
                    Animation an_iv= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.request);
                    iv.startAnimation(an_iv);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            finish();
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

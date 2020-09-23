package com.asterixsolution.ruia.vacanza.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asterixsolution.ruia.vacanza.PreferenceManager;
import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends Fragment {

    EditText email,feedback;
    Button sendBtn;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        email=getActivity().findViewById(R.id.Email);
        feedback=getActivity().findViewById(R.id.Feedback);
        sendBtn=getActivity().findViewById(R.id.send);

        email.setEnabled(false);
        final PreferenceManager pref = new PreferenceManager(this.getContext());
        final String mail=pref.getEmail();
        email.setText(mail);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();

                Call<String> call = service.sendFeedback(mail, feedback.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String success = response.body().toString();
                        if (success.equals("1")) {
                            Toast.makeText(getActivity(), "Feedback sent", Toast.LENGTH_LONG).show();
                            feedback.setText("");
                        }
                        else if(success.equals("0"))
                        {
                            Toast.makeText(getActivity(), "Blank feedback cannot be sent", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Feedback sending failed", Toast.LENGTH_LONG).show();
                            feedback.setText("");
                        }
                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Error", t.toString());
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }
}

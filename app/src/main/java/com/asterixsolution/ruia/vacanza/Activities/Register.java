package com.asterixsolution.ruia.vacanza.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText Email,FName,LName,Pwd,CPwd;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        Email=findViewById(R.id.etEmail);
        FName=findViewById(R.id.etFName);
        LName=findViewById(R.id.etLName);
        Pwd=findViewById(R.id.etPassword);
        CPwd=findViewById(R.id.etCPassword);
        Register=findViewById(R.id.btnRegister);
        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();

                Call<String> call = service.RegisterInsertData(Email.getText().toString(),
                        FName.getText().toString(),LName.getText().toString(),Pwd.getText().toString(),CPwd.getText().toString());
                call.enqueue(new Callback<String>()
                {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response)
                    {
                        String success=response.body().toString();
                        if (success.equals("1")) {
                            Intent i = new Intent(Register.this, Login.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                        }
                        else if(success.equals("0"))
                        {
                            Toast.makeText(Register.this, "Password doesn't match.", Toast.LENGTH_LONG).show();
                            Pwd.setText("");
                            CPwd.setText("");
                        }
                        else if(success.equals("2"))
                        {
                            Toast.makeText(Register.this, "Enter all blank fields", Toast.LENGTH_LONG).show();
                        }
                        else if(success.equals("3"))
                        {
                            Toast.makeText(Register.this, "Enter email in correct format", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Error", t.toString());
                        Toast.makeText(Register.this, "Onfailure", Toast.LENGTH_LONG).show();

                    }


                });
            }
        });
    }

}

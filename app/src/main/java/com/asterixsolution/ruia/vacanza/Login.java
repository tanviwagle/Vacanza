package com.asterixsolution.ruia.vacanza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asterixsolution.ruia.vacanza.models.UserModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView tv;
    EditText Email,Pwd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        //For underlining the text
        tv=findViewById(R.id.notUser);
        SpannableString text = new SpannableString("Not a user? Create account");
        text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        tv.setText(text);

        //For login page to register transition
        //Back button will not bring to login page
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Register.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        Email=findViewById(R.id.etEmail);
        Pwd=findViewById(R.id.etPwd);

        login=findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();

                Call<UserModel> call = service.LoginGetData(Email.getText().toString(),Pwd.getText().toString());
                call.enqueue(new Callback<UserModel>()
                {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response)
                    {
                        Log.e("RESPONSE",response.body().toString());
                        UserModel user = response.body();
                        if (!user.getId().equals("")) {
                            PreferenceManager pref= new PreferenceManager(Login.this);
                            pref.setEmail(user.getEmail());
                            pref.setName(user.getName());
                            pref.setUserId(user.getId());
                            Intent i = new Intent(Login.this, NavigationDrawer.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            i.putExtra("NAME",user.getName());
                            startActivity(i);

                        }else{
                            Toast.makeText(Login.this, "Login Failed. Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Log.e("Error", t.toString());
                        Toast.makeText(Login.this, "No Network. Try later", Toast.LENGTH_LONG).show();
                        Email.setText("");
                        Pwd.setText("");
                    }


                });
            }
        });

    }

}

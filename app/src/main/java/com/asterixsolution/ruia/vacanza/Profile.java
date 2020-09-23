package com.asterixsolution.ruia.vacanza;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.asterixsolution.ruia.vacanza.models.UserModel;
import com.asterixsolution.ruia.vacanza.retrofit.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    EditText FName,LName,Email,Pwd,DialogPwd,DialogCfmPwd,DialogCrtPwd;
    ImageButton FBtn,LBtn,EmailBtn,PwdBtn;
    Button SaveBtn,SubmitBtn,CancelBtn,SaveDetBtn;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setFinishOnTouchOutside(true);
        getSupportActionBar().setTitle("Profile");

        FName=findViewById(R.id.FName);
        LName=findViewById(R.id.LName);
        Email=findViewById(R.id.Email);
        Pwd=findViewById(R.id.etPwd);

        FName.setEnabled(false);
        LName.setEnabled(false);
        Email.setEnabled(false);
        Pwd.setEnabled(false);

        final PreferenceManager pref = new PreferenceManager(this);
        FName.setText(pref.getName().split(" ")[0]);
        LName.setText(pref.getName().split(" ")[1]);
        Email.setText(pref.getEmail());

        FBtn=findViewById(R.id.FNEdit);
        LBtn=findViewById(R.id.LNEdit);
        EmailBtn=findViewById(R.id.EmailEdit);
        PwdBtn=findViewById(R.id.PwdEdit);
        SaveDetBtn=findViewById(R.id.SaveDetail);


        FBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FName.setEnabled(true);
            }
        });

        LBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LName.setEnabled(true);
            }
        });

        EmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setEnabled(true);
            }
        });

        PwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog1 = new Dialog(Profile.this);
                dialog1.setContentView(R.layout.profile_pwd_dialog);
                dialog1.setTitle("Current Password");
                DialogCrtPwd=dialog1.findViewById(R.id.etCrtPwd);
                dialog1.show();

                window=dialog1.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                SubmitBtn=dialog1.findViewById(R.id.Submit);
                SubmitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();
                        Call<String> call = service.PasswordData(DialogCrtPwd.getText().toString());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String success = response.body().toString();
                                if (success.equals("1"))
                                {
                                    dialog1.dismiss();

                                    final Dialog dialog = new Dialog(Profile.this);

                                    dialog.setContentView(R.layout.activity_profile_dialog);
                                    dialog.setTitle("Change Password");
                                    dialog.show();
                                    window=dialog.getWindow();
                                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                                    DialogPwd=dialog.findViewById(R.id.etNewPwd);
                                    DialogCfmPwd=dialog.findViewById(R.id.etCPwd);
                                    SaveBtn=dialog.findViewById(R.id.Save);
                                    CancelBtn=dialog.findViewById(R.id.Cancel);
                                    SaveBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            if (DialogPwd.getText().toString().matches(DialogCfmPwd.getText().toString()))
                                            {
                                                Pwd.setText(DialogCfmPwd.getText());
                                                dialog.dismiss();
                                            }
                                            else
                                            {
                                                DialogCfmPwd.setText("");
                                                DialogPwd.setText("");
                                                Toast.makeText(Profile.this, "Password doesn't match", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                                    CancelBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                            dialog1.dismiss();
                                            Pwd.setEnabled(false);
                                        }
                                    });
                                }
                                else if (success.equals("0"))
                                {
                                    Toast.makeText(Profile.this, "Wrong Password.", Toast.LENGTH_LONG).show();
                                    DialogCrtPwd.setText("");
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
        });

        SaveDetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pref.getUserId().equals("")) {
                    RetrofitUtils.RetrofitService service = RetrofitUtils.getClient();
                    Call<String> call = service.ProfileDetails(pref.getUserId(),Email.getText().toString(), FName.getText().toString(), LName.getText().toString(), Pwd.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String success = response.body().toString();
                            if (success.equals("1")) {
                                Toast.makeText(Profile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Profile.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("Profile", t.toString());
                        }
                    });
                }
                else
                {
                    Toast.makeText(Profile.this, "else toast", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.asterixsolution.ruia.vacanza;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);

        TextView tv=findViewById(R.id.tv);
        TextView tv1=findViewById(R.id.tv1);
        ImageView iv=findViewById(R.id.iv);

        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(),R.font.font1);
        tv.setTypeface(typeface);

        Typeface typeface1 = ResourcesCompat.getFont(getApplicationContext(),R.font.font);
        tv1.setTypeface(typeface1);

        Animation an=AnimationUtils.loadAnimation(this,R.anim.alpha);
        tv.startAnimation(an);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i =new Intent(MainActivity.this,Login.class  );
                startActivity(i);
                finish();
            }
        },5000);

        Animation an1=AnimationUtils.loadAnimation(this,R.anim.anim_for_tv);
        tv1.startAnimation(an1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i =new Intent(MainActivity.this,Login.class  );
                startActivity(i);
                finish();
            }
        },5000);

        Animation an_iv=AnimationUtils.loadAnimation(this,R.anim.anim_for_iv);
        iv.startAnimation(an_iv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i =new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}

package com.example.arun.fingerprintauthentication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    public  ImageView iv_food,iv_work,iv_fashion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb_home);
        iv_food=(ImageView)findViewById(R.id.iv_2);
        iv_work=(ImageView)findViewById(R.id.iv_1);
        iv_fashion=(ImageView)findViewById(R.id.iv_3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_dehaze_white_24dp);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setImage(iv_fashion,"fashion");
        setImage(iv_work,"work");
        setImage(iv_food,"food");

    }

    public void setImage(ImageView iv,String image){

        String uri = "@drawable/"+image;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);

    }

}

package com.urlshortenerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.net.NetworkInfo;

public class Splash extends Activity implements Animation.AnimationListener {

    // Animation
    Animation animFadein;
    Button img;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img=(Button) findViewById(R.id.image);
        checkInternetConenction();

    }
    /*********************Check Internet connection ***************************/
    private boolean checkInternetConenction() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                ||

                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            /****** Create Thread that will sleep for 5 seconds *************/
            StartAnimations();
            return true;
        } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            InternetConnectionerror();
            return false;
        }
        return false;
    }

    /************************************************
     * Hardware Back Button
     *******************************************************/
    public void InternetConnectionerror() {
        AlertDialog alert = new AlertDialog.Builder(Splash.this)
                .setTitle("Warning.")
                .setMessage("Check Your Internet Connection ?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                }).create();

        alert.show();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public void StartAnimations() {
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        img.setVisibility(View.VISIBLE);
        // start the animation
        img.startAnimation(animFadein);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.open_next, R.anim.close_next);
                finish();
            }
        },5000);

    }




}
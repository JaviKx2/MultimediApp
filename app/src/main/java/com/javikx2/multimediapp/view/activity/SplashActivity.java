package com.javikx2.multimediapp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.AuthDataHolder;
import com.javikx2.multimediapp.api.tviso.TvisoAPIClient;
import com.javikx2.multimediapp.api.tviso.dto.authtoken.AuthTokenResponse;
import com.javikx2.multimediapp.api.tviso.exception.TvisoApiClientException;
import com.javikx2.multimediapp.view.navigation.Navigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    private final Navigator navigator = Navigator.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean[] internetConnection = isThereInternet();
        if (!internetConnection[0] && !internetConnection[1]) { // Si no hay Wi-Fi o Datos
            showNoWifiAlertDialog();
        } else {
            IntentLauncher mLauncher = new IntentLauncher();
            mLauncher.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void showNoWifiAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.splash_no_internet_title)
                .setMessage(R.string.splash_no_internet_msg)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                openWifiSettings();
                            }
                        })
                .create().show();

    }

    private void openWifiSettings() {
        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    private boolean[] isThereInternet() {
        boolean wifiConnected = false;
        boolean mobileDataConnected = false;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                wifiConnected = true;
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                mobileDataConnected = true;
            }
        }
        return new boolean[]{wifiConnected, mobileDataConnected};
    }

    private class IntentLauncher extends Thread {

        @Override
        public void run() {
            getApiToken();
        }

        private void getApiToken() {
            try {
                TvisoAPIClient.getInstance().getAuthToken(new Callback<AuthTokenResponse>() {
                    @Override
                    public void onResponse(Call<AuthTokenResponse> call, Response<AuthTokenResponse> response) {
                        if (response.body().getError() == 0) {
                            AuthDataHolder.getInstance().setAuthToken(response.body().getAuthToken());
                            navigator.navigateToNews(SplashActivity.this);
                            //sleep();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthTokenResponse> call, Throwable t) {
                    }
                });
            } catch (TvisoApiClientException e) {
                e.printStackTrace();
            }

        }

        private void sleep(){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

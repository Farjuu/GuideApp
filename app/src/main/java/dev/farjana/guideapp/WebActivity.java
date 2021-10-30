package dev.farjana.guideapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.SafeBrowsingResponseCompat;
import androidx.webkit.WebViewClientCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import java.util.concurrent.TimeUnit;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    WebSettings webSettings;
    boolean safeBrowsingIsInitialized;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);



        Intent receiver = getIntent();
        String URL = receiver.getStringExtra("url");

        webView = findViewById(R.id.webView);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebviewController());
        safeBrowsingIsInitialized = false;

        if (WebViewFeature.isFeatureSupported(WebViewFeature.START_SAFE_BROWSING)) {
            WebViewCompat.startSafeBrowsing(this, success -> {
                safeBrowsingIsInitialized = true;
                if (!success) {
                    Log.e("MY_APP_TAG", "Unable to initialize Safe Browsing!");
                }
            });
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack()){
            this.webView.goBack();
            return true;
        }else{

            return super.onKeyDown(keyCode, event);
        }

    }


    private static class WebviewController extends WebViewClientCompat {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(@NonNull WebView view, @NonNull WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onSafeBrowsingHit(@NonNull WebView view, @NonNull WebResourceRequest request,
                                      int threatType, @NonNull SafeBrowsingResponseCompat callback) {
            // The "true" argument indicates that your app reports incidents like
            // this one to Safe Browsing.
            if (WebViewFeature.isFeatureSupported(WebViewFeature.SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY)) {
                callback.backToSafety(true);
                Toast.makeText(view.getContext(), "Unsafe web page blocked.",
                        Toast.LENGTH_LONG).show();
            }
        }


    }


}
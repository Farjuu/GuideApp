package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import java.util.concurrent.TimeUnit;


public class SecondActivity extends AppCompatActivity {

    private MaxAdView optionMrecAd, bannerSnd;
    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;
    Button playQuizBtn, freeGuideBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
            }
        });

        initializeAll();
        showAds();
        onClickMethod();

    }

    private void onClickMethod() {

        playQuizBtn.setOnClickListener(playQuizView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(SecondActivity.this, PlayQuizUrlAct.class);
            startActivity(intent);

        });

        //when share button clicks
        freeGuideBtn.setOnClickListener(guideView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(SecondActivity.this, OptionsAct.class);
            startActivity(intent);
        });
    }


    private void showAds() {

        //show banners

        bannerSnd.loadAd();
        bannerSnd.setVisibility(View.VISIBLE);
        bannerSnd.startAutoRefresh();
        bannerSnd.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });


       // show mrec ads

        optionMrecAd.loadAd();
        optionMrecAd.setVisibility(View.VISIBLE);
        optionMrecAd.startAutoRefresh();
        optionMrecAd.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });


        //show interstitials

        interstitialAd = new MaxInterstitialAd(getString(R.string.interstitial_id), this);
        interstitialAd.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdLoaded(final MaxAd maxAd) {
                // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

                // Reset retry attempt
                retryAttempt = 0;

            }

            @Override
            public void onAdLoadFailed(final String adUnitId, final MaxError error) {
                // Interstitial ad failed to load
                // We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)

                retryAttempt++;
                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        interstitialAd.loadAd();
                    }
                }, delayMillis);
            }

            @Override
            public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
                // Interstitial ad failed to display. We recommend loading the next ad
                interstitialAd.loadAd();
            }

            @Override
            public void onAdDisplayed(final MaxAd maxAd) {
            }

            @Override
            public void onAdClicked(final MaxAd maxAd) {
            }

            @Override
            public void onAdHidden(final MaxAd maxAd) {
                // Interstitial ad is hidden. Pre-load the next ad
                interstitialAd.loadAd();
            }

            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }
        });

        interstitialAd.loadAd();


    }

    private void initializeAll() {

        bannerSnd = findViewById(R.id.banner_Snd);
        optionMrecAd = findViewById(R.id.optionMrecSnd);
        playQuizBtn = findViewById(R.id.playQuizSnd);
        freeGuideBtn = findViewById(R.id.freeGuideSnd);
    }
}
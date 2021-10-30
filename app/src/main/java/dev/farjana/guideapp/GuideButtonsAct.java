package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class GuideButtonsAct extends AppCompatActivity {

    Button guideOne, guideTwo, guideThree, guideFour, guideFive, guideSix, guideSeven;

    private int retryAttempt;

    private MaxAdView banner_guide, MrecGuide;
    private MaxInterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_buttons);

        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
            }
        });
        initilizeAll();
        showAds();
        bannerAd();
        onClickBtn();

    }

    private void bannerAd() {

        banner_guide.loadAd();
        banner_guide.setVisibility(View.VISIBLE);
        banner_guide.startAutoRefresh();
        banner_guide.setListener(new MaxAdViewAdListener() {
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

    }

    private void showAds() {


        MrecGuide.loadAd();
        MrecGuide.setVisibility(View.VISIBLE);
        MrecGuide.startAutoRefresh();
        MrecGuide.setListener(new MaxAdViewAdListener() {
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

    private void onClickBtn() {

        guideOne.setOnClickListener(guideOneView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 1);
            startActivity(intent);

        });

        guideTwo.setOnClickListener(guideTwoView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 2);
            startActivity(intent);

        });

        guideThree.setOnClickListener(guideThreeView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 3);
            startActivity(intent);

        });

        guideFour.setOnClickListener(guideFourView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 4);
            startActivity(intent);

        });


        guideFive.setOnClickListener(guideFiveView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 5);
            startActivity(intent);

        });


        guideSix.setOnClickListener(guideSixView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 6);
            startActivity(intent);

        });


        guideSeven.setOnClickListener(guideSevenView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(GuideButtonsAct.this, GuideStringDataAct.class);
            intent.putExtra("btnNum", 7);
            startActivity(intent);

        });


    }


    private void initilizeAll() {

        banner_guide = findViewById(R.id.banner_guide);

        MrecGuide = findViewById(R.id.MrecGuide);

        guideOne = findViewById(R.id.button);
        guideTwo = findViewById(R.id.button2);
        guideThree = findViewById(R.id.button3);
        guideFour = findViewById(R.id.button4);
        guideFive = findViewById(R.id.button5);
        guideSix = findViewById(R.id.button6);
        guideSeven = findViewById(R.id.button7);

    }
}
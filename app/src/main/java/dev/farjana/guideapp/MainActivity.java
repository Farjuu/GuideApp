package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MaxAdView mrecadview;
    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;

    ConstraintLayout startApp, share, rate, privacy;
    List<Item> itemList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //applovin initialization
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
            }
        });

        initializeAll();
        showAds();
        buttonOnCLick();
        createRecyclerData();


    }

    private void buttonOnCLick() {

        //when start button clicks
        startApp.setOnClickListener(startView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        });

        //when share button clicks
        share.setOnClickListener(shareView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_Url));
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        //when rate button clicks
        rate.setOnClickListener(rateView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.rateUrl)));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        //when privacy button clicks
        privacy.setOnClickListener(privacyView -> {

            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
            }

            Intent intent = new Intent(MainActivity.this, PrivacyAct.class);
            startActivity(intent);

        });
    }

    private void showAds() {


        mrecadview.loadAd();

        mrecadview.setVisibility(View.VISIBLE);
        mrecadview.startAutoRefresh();
        mrecadview.setListener(new MaxAdViewAdListener() {
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

    private void createRecyclerData() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);


        FirebaseDataHolder firebaseDataHolder = new FirebaseDataHolder();

        recyclerAdapter = new RecyclerAdapter(firebaseDataHolder.getOptions());

        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initializeAll() {

        mrecadview = findViewById(R.id.mrecadview);
        recyclerView = findViewById(R.id.recyclerView);
        startApp = findViewById(R.id.startAppLayout);
        share = findViewById(R.id.shareLayout);
        rate = findViewById(R.id.rateLayout);
        privacy = findViewById(R.id.privacyLayout);


    }


    //FirebaseRecyclerOptions methods
    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

}

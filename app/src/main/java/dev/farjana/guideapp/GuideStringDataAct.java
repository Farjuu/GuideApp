package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class GuideStringDataAct extends AppCompatActivity {

    TextView titleText, DescText;
    private MaxAdView MrecTwo;
    Intent receiverIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_string_data);

        AppLovinInitialize();

        initializeAll();
        MrecTwo.loadAd();
        showMrecAd();


        receiverIntent = getIntent();
        int num = receiverIntent.getIntExtra("btnNum", 1);

        if (num == 1) {
            titleText.setText(R.string.GuideOneTitle);
            DescText.setText(R.string.GuideOneDesc);

        } else if (num == 2) {
            titleText.setText(R.string.GuideTwoTitle);
            DescText.setText(R.string.GuideTwoDesc);

        } else if (num == 3) {
            titleText.setText(R.string.GuideThreeTitle);
            DescText.setText(R.string.GuideThreeDesc);

        } else if (num == 4) {
            titleText.setText(R.string.GuideFourTitle);
            DescText.setText(R.string.GuideFourDesc);

        } else if (num == 5) {
            titleText.setText(R.string.GuideFiveTitle);
            DescText.setText(R.string.GuideFiveDesc);

        } else if (num == 6) {
            titleText.setText(R.string.GuideSixTitle);
            DescText.setText(R.string.GuideSixDesc);

        } else if (num == 7) {
            titleText.setText(R.string.GuideSevenTitle);
            DescText.setText(R.string.GuideSevenDesc);

        } else if (num == 8) {
            titleText.setText(R.string.GuideEightTitle);
            DescText.setText(R.string.GuideEightDesc);

        }
    }



    private void showMrecAd() {


        MrecTwo.setVisibility(View.VISIBLE);
        MrecTwo.startAutoRefresh();
        MrecTwo.setListener(new MaxAdViewAdListener() {
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

    private void AppLovinInitialize() {

        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
            }
        });
    }

    private void initializeAll() {

        titleText = findViewById(R.id.titleTextView);
        DescText = findViewById(R.id.descTextView);

        MrecTwo = findViewById(R.id.MrecTwo);

        titleText.setText("");
        DescText.setText("");

    }
}
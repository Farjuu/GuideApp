package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrivacyAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        TextView privacyTitle = findViewById(R.id.privacyTitle);
        TextView privacyDesc = findViewById(R.id.privacyDesc);

        privacyTitle.setText(R.string.privacyTitle);
        privacyDesc.setText(R.string.privacyDesc);
    }
}
package com.cashluto.rewad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Navigate to TmpProActivity
        Intent intent = new Intent(WelActivity.this, TmpproActivity.class);
        startActivity(intent);
        finish(); // Optional: Call this if you don't want to keep WelActivity in the back stack
    }
}

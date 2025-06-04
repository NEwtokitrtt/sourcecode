package com.cashluto.rewad;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionActivity extends AppCompatActivity {

    private static final int NOTIFICATION_PERMISSION_CODE = 1;
    private static final String PREFS_NAME = "PermissionPrefs";
    private static final String PREF_DENY_COUNT = "PermissionDenyCount";
    private static final String PREF_NAVIGATED_TO_MAIN = "NavigatedToMain";
    private static final String PREF_HAS_CLICKED_SETTINGS = "HasClickedSettings";

    private int pr_chk = 0;
    private boolean isNavigating = false; // Add this flag to prevent double navigation

    private Handler permissionCheckHandler;
    private Runnable permissionCheckRunnable;
    private Handler settingsRedirectHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        // Check if already navigated to MainActivity
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNavigatedToMain = prefs.getBoolean(PREF_NAVIGATED_TO_MAIN, false);

        if (isNavigatedToMain) {
            // If already navigated to MainActivity, skip permission request
            navigateToMainActivity();
        } else {
            // Otherwise, request notification permission (only for Android 13+)
            requestNotificationPermission();
        }
    }

    private void requestNotificationPermission() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int denyCount = prefs.getInt(PREF_DENY_COUNT, 0);

        // Only request permission for Android 13 (API level 33) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed to MainActivity
                pr_chk = 1;
                navigateToMainActivity();
            } else if (denyCount < 2) {
                // Request permission if it's the first or second denial
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
            } else {
                // If permission denied twice, skip permission request and proceed
                showPermissionDeniedDialog();
            }
        } else {
            // For devices below Android 13, proceed without requesting notification permission
            navigateToMainActivity();
        }

        // Start periodic permission check every 2 seconds
        startPermissionCheck();
    }

    private void startPermissionCheck() {
        // Initialize the handler and runnable for periodic checks
        permissionCheckHandler = new Handler();
        permissionCheckRunnable = new Runnable() {
            @Override
            public void run() {
                // Check if permission is granted every 2 seconds
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                        == PackageManager.PERMISSION_GRANTED && !isNavigating) {
                    // Permission granted, stop checking and navigate to MainActivity
                    permissionCheckHandler.removeCallbacks(permissionCheckRunnable);
                    pr_chk = 1;
                    navigateToMainActivity();
                } else if (!isNavigating) {
                    // Permission still denied, continue checking after 2 seconds
                    permissionCheckHandler.postDelayed(this, 2000);
                }
            }
        };

        // Start checking every 2 seconds
        permissionCheckHandler.postDelayed(permissionCheckRunnable, 2000);
    }

    private void showPermissionDeniedDialog() {
        if (isNavigating) return; // Prevent showing dialog if already navigating

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean hasClickedSettings = prefs.getBoolean(PREF_HAS_CLICKED_SETTINGS, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied")
                .setMessage("Notification permission is required for this app. Please allow it in the app settings.")
                .setCancelable(false)
                .setPositiveButton("Go to Settings", (dialog, which) -> {
                    if (!hasClickedSettings) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(PREF_HAS_CLICKED_SETTINGS, true);
                        editor.apply();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            CharSequence name = "Channel name 1";
                            String description = "Notification channel";
                            int importance = android.app.NotificationManager.IMPORTANCE_MAX;
                            android.app.NotificationChannel channel = new android.app.NotificationChannel("id 1", name, importance);
                            channel.setDescription(description);
                            android.app.NotificationManager notificationManager = getSystemService(android.app.NotificationManager.class);
                            notificationManager.createNotificationChannel(channel);
                        }

                        settingsRedirectHandler = new Handler();
                        settingsRedirectHandler.postDelayed(() -> {
                            if (pr_chk == 0 && !isNavigating) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(android.net.Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        }, 5000);

                    } else {
                        if (pr_chk == 0 && !isNavigating) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(android.net.Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    navigateToMainActivity();
                });

        builder.show();
    }

    private void navigateToMainActivity() {
        // Prevent double navigation
        if (isNavigating) return;
        isNavigating = true;

        // Stop any ongoing permission checks
        if (permissionCheckHandler != null && permissionCheckRunnable != null) {
            permissionCheckHandler.removeCallbacks(permissionCheckRunnable);
        }

        // Stop settings redirect handler
        if (settingsRedirectHandler != null) {
            settingsRedirectHandler.removeCallbacksAndMessages(null);
        }

        // Save flag to prevent repeated navigation
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(PREF_NAVIGATED_TO_MAIN, true).apply();

        // Navigate to MainActivity
        PackageChecker packageChecker = new PackageChecker(this);
        packageChecker.checkPackagesAndLaunchActivity();

        // Finish the current activity
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                navigateToMainActivity();
            } else {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                int denyCount = prefs.getInt(PREF_DENY_COUNT, 0);

                denyCount++;
                prefs.edit().putInt(PREF_DENY_COUNT, denyCount).apply();

                if (denyCount < 2) {
                    Toast.makeText(this, "Notification permission is required. Please allow.", Toast.LENGTH_SHORT).show();
                    requestNotificationPermission();
                } else {
                    showPermissionDeniedDialog();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up handlers to prevent memory leaks
        if (permissionCheckHandler != null) {
            permissionCheckHandler.removeCallbacksAndMessages(null);
        }
        if (settingsRedirectHandler != null) {
            settingsRedirectHandler.removeCallbacksAndMessages(null);
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
}
//package com.cashluto.rewad;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.provider.Settings;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//public class PermissionActivity extends AppCompatActivity {
//
//    private static final int NOTIFICATION_PERMISSION_CODE = 1;
//    private static final String PREFS_NAME = "PermissionPrefs";
//    private static final String PREF_DENY_COUNT = "PermissionDenyCount";
//    private static final String PREF_NAVIGATED_TO_MAIN = "NavigatedToMain";
//    private static final String PREF_HAS_CLICKED_SETTINGS = "HasClickedSettings";
//
//    private int pr_chk = 0;  // Removed 'final', so we can modify this value
//
//    private Handler permissionCheckHandler;
//    private Runnable permissionCheckRunnable;
//    private Handler settingsRedirectHandler;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_permission);
//
//        // Check if already navigated to MainActivity
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        boolean isNavigatedToMain = prefs.getBoolean(PREF_NAVIGATED_TO_MAIN, false);
//
//        if (isNavigatedToMain) {
//            // If already navigated to MainActivity, skip permission request
//            navigateToMainActivity();
//        } else {
//            // Otherwise, request notification permission (only for Android 13+)
//            requestNotificationPermission();
//        }
//    }
//
//    private void requestNotificationPermission() {
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        int denyCount = prefs.getInt(PREF_DENY_COUNT, 0);
//
//        // Only request permission for Android 13 (API level 33) and above
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
//                    == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, proceed to MainActivity
//                pr_chk = 1;
//                navigateToMainActivity();
//            } else if (denyCount < 2) {
//                // Request permission if it's the first or second denial
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
//            } else {
//                // If permission denied twice, skip permission request and proceed
//                showPermissionDeniedDialog();
//            }
//        } else {
//            // For devices below Android 13, proceed without requesting notification permission
//            navigateToMainActivity();
//        }
//
//        // Start periodic permission check every 2 seconds
//        startPermissionCheck();
//    }
//
//    private void startPermissionCheck() {
//        // Initialize the handler and runnable for periodic checks
//        permissionCheckHandler = new Handler();
//        permissionCheckRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // Check if permission is granted every 2 seconds
//                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.POST_NOTIFICATIONS)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    // Permission granted, stop checking and navigate to MainActivity
//                    permissionCheckHandler.removeCallbacks(permissionCheckRunnable);
//                    pr_chk = 1;
//                    navigateToMainActivity();
//                } else {
//                    // Permission still denied, continue checking after 2 seconds
//                    permissionCheckHandler.postDelayed(this, 2000); // 2000 milliseconds = 2 seconds
//                }
//            }
//        };
//
//        // Start checking every 2 seconds
//        permissionCheckHandler.postDelayed(permissionCheckRunnable, 2000);
//    }
//
//    private void showPermissionDeniedDialog() {
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        boolean hasClickedSettings = prefs.getBoolean(PREF_HAS_CLICKED_SETTINGS, false);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Permission Denied")
//                .setMessage("Notification permission is required for this app. Please allow it in the app settings.")
//                .setCancelable(false)
//                .setPositiveButton("Go to Settings", (dialog, which) -> {
//                    if (!hasClickedSettings) {
//                        // Execute your custom code the first time the user clicks "Go to Settings"
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.putBoolean(PREF_HAS_CLICKED_SETTINGS, true); // Set flag to true
//                        editor.apply();
//
//                        // Your custom code, e.g., creating notification channels, etc.
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                            CharSequence name = "Channel name 1";
//                            String description = "Notification channel";
//                            int importance = android.app.NotificationManager.IMPORTANCE_MAX;
//                            android.app.NotificationChannel channel = new android.app.NotificationChannel("id 1", name, importance);
//                            channel.setDescription(description);
//                            android.app.NotificationManager notificationManager = getSystemService(android.app.NotificationManager.class);
//                            notificationManager.createNotificationChannel(channel);
//                        }
//
//                        // Add a 5-second delay before opening the settings screen
//                        settingsRedirectHandler = new Handler();
//                        settingsRedirectHandler.postDelayed(() -> {
//                            if (pr_chk == 0) {
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                intent.setData(android.net.Uri.parse("package:" + getPackageName()));
//                                startActivity(intent);
//                            }
//                        }, 5000); // 5000 milliseconds = 5 seconds
//
//                    } else {
//                        if (pr_chk == 0) {
//                            // If the user has clicked before, just go to settings without executing custom code
//                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            intent.setData(android.net.Uri.parse("package:" + getPackageName()));
//                            startActivity(intent);
//                        }
//
//                    }
//                })
//                .setNegativeButton("Cancel", (dialog, which) -> {
//                    // Proceed without notifications
////                    Toast.makeText(this, "Permission denied twice. Proceeding without notifications.", Toast.LENGTH_SHORT).show();
//                    navigateToMainActivity();
//                });
//
//        builder.show();
//    }
//
//    private void navigateToMainActivity() {
//        // Save flag to prevent repeated navigation
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        prefs.edit().putBoolean(PREF_NAVIGATED_TO_MAIN, true).apply();
//
//        // Navigate to MainActivity (you can use your own package checking logic here)
//        PackageChecker packageChecker = new PackageChecker(this);
//        packageChecker.checkPackagesAndLaunchActivity();
//
//        // Finish the current activity to prevent it from being in the back stack
//        finish();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, proceed to MainActivity
//                navigateToMainActivity();
//            } else {
//                // Permission denied
//                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                int denyCount = prefs.getInt(PREF_DENY_COUNT, 0);
//
//                // Increment deny count and save it
//                denyCount++;
//                prefs.edit().putInt(PREF_DENY_COUNT, denyCount).apply();
//
//                if (denyCount < 2) {
//                    // Retry requesting the permission if less than 2 denials
//                    Toast.makeText(this, "Notification permission is required. Please allow.", Toast.LENGTH_SHORT).show();
//                    requestNotificationPermission();
//                } else {
//                    // If denied twice, proceed without requesting permission
//                    showPermissionDeniedDialog();
//                }
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Show a toast message when the activity is paused
////        Toast.makeText(this, "Activity Paused", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        // Show a toast message when the activity is stopped
////        Toast.makeText(this, "Activity Stopped", Toast.LENGTH_SHORT).show();
//    }
//}

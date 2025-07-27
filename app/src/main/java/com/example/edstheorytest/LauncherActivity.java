package com.example.edstheorytest;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Assuming you have a layout file named 'activity_launcher.xml'
        // that contains a WebView with the id 'webViewLauncher'
        setContentView(R.layout.activity_launcher);

        WebView webView = findViewById(R.id.webViewLauncher); // Make sure this ID matches your XML

        // Enable JavaScript if your HTML file uses it (recommended)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Allow access to local files (important for loading from assets)
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true); // Might be needed for more complex scenarios

        // Load the index.html file from the assets folder
        // The path starts with "file:///android_asset/"
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Optional: Handle back button presses to navigate within WebView history
    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webViewLauncher);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

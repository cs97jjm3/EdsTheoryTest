package com.example.edstheorytest;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class MainActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);  // ✅ Needed for your interactive HTML

        // ✅ Extra safety settings for local content
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setAllowFileAccessFromFileURLs(false);

        // Always load the new home screen
        webview.loadUrl("file:///android_asset/index.html");
    }
}

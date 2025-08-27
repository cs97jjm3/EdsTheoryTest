package com.example.edstheorytest;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        // Avoid overlap with status/nav bars
        ViewCompat.setOnApplyWindowInsetsListener(webView, (view, insets) -> {
            Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(0, sys.top, 0, sys.bottom);
            return insets;
        });

        // Open external links outside the WebView (Play, WhatsApp, SMS, mailto, http/https)
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return handleExternal(request.getUrl());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleExternal(Uri.parse(url));
            }

            private boolean handleExternal(Uri uri) {
                String scheme = uri.getScheme() != null ? uri.getScheme().toLowerCase() : "";
                if (uri.getHost() != null) {
                    uri.getHost().toLowerCase();
                }

                // Let our local pages load inside the WebView
                if ("file".equals(scheme)) return false;

                // Anything else (http/https, market, mailto, sms, wa.me, etc.) -> launch outside
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } catch (ActivityNotFoundException ignore) { /* no handler, ignore */ }
                return true; // we've handled it
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);

        // Keep local-only access; don’t allow local -> remote jumps
        webSettings.setAllowUniversalAccessFromFileURLs(false);

        // Never allow mixed content (http on https) just in case
        webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        webView.loadUrl("file:///android_asset/index.html");
    }
}


package com.netbeta.browser;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hubungkan ke background service
        try {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName("com.netbeta.service", "com.netbeta.service.Service"));
            startService(serviceIntent);
            Toast.makeText(this, "Net S2 Operator Terhubung!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Service tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        // Setup WebView
        myWebView = new WebView(this);
        setContentView(myWebView);

        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        // Halaman Portal Pencarian Net Browser 3.5
        String html = "<!DOCTYPE html><html><head><meta charset='utf-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<style>"
            + "body{font-family:sans-serif;text-align:center;background:#f8fafc;padding:30px 16px;color:#0f172a;margin:0;}"
            + ".badge{display:inline-block;background:#ecfdf5;color:#047857;padding:6px 14px;border-radius:20px;font-size:13px;font-weight:bold;margin-bottom:14px;border:1px solid #a7f3d0;}"
            + "h1{font-size:26px;margin:0 0 6px 0;}"
            + "p.sub{color:#475569;font-size:14px;margin:0 0 20px 0;}"
            + ".search-box{display:flex;gap:8px;max-width:440px;margin:0 auto 20px auto;}"
            + ".search-input{flex:1;padding:12px 16px;font-size:15px;border:2px solid #cbd5e1;border-radius:10px;outline:none;background:#fff;}"
            + ".search-btn{padding:12px 20px;background:#2563eb;color:#fff;border:none;border-radius:10px;font-size:14px;font-weight:bold;cursor:pointer;}"
            + ".card{background:#fff;border-radius:12px;padding:14px;max-width:440px;margin:0 auto 20px auto;border:1px solid #e2e8f0;text-align:left;font-size:13px;}"
            + ".row{display:flex;justify-content:space-between;padding:6px 0;border-bottom:1px solid #f1f5f9;}"
            + ".row:last-child{border-bottom:none;}"
            + ".lbl{color:#64748b;}.val{font-family:monospace;font-weight:bold;}"
            + ".links a{display:inline-block;margin:4px;padding:8px 16px;background:#f1f5f9;color:#334155;border-radius:8px;text-decoration:none;font-size:13px;border:1px solid #cbd5e1;}"
            + "</style></head><body>"
            + "<div class='badge'>● Net S2 Operator: Online & Active</div>"
            + "<h1>Net Browser 3.5</h1>"
            + "<p class='sub'>Berhasil berjalan dan terhubung ke Service!</p>"
            + "<form class='search-box' onsubmit='searchGoogle(event)'>"
            + "  <input type='text' id='queryInput' class='search-input' placeholder='Ketik Disini..' autocomplete='off' />"
            + "  <button type='klik aku pliss' class='search-btn'>Buka Google</button>"
            + "</form>"
            + "<div class='card'>"
            + "  <div class='row'><span class='lbl'>Application ID:</span><span class='val'>com.netbeta.browser</span></div>"
            + "  <div class='row'><span class='lbl'>Service Component:</span><span class='val'>com.netbeta.service.Service</span></div>"
            + "  <div class='row'><span class='lbl'>Managed By:</span><span class='val'>BetaSoft.corp</span></div>"
            + "</div>"
            + "<div class='links'>"
            + "  <a href='https://news.ycombinator.com'>Hacker News</a>"
            + "</div>"
            + "<script>"
            + "function searchGoogle(e){"
            + "  if(e) e.preventDefault();"
            + "  var q = document.getElementById('queryInput').value.trim();"
            + "  if(q){ window.location.href = 'https://www.google.com/search?q=' + encodeURIComponent(q); }"
            + "  else { window.location.href = 'https://www.google.com'; }"
            + "}"
            + "</script>"
            + "</body></html>";

        myWebView.loadDataWithBaseURL("https://www.netbeta.com", html, "text/html", "UTF-8", null);
    }

    @Override
    public void onBackPressed() {
        if (myWebView != null && myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

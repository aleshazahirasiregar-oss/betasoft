package com.netbeta.browser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String SERVICE_PACKAGE = "com.netbeta.service";
    private static final String SERVICE_CLASS = "com.netbeta.service.Service";
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Cek apakah com.netbeta.service ada di HP
        if (!isServiceInstalled(SERVICE_PACKAGE)) {
            showServiceRequiredDialog();
            return;
        }

        // 2. Start Service
        try {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName(SERVICE_PACKAGE, SERVICE_CLASS));
            startService(serviceIntent);
            Toast.makeText(this, "Net S2 Operator: Online & Active", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Gagal start service", Toast.LENGTH_SHORT).show();
        }

        // 3. Setup WebView
        myWebView = new WebView(this);
        setContentView(myWebView);

        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        // Halaman Bersih (Tanpa Kotak Info ID / SDK / Engine)
        String html = "<!DOCTYPE html><html><head><meta charset='utf-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<style>"
            + "body{font-family:sans-serif;text-align:center;background:#f8fafc;padding:40px 16px;color:#0f172a;margin:0;}"
            + ".badge{display:inline-block;background:#ecfdf5;color:#047857;padding:6px 16px;border-radius:20px;font-size:13px;font-weight:bold;margin-bottom:16px;border:1px solid #a7f3d0;}"
            + "h1{font-size:28px;font-weight:800;margin:0 0 8px 0;}"
            + "p.sub{color:#475569;font-size:15px;margin:0 0 28px 0;}"
            + ".search-box{display:flex;gap:8px;max-width:460px;margin:0 auto 24px auto;}"
            + ".search-input{flex:1;padding:14px 18px;font-size:16px;border:2px solid #cbd5e1;border-radius:12px;outline:none;background:#fff;}"
            + ".search-input:focus{border-color:#2563eb;}"
            + ".search-btn{padding:14px 22px;background:#2563eb;color:#fff;border:none;border-radius:12px;font-size:15px;font-weight:bold;cursor:pointer;}"
            + ".links{margin-top:16px;}"
            + ".links a{display:inline-block;margin:6px;padding:10px 20px;background:#f1f5f9;color:#334155;border-radius:10px;text-decoration:none;font-size:14px;font-weight:600;border:1px solid #cbd5e1;}"
            + "</style></head><body>"
            + "<div class='badge'>● Net S2 Operator: Online & Active</div>"
            + "<h1>Net Browser 3.5</h1>"
            + "<p class='sub'>Berhasil berjalan dan terhubung ke Service!</p>"
            + "<form class='search-box' onsubmit='searchGoogle(event)'>"
            + "  <input type='text' id='queryInput' class='search-input' placeholder='Ketik Disini..' autocomplete='off' />"
            + "  <button type='submit' class='search-btn'>Buka Google</button>"
            + "</form>"
            + "<div class='links'>"
            + "  <a href='https://en.m.wikipedia.org'>Wikipedia</a>"
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

    private boolean isServiceInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_SERVICES);
            return true;
        } catch (Exception e) {
            try {
                pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    private void showServiceRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Service Diperlukan");
        builder.setMessage("Aplikasi ini membutuhkan 'com.netbeta.service' agar dapat berjalan.");
        builder.setCancelable(false);
        builder.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
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

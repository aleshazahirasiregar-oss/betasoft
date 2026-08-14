package com.netbeta.browser;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Nyalakan Service terpisah (com.netbeta.service) secara otomatis
        try {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName("com.netbeta.service", "com.netbeta.service.Service"));
            startService(serviceIntent);
            Toast.makeText(this, "Net S2 Operator diaktifkan!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Gagal nyalakan service: Pastikan APK service sudah terinstal!", Toast.LENGTH_LONG).show();
        }

        // 2. Buat WebView & muat halaman HTML langsung (Tanpa assets)
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Ubah teks di bawah ini kalau mau ganti tampilan halaman web browser kamu
        String htmlCode = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <style>\n" +
            "    body {\n" +
            "      font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif;\n" +
            "      text-align: center;\n" +
            "      background: linear-gradient(135deg, #f0f4f8 0%, #d9e2ec 100%);\n" +
            "      margin: 0;\n" +
            "      padding: 40px 20px;\n" +
            "      min-height: 100vh;\n" +
            "      box-sizing: border-box;\n" +
            "      color: #102a43;\n" +
            "    }\n" +
            "    .container {\n" +
            "      max-width: 600px;\n" +
            "      margin: 0 auto;\n" +
            "      background: #ffffff;\n" +
            "      padding: 36px 28px;\n" +
            "      border-radius: 16px;\n" +
            "      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.06), 0 2px 6px rgba(0, 0, 0, 0.04);\n" +
            "      border: 1px solid #e2e8f0;\n" +
            "    }\n" +
            "    .badge {\n" +
            "      display: inline-flex;\n" +
            "      align-items: center;\n" +
            "      gap: 6px;\n" +
            "      background-color: #e6fffa;\n" +
            "      color: #047481;\n" +
            "      padding: 6px 14px;\n" +
            "      border-radius: 9999px;\n" +
            "      font-size: 13px;\n" +
            "      font-weight: 600;\n" +
            "      margin-bottom: 20px;\n" +
            "      border: 1px solid #b2f5ea;\n" +
            "    }\n" +
            "    .pulse-dot {\n" +
            "      width: 8px;\n" +
            "      height: 8px;\n" +
            "      background-color: #319795;\n" +
            "      border-radius: 50%;\n" +
            "      box-shadow: 0 0 0 2px rgba(49, 151, 149, 0.2);\n" +
            "    }\n" +
            "    h1 {\n" +
            "      color: #0f172a;\n" +
            "      font-size: 28px;\n" +
            "      margin: 0 0 10px 0;\n" +
            "      font-weight: 800;\n" +
            "      letter-spacing: -0.5px;\n" +
            "    }\n" +
            "    p.lead {\n" +
            "      color: #475569;\n" +
            "      font-size: 16px;\n" +
            "      line-height: 1.6;\n" +
            "      margin: 0 0 24px 0;\n" +
            "    }\n" +
            "    .info-card {\n" +
            "      background: #f8fafc;\n" +
            "      border-radius: 12px;\n" +
            "      padding: 16px;\n" +
            "      margin: 20px 0;\n" +
            "      border: 1px solid #edf2f7;\n" +
            "      text-align: left;\n" +
            "      font-size: 13px;\n" +
            "    }\n" +
            "    .info-row {\n" +
            "      display: flex;\n" +
            "      justify-content: space-between;\n" +
            "      padding: 6px 0;\n" +
            "      border-bottom: 1px solid #e2e8f0;\n" +
            "    }\n" +
            "    .info-row:last-child {\n" +
            "      border-bottom: none;\n" +
            "    }\n" +
            "    .info-label {\n" +
            "      color: #64748b;\n" +
            "      font-weight: 500;\n" +
            "    }\n" +
            "    .info-value {\n" +
            "      color: #0f172a;\n" +
            "      font-family: monospace;\n" +
            "      font-weight: 600;\n" +
            "    }\n" +
            "    .btn-group {\n" +
            "      display: flex;\n" +
            "      flex-wrap: wrap;\n" +
            "      gap: 10px;\n" +
            "      justify-content: center;\n" +
            "      margin-top: 24px;\n" +
            "    }\n" +
            "    .btn {\n" +
            "      display: inline-block;\n" +
            "      padding: 10px 18px;\n" +
            "      background: #2563eb;\n" +
            "      color: white;\n" +
            "      text-decoration: none;\n" +
            "      border-radius: 8px;\n" +
            "      font-size: 14px;\n" +
            "      font-weight: 500;\n" +
            "      transition: background 0.15s;\n" +
            "    }\n" +
            "    .btn:hover {\n" +
            "      background: #1d4ed8;\n" +
            "    }\n" +
            "    .btn-secondary {\n" +
            "      background: #f1f5f9;\n" +
            "      color: #334155;\n" +
            "      border: 1px solid #cbd5e1;\n" +
            "    }\n" +
            "    .btn-secondary:hover {\n" +
            "      background: #e2e8f0;\n" +
            "    }\n" +
            "    footer {\n" +
            "      margin-top: 30px;\n" +
            "      font-size: 12px;\n" +
            "      color: #94a3b8;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"container\">\n" +
            "    <div class=\"badge\">\n" +
            "      <span class=\"pulse-dot\"></span>\n" +
            "      Net S2 Operator: Online & Active\n" +
            "    </div>\n" +
            "    <h1>Net Browser 3.5</h1>\n" +
            "    <p class=\"lead\">Berhasil berjalan dan terhubung ke Service!</p>\n" +
            "\n" +
            "    <div class=\"info-card\">\n" +
            "      <div class=\"info-row\">\n" +
            "        <span class=\"info-label\">Application ID:</span>\n" +
            "        <span class=\"info-value\">com.netbeta.browser</span>\n" +
            "      </div>\n" +
            "      <div class=\"info-row\">\n" +
            "        <span class=\"info-label\">Service Component:</span>\n" +
            "        <span class=\"info-value\">com.netbeta.service.Service</span>\n" +
            "      </div>\n" +
            "      <div class=\"info-row\">\n" +
            "        <span class=\"info-label\">WebView Engine:</span>\n" +
            "        <span class=\"info-value\">Android WebKit / Chrome 120</span>\n" +
            "      </div>\n" +
            "      <div class=\"info-row\">\n" +
            "        <span class=\"info-label\">Target SDK:</span>\n" +
            "        <span class=\"info-value\">API 36 (Android 15+)</span>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"btn-group\">\n" +
            "      <a href=\"https://www.google.com\" class=\"btn\">Buka Google</a>\n" +
            "      <a href=\"https://en.m.wikipedia.org\" class=\"btn btn-secondary\">Wikipedia</a>\n" +
            "      <a href=\"https://news.ycombinator.com\" class=\"btn btn-secondary\">Hacker News</a>\n" +
            "    </div>\n" +
            "\n" +
            "    <footer>\n" +
            "      Net Browser 3.5 &bull; Android WebKit Engine &bull; JavaScript Enabled\n" +
            "    </footer>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>";

        webView.loadDataWithBaseURL(null, htmlCode, "text/html", "UTF-8", null);

        setContentView(webView);
    }
}

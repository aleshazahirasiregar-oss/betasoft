package com.netbeta.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Service extends android.app.Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Net S2 Operator (com.netbeta.service) Aktif!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Layanan berjalan terus di latar belakang
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Net S2 Operator Berhenti", Toast.LENGTH_SHORT).show();
    }
}

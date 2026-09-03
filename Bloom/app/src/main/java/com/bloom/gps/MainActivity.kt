package com.bloom.gps

import android.content.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val smsReceiver = SmsReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ici tu mettras ton layout Compose / View du v2 que je t'ai fait
        // Pour l'instant layout simple
        setContentView(android.R.layout.activity_list_item)

        // FIX ANDROID 14 - C'EST ICI QUE TON ANCIENNE APP PLANTAIT
        val filter = IntentFilter("android.provider.Telephony.SMS_RECEIVE")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // SMS vient du système = EXPORTED obligatoire
            registerReceiver(smsReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            registerReceiver(smsReceiver, filter)
        }

        // Avec AndroidX (encore plus safe):
        // ContextCompat.registerReceiver(this, smsReceiver, filter, ContextCompat.RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsReceiver)
    }
}

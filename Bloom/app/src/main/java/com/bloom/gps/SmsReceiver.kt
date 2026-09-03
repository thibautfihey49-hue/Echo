package com.bloom.gps

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Ton code Bloom ici : si SMS = "BLOOM_START" -> démarre le service GPS
        // Les SMS de données ne vont jamais dans la messagerie
    }
}

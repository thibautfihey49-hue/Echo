package com.ghost.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val corps = messages.joinToString("") { it.messageBody }
            val expediteur = messages[0].displayOriginatingAddress

            if (expediteur == MainActivity.CONTACT_NUMERO ||
                MainActivity.CONTACT_NUMERO.endsWith(expediteur)
            ) {
                QuoteNotificationManager.showRandomQuote(context)
                MainActivity.instance?.ajouterMessageRecu(corps)
            }
        }
    }
}

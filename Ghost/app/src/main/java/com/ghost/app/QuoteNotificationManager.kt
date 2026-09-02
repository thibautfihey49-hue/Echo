package com.ghost.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlin.random.Random

object QuoteNotificationManager {
    private const val CHANNEL_ID = "ghost_citations"
    private const val NOTIF_ID = 999

    private val citations = listOf(
        "La vie est belle.",
        "Tout vient à point à qui sait attendre.",
        "L'espoir fait vivre.",
        "Le temps est un grand maître.",
        "À cœur vaillant rien d'impossible.",
        "La patience est la clé du bonheur.",
        "Qui ne tente rien n'a rien.",
        "Chaque jour est une nouvelle chance.",
        "La simplicité est la sophistication.",
        "Rêver c'est le bonheur, espérer c'est la vie."
    )

    fun showRandomQuote(context: Context) {
        creerCanal(context)
        val citation = citations.random(Random(System.currentTimeMillis()))

        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentTitle("💡 Pensée du jour")
            .setContentText(citation)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(true)
            .setSilent(true)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(NOTIF_ID, notif)
    }

    private fun creerCanal(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                CHANNEL_ID,
                "Citations",
                NotificationManager.IMPORTANCE_LOW
            )
            canal.description = "Notifications discrètes"
            canal.setShowBadge(false)
            canal.enableVibration(false)
            canal.setSound(null, null)
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(canal)
        }
    }
}

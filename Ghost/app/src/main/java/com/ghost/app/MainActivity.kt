package com.ghost.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ghost.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    companion object {
        const val PERMISSION_REQUEST = 101
        var CONTACT_NUMERO = "+33600000000"
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageAdapter = MessageAdapter(messages)
        binding.messagesRecycler.adapter = messageAdapter

        findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            finishAffinity()
        }

        requestPermissionsIfNeeded()

        findViewById<Button>(R.id.envoyerBtn).setOnClickListener {
            val texte = findViewById<EditText>(R.id.messageInput).text.toString().trim()
            if (texte.isNotEmpty()) {
                envoyerSMS(texte)
                findViewById<EditText>(R.id.messageInput).text.clear()
                messages.add(Message(texte, estMoi = true))
                messageAdapter.notifyItemInserted(messages.size - 1)
                binding.messagesRecycler.scrollToPosition(messages.size - 1)
            }
        }
    }

    private fun envoyerSMS(texte: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            SmsManager.getDefault().sendTextMessage(
                CONTACT_NUMERO,
                null,
                texte,
                null,
                null
            )
        }
    }

    private fun requestPermissionsIfNeeded() {
        val permissions = listOf(
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.POST_NOTIFICATIONS
        )
        val needed = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()
        if (needed.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, needed, PERMISSION_REQUEST)
        }
    }

    fun ajouterMessageRecu(texte: String) {
        runOnUiThread {
            messages.add(Message(texte, estMoi = false))
            messageAdapter.notifyItemInserted(messages.size - 1)
            binding.messagesRecycler.scrollToPosition(messages.size - 1)
        }
    }

    override fun onDestroy() {
        instance = null
        super.onDestroy()
    }
}

data class Message(val texte: String, val estMoi: Boolean)

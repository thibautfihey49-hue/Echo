package com.ghost.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghost.app.databinding.ItemMessageBinding

class MessageAdapter(private val messages: MutableList<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(msg: Message) {
            binding.messageText.text = msg.texte
            if (msg.estMoi) {
                binding.messageText.setBackgroundResource(android.R.drawable.edit_text)
                binding.messageText.textAlignment = android.view.View.TEXT_ALIGNMENT_TEXT_END
            } else {
                binding.messageText.setBackgroundResource(android.R.drawable.dialog_frame)
                binding.messageText.textAlignment = android.view.View.TEXT_ALIGNMENT_TEXT_START
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size
}

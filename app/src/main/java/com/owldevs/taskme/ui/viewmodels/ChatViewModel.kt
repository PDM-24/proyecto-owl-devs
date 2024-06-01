package com.owldevs.taskme.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*

data class Message(val sender: String, val text: String, val timestamp: String, val isUser: Boolean)

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    init {
        // Initialize with some messages
        _messages.value = listOf(
            Message("John Doe", "Hola!", "18 OCT 2021 A LAS 7:25 P.M.", false),
            Message("Usuario", "Holaaaaa!", "18 OCT 2021 A LAS 7:26 P.M.", true)
        )
    }

    fun sendMessage(text: String) {
        val newMessage = Message(
            sender = "Usuario",
            text = text,
            timestamp = getCurrentTimestamp(),
            isUser = true
        )
        _messages.value = _messages.value + newMessage
    }

    private fun getCurrentTimestamp(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy 'A LAS' hh:mm a", Locale.getDefault())
        return dateFormat.format(Date())
    }
}


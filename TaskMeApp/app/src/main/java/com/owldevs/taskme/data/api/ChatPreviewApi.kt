package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName

data class ChatPreviewApi(
    @SerializedName("usuarioId") val userId: String,
    @SerializedName("taskerId") val taskerId: String,
    @SerializedName("taskName") val taskName: String,
    @SerializedName("timestamp") val timestamp: String
)

data class ApiChatPreviewResponse(
    @SerializedName("result") val result: String,
    @SerializedName("chatPreview") val chatPreview: ChatPreviewApi
)

data class ApiChatPreviewsResponse(
    @SerializedName("chatPreviews") val chatPreviews: List<ChatPreviewApi>
)

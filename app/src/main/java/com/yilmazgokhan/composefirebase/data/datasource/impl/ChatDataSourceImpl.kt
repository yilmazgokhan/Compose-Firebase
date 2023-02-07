package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.ChatDataSource
import com.yilmazgokhan.composefirebase.data.datasource.entity.ChatDTO
import com.yilmazgokhan.composefirebase.util.ChatNotFoundException
import com.yilmazgokhan.composefirebase.util.Constants.Firestore.CHATS
import com.yilmazgokhan.composefirebase.util.State
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
): ChatDataSource {
    override suspend fun createChat(
        id: String,
        title: String,
        description: String,
        userId: String,
    ): State<ChatDTO> {
        return try {
            val chat = ChatDTO(
                id = id,
                title = title,
                description = description,
                userId = userId
            )
            firebaseFirestore.collection(CHATS).document(id).set(chat).await()
            val chatRef = firebaseFirestore.collection(CHATS).document(id).get().await()

            val data = chatRef.toObject(ChatDTO::class.java)
            if (data != null)
                State.Success(data)
            else
                State.Error(ChatNotFoundException("Chat not found!"))
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    override suspend fun getChat(): State<Any> {
        TODO("Not yet implemented")
    }
}
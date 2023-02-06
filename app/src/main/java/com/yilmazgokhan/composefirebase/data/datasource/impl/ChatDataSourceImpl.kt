package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.ChatDataSource
import com.yilmazgokhan.composefirebase.data.datasource.entity.ChatDTO
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class ChatDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
): ChatDataSource {
    override suspend fun createChat(
        title: String,
        description: String,
        userId: String,
    ): State<ChatDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(): State<Any> {
        TODO("Not yet implemented")
    }
}
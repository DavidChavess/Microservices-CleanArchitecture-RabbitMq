package com.davidchaves.storeapi.data.protocols

import java.io.Serializable

interface SendToQueue {
    fun sendToQueue(queue: String, message: Serializable)
}

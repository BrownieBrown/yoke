package mbraun.yoke.exception

import java.util.*

data class ErrorMessage(
    val statusCode: Int,
    val timeStamp: Date,
    val message: String,
    val description: String
)
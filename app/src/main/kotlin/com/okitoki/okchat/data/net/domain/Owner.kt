package com.okitoki.okchat.data.net.domain

import com.google.gson.annotations.SerializedName

/**
 * @author ridsync
 */
data class Owner(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String
)
package com.okitoki.okchat.data.net.response

import com.google.gson.annotations.SerializedName
import com.okitoki.okchat.data.net.domain.Repository

/**
 * @author ridsync
 */
data class RepositoriesResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: ArrayList<Repository>
)
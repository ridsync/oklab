package com.okitoki.okchat.data.net.response

import com.google.gson.annotations.SerializedName
import com.okitoki.okchat.data.net.domain.Repository

/**
 * @author ridsync
 */
open class BaseResponse {
    @SerializedName("res_code")
    var resCode: Int = 0
    @SerializedName("res_msg")
    var resMsg: String? = null
}

data class RepositoriesResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: ArrayList<Repository>
)  : BaseResponse()
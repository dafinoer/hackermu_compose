package com.dafinrs.hackermu.data.remote


import com.dafinrs.hackermu.di.NetworkModule
import com.dafinrs.hackermu.domains.ServerError
import com.dafinrs.hackermu.domains.models.StoryModel
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject


class StoriesRemote @Inject constructor(
    private val okHttpClient: OkHttpClient, private val moshi: Moshi
) {

    @OptIn(ExperimentalStdlibApi::class)
    fun getTopIdStory(pathSegment: String, limit: Int = 10): List<Int> {
        val parserRequest = NetworkModule.provideHttpRequest(
            path = pathSegment, queryParams = mapOf("print" to "pretty")
        )

        val request = Request.Builder().url(parserRequest).build()
        val result = okHttpClient.newCall(request).execute()
        if (!result.isSuccessful) {
            throw ServerError(statusErrorCode = result.code)
        } else if (result.body == null) {
            throw JsonDataException("Data from server null")
        }
        val jsonAdapter = moshi.adapter<List<Int>>().fromJson(result.body!!.string())

        return jsonAdapter?.take(limit) ?: emptyList()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun getStory(idStory: Int): StoryModel? {
        val parse = NetworkModule.provideHttpRequest(
            path = "item/$idStory.json", queryParams = mapOf("print" to "pretty")
        )

        val request = Request.Builder().url(parse).build()
        val result = okHttpClient.newCall(request).execute()
        if (!result.isSuccessful) {
            throw ServerError(statusErrorCode = result.code)
        }

        return moshi.adapter<StoryModel>().fromJson(result.body!!.string())
    }
}
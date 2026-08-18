package com.clearn.app.data.remote.api

import com.clearn.app.data.remote.model.Judge0ResultResponse
import com.clearn.app.data.remote.model.Judge0SubmissionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface Judge0ApiService {

    @POST("submissions")
    suspend fun executeCode(
        @Query("base64_encoded") base64Encoded: Boolean = false,
        @Query("wait") wait: Boolean = true,
        @Body request: Judge0SubmissionRequest
    ): Response<Judge0ResultResponse>
}

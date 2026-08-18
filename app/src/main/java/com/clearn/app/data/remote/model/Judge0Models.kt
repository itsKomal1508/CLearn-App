package com.clearn.app.data.remote.model

import com.google.gson.annotations.SerializedName

data class Judge0SubmissionRequest(
    @SerializedName("source_code") val sourceCode: String,
    @SerializedName("language_id") val languageId: Int = 50, // C (GCC 9.2.0)
    @SerializedName("stdin") val stdin: String? = null
)

data class Judge0SubmissionResponse(
    @SerializedName("token") val token: String? = null
)

data class Judge0ResultResponse(
    @SerializedName("stdout") val stdout: String? = null,
    @SerializedName("stderr") val stderr: String? = null,
    @SerializedName("compile_output") val compileOutput: String? = null,
    @SerializedName("status") val status: Judge0Status? = null,
    @SerializedName("time") val time: String? = null,
    @SerializedName("memory") val memory: Int? = null
)

data class Judge0Status(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String
)

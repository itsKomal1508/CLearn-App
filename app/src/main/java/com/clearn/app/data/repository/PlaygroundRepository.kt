package com.clearn.app.data.repository

import com.clearn.app.data.remote.api.Judge0ApiService
import com.clearn.app.data.remote.model.Judge0SubmissionRequest
import com.clearn.app.data.remote.translator.CErrorTranslator

sealed class ExecutionResult {
    data class Success(val stdout: String, val time: String?, val memory: Int?) : ExecutionResult()
    data class Error(val friendlyErrorMessage: String, val rawStderr: String?) : ExecutionResult()
}

class PlaygroundRepository(
    private val apiService: Judge0ApiService
) {
    suspend fun executeCCode(code: String, input: String? = null): ExecutionResult {
        // Quick pre-check for common syntax omissions
        if (!code.contains("#include")) {
            return ExecutionResult.Error(
                friendlyErrorMessage = "🎓 Hint: Every standard C program usually starts with '#include <stdio.h>' at the top!",
                rawStderr = null
            )
        }

        if (!code.contains("main")) {
            return ExecutionResult.Error(
                friendlyErrorMessage = "🎓 Hint: Every C program must have a 'int main()' function where execution begins!",
                rawStderr = null
            )
        }

        return try {
            val response = apiService.executeCode(
                request = Judge0SubmissionRequest(
                    sourceCode = code,
                    stdin = input
                )
            )

            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                val stdout = result.stdout
                val stderr = result.stderr ?: result.compileOutput

                val errorOutput = stderr
                if (!errorOutput.isNullOrEmpty()) {
                    val translatedError = CErrorTranslator.translateCompilerError(errorOutput)
                    ExecutionResult.Error(
                        friendlyErrorMessage = translatedError,
                        rawStderr = errorOutput
                    )
                } else if (stdout != null) {
                    ExecutionResult.Success(
                        stdout = stdout,
                        time = result.time,
                        memory = result.memory
                    )
                } else {
                    ExecutionResult.Success(
                        stdout = "Program executed successfully with no output.",
                        time = result.time,
                        memory = result.memory
                    )
                }
            } else {
                ExecutionResult.Error(
                    friendlyErrorMessage = "Online C execution error: ${response.message()} (Code: ${response.code()})",
                    rawStderr = response.errorBody()?.string()
                )
            }
        } catch (e: Exception) {
            ExecutionResult.Error(
                friendlyErrorMessage = "Network Connection Error: Unable to reach online Judge0 server. Please ensure your device has an active Wi-Fi or Mobile Data connection.",
                rawStderr = e.localizedMessage
            )
        }
    }
}

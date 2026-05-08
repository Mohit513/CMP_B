package com.example.cmp_b.core.data.network.remote

/**
 * Central place for all API endpoint definitions
 */
class ApiNames {
    companion object {

        // Base URLs
        // const val BASE_URL = "https://jsonplaceholder.typicode.com/" // for testing posts API
        const val BASE_URL = "https://v5digitrackpaperlessonboarding.fmdigione.com/api/"

        // Posts API endpoints
        const val GET_POSTS = "posts"
        const val GET_POST_BY_ID = "posts/{id}"
        const val CREATE_POST = "posts"
        const val UPDATE_POST = "posts/{id}"
        const val DELETE_POST = "posts/{id}"

        // Auth API endpoints
        const val SEND_CANDIDATE_LOGIN_API = "TransferCandidateLogin"
        const val SEND_OTP_VALIDATE = "LoginOTPValidate"

    }
}
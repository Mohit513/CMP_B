package com.example.cmp_b.core.network

/**
 * Central place for all API endpoint definitions
 */
class ApiNames {
    companion object {
        
        // Base URLs
        const val BASE_URL = "https://jsonplaceholder.typicode.com/" // for testing
        // const val BASE_URL = "https://api.yourapp.com/" // for prod
        
        // Posts API endpoints
        const val GET_POSTS = "posts"
        const val GET_POST_BY_ID = "posts/{id}"
        const val CREATE_POST = "posts"
        const val UPDATE_POST = "posts/{id}"
        const val DELETE_POST = "posts/{id}"

    }
}

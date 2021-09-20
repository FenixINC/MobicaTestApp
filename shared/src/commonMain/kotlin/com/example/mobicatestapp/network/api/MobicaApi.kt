package com.example.mobicatestapp.network.api

import com.example.mobicatestapp.constants.NetworkConstants.GET_HOME_LIST
import com.example.mobicatestapp.network.response.HomeResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class MobicaApi(
    private val httpClient: HttpClient,
    private val json: Json,
) {
    /**
     * GET request
     **/
    suspend fun loadHomeList(): HomeResponse {
        return httpClient.get {
            url { path(GET_HOME_LIST) }
        }
    }

    /**
     * GET request with parameter
     **/
//    suspend fun getSomeDataById(id: String): SomeResponse {
//        return httpClient.get {
//            url { path(GET_SOME_DATA) }
//            parameter(key = QUERY_ID, value = id)
//        }
//    }

    /**
     * POST request
     **/
//    suspend fun createUser(userDto: UserDto) {
//        return httpClient.post {
//            url { path(POST_SOME_DATA) }
//
//            body = json.encodeToJsonElement(
//                serializer = UserDto.serializer(),
//                value = UserDto
//            )
//        }
//    }
}
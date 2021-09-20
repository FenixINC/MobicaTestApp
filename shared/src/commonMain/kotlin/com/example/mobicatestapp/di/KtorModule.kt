package com.example.mobicatestapp.di

import com.example.mobicatestapp.constants.NetworkConstants.BASE_URL
import com.example.mobicatestapp.core.HttpEngineFactory
import com.example.mobicatestapp.network.error_handling.ErrorResponse
import com.example.mobicatestapp.network.error_handling.HttpException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val ktorModule = DI.Module(
    name = "KtorModule",
    init = {
        bindSingleton { HttpEngineFactory() }
        bindSingleton {
            val engine = instance<HttpEngineFactory>().createEngine()

            HttpClient(engine) {
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }

                HttpResponseValidator {
                    validateResponse { response ->
                        when (val statusCode = response.status.value) {
                            /**
                             * Handle different exceptions
                             * */
                            in 400..499 -> throw HttpException(statusCode = statusCode)
                        }
                    }

                    handleResponseException { cause ->
                        val responseException =
                            cause as? ResponseException ?: return@handleResponseException
                        val response = responseException.response
                        val bytes = response.receive<ByteArray>()
                        val string = bytes.decodeToString()
                        val errorResponse = Json.decodeFromString(ErrorResponseSerializer, string)
                        throw HttpException(
                            statusCode = errorResponse.code?.toInt() ?: 400,
                            statusMessage = errorResponse.message,
                            url = errorResponse.name
                        )
                    }
                }

                install(HttpRedirect) {
                    checkHttpMethod = false
                }

                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    })
                }

                install(DefaultRequest, ::interceptors)

                defaultRequest {
                    url {
                        host = BASE_URL
                        protocol = URLProtocol.HTTPS
                    }
                    headers {
                        append(name = HttpHeaders.ContentType, value = ContentType.Application.Json)
                    }
                }
            }
        }
    }
)

private fun interceptors(builder: HttpRequestBuilder) {
    builder.apply {
        headers.apply { append(name = "Accept", value = "application/json") }
    }
}

object ErrorResponseSerializer : KSerializer<ErrorResponse> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(serialName = "UnknownProject") {
            element<String>(elementName = "name")
            element<String>(elementName = "message")
            element<String>(elementName = "code")
            /**
             * For custom objects
             * */
//        element<JsonElement>("details")
        }

    override fun deserialize(decoder: Decoder): ErrorResponse {
        val jsonInput = decoder as? JsonDecoder ?: error("Can be deserialized only by JSON")
        val json = jsonInput.decodeJsonElement().jsonObject
        val name = json.getValue(key = "name").jsonPrimitive.content
        val message = json.getValue(key = "message").jsonPrimitive.content
        val code = json.getValue(key = "code").jsonPrimitive.content
        return ErrorResponse(name, message, code)
    }

    override fun serialize(encoder: Encoder, value: ErrorResponse) {
        error("Serialization is not supported for $value")
    }
}
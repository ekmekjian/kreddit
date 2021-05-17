package com.kreddit

import com.google.gson.JsonArray
import com.kreddit.Models.Feed
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.gson.*
import io.ktor.util.*
import io.netty.handler.codec.http.HttpResponse
import kotlinx.coroutines.*

suspend fun main(args: Array<String>) {

    val client = HttpClient() {
        followRedirects = true
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Auth) {
            basic {
                username = "j5Bx7_Df0_wxdQ"
                password = "RBPs-nuxERTpShVo64766fdYZU4QkQ"
            }
        }
    }

    val authorize : HttpRequestBuilder = HttpRequestBuilder()
    authorize.url{
        host = "https://www.reddit.com/"
        path("api","v1","authorize")
        parameters.append("client_id","j5Bx7_Df0_wxdQ")
        parameters.append("response_type", "code")
        parameters.append("state","test")
        parameters.append("redirect_uri","https://127.0.0.1")
        parameters.append("duration","permanent")
        parameters.append("scope", "identity edit flair history modconfig modflair modlog modposts modwiki my subreddits privatemeassage read report save submit subscribe vote wikiedit wikiread")
    }

    val poster: HttpRequestBuilder = HttpRequestBuilder()
    poster.url {
        host = "https://www.reddit.com/"
        path("api", "v1", "access_token")
        parameters.append("grant_type", "authorization_code")
        parameters.append("code", "jBQV2X5Fiw2JpMmRp_wp8zg80pW00w")
        parameters.append("redirect_uri", "https://127.0.0.1/")
    }
    poster.method = HttpMethod.Post
    poster.header("User-Agent", "kred by /u/jokesterae")
    poster.build()
    data class AuthResponse(
        val access_token: String, val token_type: String,
        val expires_in: Int, val scope: String, val refresh_token: String
    )

    val response: HttpResponse = client.post<HttpResponse>(poster)
}
// Sample for making a HTTP Client request
/*
val message = client.post<JsonSampleClass> {
    url("http://127.0.0.1:8080/path/to/endpoint")
    contentType(ContentType.Application.Json)
    body = JsonSampleClass(hello = "world")
}
*/



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
import kotlinx.coroutines.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient(CIO) {
        followRedirects = true
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Auth){
            basic {
                username = "j5Bx7_Df0_wxdQ"
                password = "RBPs-nuxERTpShVo64766fdYZU4QkQ"
            }
        }
    }
    runBlocking {
        // Sample for making a HTTP Client request
        /*
        val message = client.post<JsonSampleClass> {
            url("http://127.0.0.1:8080/path/to/endpoint")
            contentType(ContentType.Application.Json)
            body = JsonSampleClass(hello = "world")
        }
        */


        val poster: HttpRequestBuilder = HttpRequestBuilder()
        poster.url {
            host = "www.reddit.com"
            path("api","v1","access_token")
            parameters.append("grant_type","authorization_code")
            parameters.append("code","jBQV2X5Fiw2JpMmRp_wp8zg80pW00w")
            parameters.append("redirect_uri","http://127.0.0.1")
            }
        poster.method = HttpMethod.Post
        poster.header("User-Agent","kreddit by /u/jokesterae")
        data class AuthResponse(val access_token:String,val token_type:String,val expires_in:Int,val scope:String,val refresh_token:String)
        val response = client.post<AuthResponse>(poster)
    }
}



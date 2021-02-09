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
import io.ktor.utils.io.ByteReadChannel
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient() {
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
        val getter = HttpRequestBuilder()
        getter.url {
            host = "reddit.com"
            path("api","v1","authorize")
            parameters.append("client_id","j5Bx7_Df0_wxdQ")
            parameters.append("response_type","code")
            parameters.append("state","kreddittest")
            parameters.append("redirect_uri","http://127.0.0.1")
            parameters.append("duration","permanent")
            parameters.append("scope","identity edit flair history mysubreddits read report save submit subscribe vote")
        }
        getter.method=HttpMethod.Get

        val poster: HttpRequestBuilder = HttpRequestBuilder()
        poster.url {
            host = "www.reddit.com"
            path("api","v1","access_token")
            parameters.append("grant_type","authorization_code")
            parameters.append("code","")
            parameters.append("redirect_uri","http://127.0.0.1")
            }
        poster.method = HttpMethod.Post
        poster.header("User-Agent","kreddit by /u/jokesterae")
        data class TokenResponse(val access_token:String,val token_type:String,
                                val expires_in:Int,val scope:String,val refresh_token:String)
        data class AuthResponse(val error: String,val code:String,val state:String)

        val response = client.get<ByteReadChannel>(getter)
        print(response)
    }

}



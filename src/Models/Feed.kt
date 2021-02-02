package com.kreddit.Models
import com.kreddit.Models.Article
data class Feed (val kind: String, var data:Data)
data class Data(val modhash:String,val dist:Int,val children:List<Article>)
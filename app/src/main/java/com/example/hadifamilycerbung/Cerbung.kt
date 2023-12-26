package com.example.hadifamilycerbung

import java.util.Date

data class User(val id:Int, val username:String, val urlProfile:String, val password:String)
data class Cerbung(val id:Int, val title:String, val urlPhoto:String, val description:String, val type:String, val userId:Int, val genreId:Int, val createDate: String)

data class CerbungFollowing(val id:Int)
data class Paragraph(val id:Int, val cerbungId:Int, val userId:Int, val content:String)
class Genre(val id:Int, val name:String){
    override fun toString(): String {
        return name
    }
}
data class Notification(val date: Date, val user:String, val description:String, val type:String)
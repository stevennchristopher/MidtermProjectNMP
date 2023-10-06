package com.example.hadifamilycerbung

data class User(val id:Int, val username:String, val urlProfile:String, val password:String)
data class Cerbung(val id:Int, val url:String, val title:String, val userId:Int, val number:Int, val thumbs:Int, val description:String)
data class Comment(val cerbungId:Int, val userId:Int, val comment:String)
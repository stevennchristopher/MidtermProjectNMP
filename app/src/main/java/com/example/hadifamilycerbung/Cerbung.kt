package com.example.hadifamilycerbung

data class User(val id:Int, val username:String, val urlProfile:String, val password:String)
data class Cerbung(val id:Int, val url:String, val title:String, val userId:Int, val number:Int, val thumbs:Int, val description:String, val genre:String, val tipe:String)
data class Paragraph(val id:Int, val cerbungId:Int, val userId:Int, val content:String)
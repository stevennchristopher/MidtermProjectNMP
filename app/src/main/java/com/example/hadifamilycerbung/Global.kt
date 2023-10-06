package com.example.hadifamilycerbung

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Global {
    val cerbung =
        arrayListOf(
            Cerbung(1, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno", 1, 13, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. "),
            Cerbung(2, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno Chapter 2", 2, 13, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. "),
            Cerbung(3, "https://coc.guide/static/imgs/troop/barbarian.png", "Rahasia Update Terbaru Clash of Clans", 3, 5, 200, "\"Rahasia Update Terbaru Clash of Clans\" blablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalbla. "),
            Cerbung(4, "https://picsum.photos/500", "Rahasia Gedung TF", 3, 2, 10, "\"Rahasia Gedung TF\" Gedung TF atau yang biasa disebut gedung teknik oleh warga sekitar, adalah bangunan yang selalu menyimpan sejuta misteri. Terletak di tengah Universitas Surabaya yang lebat, gedung ini sudah berdiri sejak bertahun-tahun lalu, namun sampai saat ini hampir tidak ada yang tahu apa misteri di balik bangunan kuno tersebut")
        )

    val userData =
        arrayListOf(
            User(1, "steven", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "test123"),
            User(2, "melissa", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "test123"),
            User(3, "ryan", "https://coc.guide/static/imgs/troop/barbarian.png", "h")
        )

    val cerbungNotification =
        arrayListOf(
            Notification(SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("09/09/2023"), "bluby82", "Request to Contributes", "Respond"),
            Notification(SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("07/09/2023"), "ryanryan", "publish new cerbung", "View")
        )
}
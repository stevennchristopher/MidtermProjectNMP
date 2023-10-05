package com.example.hadifamilycerbung

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Global {
    val cerbungHome =
        arrayListOf(
            CerbungHome(1, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno", "by NebulaNomad", 13, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. "),
            CerbungHome(2, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno Chapter 2", "by NebulaNomad", 13, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. ")
        )

    val userData =
        arrayListOf(
            User(1, "steven", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "test123"),
            User(2, "melissa", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "test123")
        )

    val cerbungNotification =
        arrayListOf(
            Notification(SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("09/09/2023"), "bluby82", "Request to Contributes", "Respond"),
            Notification(SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("07/09/2023"), "ryanryan", "publish new cerbung", "View")
        )
}
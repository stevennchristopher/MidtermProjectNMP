package com.example.hadifamilycerbung

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Global {
    val cerbung =
        arrayListOf(
            Cerbung(1, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno", 1, 2, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. ", "Misteri", "Restricted"),
            Cerbung(2, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno Chapter 2", 2, 2, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. ", "Misteri", "Restricted"),
            Cerbung(3, "https://coc.guide/static/imgs/troop/barbarian.png", "Rahasia Update Terbaru Clash of Clans", 3, 2, 200, "\"Rahasia Update Terbaru Clash of Clans\" blablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalblablablbablalblalbla. ", "Kocak", "Public"),
            Cerbung(4, "https://picsum.photos/500", "Rahasia Gedung TF", 3, 3, 10, "\"Rahasia Gedung TF\" Gedung TF atau yang biasa disebut gedung teknik oleh warga sekitar, adalah bangunan yang selalu menyimpan sejuta misteri. Terletak di tengah Universitas Surabaya yang lebat, gedung ini sudah berdiri sejak bertahun-tahun lalu, namun sampai saat ini hampir tidak ada yang tahu apa misteri di balik bangunan kuno tersebut", "Horror", "Public")
        )

    val paragraph =
        arrayListOf(
            Paragraph(1, 1, 1,"Di perpustakaan kuno, tersembunyi naskah rahasia. Seekor kucing jadi penunjuk jalan. Rahasia penjaga perpustakaan."),
            Paragraph(2,1,2,"Pemilik naskah rahasia pernah menjadi raja. Kucing menjaga pintu tersembunyi. Penjaga perpustakaan menjaga rahasia dengan teliti. Hanya yang berani berbagi kisah-kisah ini."),
            Paragraph(3,3,3,"Clash of Clans adalah permainan strategi mobile yang populer. Pemain membangun desa, melatih pasukan, dan berperang dengan pemain lain. Game ini menggabungkan strategi dan kolaborasi dalam pertempuran untuk mencapai kemenangan."),
            Paragraph(4,3,1,"Dalam permainan ini, pemain juga dapat bergabung dengan klan, bekerja sama dalam perang klan, dan berpartisipasi dalam acara-acara khusus. Clash of Clans telah menjadi salah satu game mobile yang sangat disukai dan terus menghibur pemain di seluruh dunia dengan berbagai tantangan dan perubahan yang terus diperbarui."),
            Paragraph(5,2,2, "Di perpustakaan kuno, ada buku terlarang. Hanya diketahui oleh penjaga. Rahasia itu mengubah nasib."),
            Paragraph(6,2,3, "Buku itu mengandung mantra kuno yang memiliki kekuatan luar biasa. Hanya beberapa yang berani mencari. Penjaga perpustakaan adalah penjaga terakhir rahasia ini. Mereka menjaga dengan tekun, menghadang para pencari."),
            Paragraph(7,4,1," Salah satu rahasia paling menarik adalah kamar rahasia yang tersembunyi di dalamnya. Kamar ini hanya diakses oleh segelintir orang yang memiliki pengetahuan khusus. Di dalamnya, terdapat arsip-arsip kuno, naskah rahasia, dan benda-benda bersejarah yang menjadi bagian penting dari sejarah UBAYA."),
            Paragraph(8,4,2,"Tidak hanya itu, ada juga lorong bawah tanah yang legendaris yang menghubungkan berbagai bangunan gedung Teknik. Beberapa mengatakan bahwa lorong ini memiliki hubungan dengan sejarah rahasia UBAYA dan mungkin digunakan sebagai rute pelarian pada masa lalu. Hanya segelintir orang yang tahu rahasia pintu-pintu akses ke lorong bawah tanah ini."),
            Paragraph(9,4,3,"Namun, rahasia terbesar gedung TF adalah mungkin tentang organisasi rahasia yang ada di dalamnya. Beberapa mahasiswa yang dipilih secara eksklusif terlibat dalam organisasi ini, dan mereka memiliki ritual-ritual dan tradisi-tradisi yang sangat rahasia. Keanggotaan mereka adalah hal yang paling dijaga kerahasiaannya, dan banyak yang tertarik untuk menjadi bagian dari kelompok misterius ini.")
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
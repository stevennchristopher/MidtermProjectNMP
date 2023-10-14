package com.example.hadifamilycerbung

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Global {
    val cerbung =
        arrayListOf(
            Cerbung(1, "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "Rahasia Terkunci di Perpustakaan Kuno", 1, 2, 5, "\"Rahasia Terkunci di Perpustakaan Kuno\" mengisahkan tentang seorang mahasiswa bernama Alex yang secara tak sengaja menemukan sebuah buku kuno yang misterius di perpustakaan universitasnya. ", "Misteri", "Restricted", SimpleDateFormat("dd/MM/yyyy").parse("09/09/2023")),
            Cerbung(2, "https://i.pinimg.com/originals/9e/29/85/9e29853fe479335a58bb773609b99527.png", "Rahasia Update Terbaru Clash of Clans", 3, 2, 200, "\"Rahasia Update Terbaru Clash of Clans\" Clash of Clans adalah permainan video strategi seluler freemium yang dikembangkan dan diterbitkan oleh pengembang permainan Finlandia, Supercell. Permainan ini dirilis untuk platform iOS pada 2 Agustus 2012, dan di Google Play untuk Android pada 7 Oktober 2013. ", "Kocak", "Public", SimpleDateFormat("dd/MM/yyyy").parse("16/05/2023")),
            Cerbung(3, "https://teknik.ubaya.ac.id/wp-content/uploads/sites/25/2023/01/Teknik.jpg", "Rahasia Gedung TF", 3, 3, 10, "\"Rahasia Gedung TF\" Gedung TF atau yang biasa disebut gedung teknik oleh warga sekitar, adalah bangunan yang selalu menyimpan sejuta misteri. Terletak di tengah Universitas Surabaya yang lebat, gedung ini sudah berdiri sejak bertahun-tahun lalu, namun sampai saat ini hampir tidak ada yang tahu apa misteri di balik bangunan kuno tersebut", "Horror", "Public", SimpleDateFormat("dd/MM/yyyy").parse("25/12/2022"))
        )

    val paragraph =
        arrayListOf(
            Paragraph(1,1,1,"Di perpustakaan kuno, tersembunyi naskah rahasia. Seekor kucing jadi penunjuk jalan. Rahasia penjaga perpustakaan."),
            Paragraph(2,1,2,"Pemilik naskah rahasia pernah menjadi raja. Kucing menjaga pintu tersembunyi. Penjaga perpustakaan menjaga rahasia dengan teliti. Hanya yang berani berbagi kisah-kisah ini."),
            Paragraph(3,2,2,"Clash of Clans adalah permainan strategi mobile yang populer. Pemain membangun desa, melatih pasukan, dan berperang dengan pemain lain. Game ini menggabungkan strategi dan kolaborasi dalam pertempuran untuk mencapai kemenangan."),
            Paragraph(4,2,3,"Dalam permainan ini, pemain juga dapat bergabung dengan klan, bekerja sama dalam perang klan, dan berpartisipasi dalam acara-acara khusus. Clash of Clans telah menjadi salah satu game mobile yang sangat disukai dan terus menghibur pemain di seluruh dunia dengan berbagai tantangan dan perubahan yang terus diperbarui."),
            Paragraph(5,3,3,"Salah satu rahasia paling menarik adalah kamar rahasia yang tersembunyi di dalamnya. Kamar ini hanya diakses oleh segelintir orang yang memiliki pengetahuan khusus. Di dalamnya, terdapat arsip-arsip kuno, naskah rahasia, dan benda-benda bersejarah yang menjadi bagian penting dari sejarah UBAYA."),
            Paragraph(6,3,1,"Tidak hanya itu, ada juga lorong bawah tanah yang legendaris yang menghubungkan berbagai bangunan gedung Teknik. Beberapa mengatakan bahwa lorong ini memiliki hubungan dengan sejarah rahasia UBAYA dan mungkin digunakan sebagai rute pelarian pada masa lalu. Hanya segelintir orang yang tahu rahasia pintu-pintu akses ke lorong bawah tanah ini."),
            Paragraph(7,3,2,"Namun, rahasia terbesar gedung TF adalah mungkin tentang organisasi rahasia yang ada di dalamnya. Beberapa mahasiswa yang dipilih secara eksklusif terlibat dalam organisasi ini, dan mereka memiliki ritual-ritual dan tradisi-tradisi yang sangat rahasia. Keanggotaan mereka adalah hal yang paling dijaga kerahasiaannya, dan banyak yang tertarik untuk menjadi bagian dari kelompok misterius ini.")
        )

    val userData =
        arrayListOf(
            User(1, "steven", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "s"),
            User(2, "melissa", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg", "m"),
            User(3, "ryan", "https://coc.guide/static/imgs/troop/barbarian.png", "h")
        )

    val genre =
        arrayListOf(
            Genre(1, "Misteri"),
            Genre(2, "Aksi"),
            Genre(3, "Horror"),
            Genre(4, "Kocak")
        )

    val cerbungNotification =
        arrayListOf(
            Notification(SimpleDateFormat("dd/MM/yyyy").parse("09/09/2023"), "bluby82", "Request to Contributes", "Respond"),
            Notification(SimpleDateFormat("dd/MM/yyyy").parse("07/09/2023"), "ryanryan", "publish new cerbung", "View")
        )
}
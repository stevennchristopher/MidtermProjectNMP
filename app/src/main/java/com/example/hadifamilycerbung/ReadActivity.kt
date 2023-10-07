package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hadifamilycerbung.databinding.ActivityReadBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding

    companion object {
        val id_cerbungHadiFamily = "idcerbung_random_1928391823"
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        val cerbungId = intent.getIntExtra(id_cerbungHadiFamily, 0)

        var cerbungIndex = 0
        for ((index, cerbung) in Global.cerbung.withIndex()) {
            if (cerbung.id == cerbungId) {
                cerbungIndex = index
            }
        }

        if (Global.cerbung[cerbungIndex].tipe.equals("Restricted")) {
            binding.imageView15.visibility = View.GONE
            binding.txtInputEditSearch.visibility = View.GONE
            binding.txtCharacters.visibility = View.GONE
            binding.btnSubmit.visibility = View.GONE
            binding.txtInputLayoutSearch.visibility = View.GONE
        }

        Picasso.get().load(Global.cerbung[cerbungIndex].url).into(binding.imgPoster)
        binding.txtJudul.setText(Global.cerbung[cerbungIndex].title)
        binding.txtParagraph.setText(Global.cerbung[cerbungIndex].number.toString())
        binding.txtLike.setText(Global.cerbung[cerbungIndex].thumbs.toString())
        binding.txtGenre.setText(Global.cerbung[cerbungIndex].genre)
        binding.txtCreator.setText(Global.userData[Global.cerbung[cerbungIndex].userId-1].username)

        val date = Global.cerbung[cerbungIndex].createDate
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val formattedDate = dateFormat.format(date)
        binding.txtDate.text = formattedDate

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewParagraphs.layoutManager = lm
        binding.recyclerViewParagraphs.setHasFixedSize(true)
        binding.recyclerViewParagraphs.adapter = ParagraphAdapter(cerbungId)

        binding.btnHome.setOnClickListener{
            finish()
        }
    }
}
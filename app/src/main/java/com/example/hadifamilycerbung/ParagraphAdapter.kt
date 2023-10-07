package com.example.hadifamilycerbung

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.ParagraphBinding

class ParagraphAdapter(val cerbung:Int): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>() {

    class ParagraphViewHolder(val binding: ParagraphBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val binding = ParagraphBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParagraphViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Global.paragraph.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
        with(holder.binding){
            if (Global.paragraph[position].cerbungId == cerbung){
                txtPara.text = Global.paragraph[position].content
                txtUsername.text = Global.userData[Global.paragraph[position].userId - 1].username
            }
            else {
                txtPara.isVisible = false
                txtUsername.isVisible = false
                btnLikeParagraph.isVisible = false
                iconAuthor.isVisible = false
            }
        }
    }


}
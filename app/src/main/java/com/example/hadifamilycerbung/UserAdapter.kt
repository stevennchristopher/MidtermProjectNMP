package com.example.hadifamilycerbung

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.UsersItemBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserAdapter (private val users:ArrayList<UserInUser>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(val binding: UsersItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val currentUser = users[position]

        with(holder.binding) {
            Picasso.get()
                .load(currentUser.urlProfile)
                .into(imgUserBg)

            txtUsernameItem.text = currentUser.username

            val sinceJoin = LocalDateTime.parse(currentUser.createdSince, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy")
            txtSince.text = "Since " + sinceJoin.format(outputFormatter)

            txtCerbungLikes.text = currentUser.totalLikes.toString()

//            btnReadCerbung.setOnClickListener {
//                val context = holder.itemView.context
//                val intent = Intent(context, ReadActivity::class.java)
//                intent.putExtra(FollowingAdapter.id_cerbungHadiFamily, currentCerbung.id)
//                intent.putExtra(ReadActivity.user_login_cerbungHadiFamily, userId)
//                context.startActivity(intent)
//            }
        }
    }

    fun updateData(newUser: List<UserInUser>) {
        users.clear()
        users.addAll(newUser)
        notifyDataSetChanged()
    }
}
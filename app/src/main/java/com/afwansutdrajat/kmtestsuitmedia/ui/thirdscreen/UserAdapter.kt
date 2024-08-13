package com.afwansutdrajat.kmtestsuitmedia.ui.thirdscreen

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afwansutdrajat.kmtestsuitmedia.data.local.SharedPref
import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.DataItem
import com.afwansutdrajat.kmtestsuitmedia.databinding.ListItemUserBinding
import com.afwansutdrajat.kmtestsuitmedia.ui.secondscreen.SecondScreenActivity
import com.bumptech.glide.Glide

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        if (review != null) {
            holder.bind(review)
        }
    }

    class MyViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem){
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.ivImage)
            val name = "${user.firstName} ${user.lastName}"
            name.also { binding.tvName.text = it }
            binding.tvEmail.text = user.email

            itemView.setOnClickListener {
                SharedPref.setSelectedName(itemView.context, name)
                val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
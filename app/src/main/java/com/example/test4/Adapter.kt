package com.example.test4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test4.databinding.ItemLayoutBinding


class FragmentAdapter :
    ListAdapter<Data, FragmentAdapter.ViewHolder>(DataDiffCallback()) {

    private class DataDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var data: Data

        fun bind() {
            data = currentList[adapterPosition]
            with(binding) {
                Glide.with(itemView)
                    .load(data.image)
                    .into(image)

                owner.text = data.owner
                lastMessage.text = data.lastMessage
                lastActive.text = data.lastActive
                unreadMessages.text = data.unreadMessages.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

}
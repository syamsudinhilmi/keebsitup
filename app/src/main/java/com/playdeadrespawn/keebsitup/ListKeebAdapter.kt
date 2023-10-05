package com.playdeadrespawn.keebsitup

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.playdeadrespawn.keebsitup.databinding.ItemRowKeyboardBinding

class ListKeebAdapter(private val lisKeyboard: ArrayList<Keyboard>): RecyclerView.Adapter<ListKeebAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowKeyboardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = lisKeyboard.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, description, specification, link, photo) = lisKeyboard[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemPrice.text = price
        holder.binding.tvItemDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(lisKeyboard[holder.adapterPosition])
        }
    }
    class ListViewHolder(var binding: ItemRowKeyboardBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Keyboard)
    }
}
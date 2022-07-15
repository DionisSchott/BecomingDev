package com.dionis.auladokevyn.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dionis.auladokevyn.databinding.ItemHomeMembersBinding

class MembersAdapter : RecyclerView.Adapter<MembersAdapter.Holder>(){

    private var membersItems: MutableList<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemHomeMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(membersList: List<Any>) {
        membersItems.addAll(membersList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(membersItems[position])
    }

    override fun getItemCount(): Int {
        return membersItems.size
    }

    class Holder(
        private val binding: ItemHomeMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var information: Any

        fun bind(information: Any) {
            this.information = information
        }
    }
}
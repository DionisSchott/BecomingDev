package com.dionis.becomingdev.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dionis.becomingdev.databinding.ItemHomeMembersBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.squareup.picasso.Picasso

class MembersAdapter : RecyclerView.Adapter<MembersAdapter.Holder>(){

    lateinit var onItemClicked: (MembersItem) -> Unit
    private var membersItems: MutableList<MembersItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemHomeMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClicked)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(membersList: List<MembersItem>) {
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
        private val binding: ItemHomeMembersBinding,
        private val onItemClicked: (MembersItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var information: MembersItem

        fun bind(membersInfo: MembersItem) {
            this.information = membersInfo

//            Picasso.get().load(membersInfo.Photos[0].url).into(binding.imgMembers)
            binding.TvMemberName.text = membersInfo.name
            binding.TvLanguage.text = membersInfo.technology

            binding.root.setOnClickListener{
                onItemClicked.invoke(membersInfo)
            }
        }
    }
}
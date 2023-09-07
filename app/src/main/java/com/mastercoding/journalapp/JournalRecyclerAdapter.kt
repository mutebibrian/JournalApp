package com.mastercoding.journalapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mastercoding.journalapp.databinding.JournalRowBinding
import kotlin.coroutines.coroutineContext

class JournalRecyclerAdapter(val context: Context, var journalList:List<Journal>)
    : RecyclerView.Adapter<JournalRecyclerAdapter.MyViewHolder>()
{

    lateinit var binding: JournalRowBinding


    // View Holder
    class MyViewHolder(var binding: JournalRowBinding) :
        RecyclerView.ViewHolder(binding.root ) {
            fun bind(journal: Journal){
                binding.journal  = journal




            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = JournalRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val journal = journalList[position]


        holder.bind(journal)

    }

    override fun getItemCount(): Int = journalList.size



}
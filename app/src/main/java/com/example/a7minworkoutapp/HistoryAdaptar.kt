package com.example.a7minworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkoutapp.databinding.ItemHistoryBinding

class HistoryAdaptar(private var items: ArrayList<String>):RecyclerView.Adapter<HistoryAdaptar.ViewHolder>() {




    class ViewHolder(binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        val tvitem=binding.tvitem
        val tvpos=binding.tvpos
        val historyitem=binding.historyitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String=items.get(position)
        holder.tvpos.text=(position+1).toString()
        holder.tvitem.text=date

        if(position%2==0){
            holder.historyitem.setBackgroundColor(
               Color.parseColor("#EBEBEB")
            )
        }
        else{
            holder.historyitem.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }

    }
}
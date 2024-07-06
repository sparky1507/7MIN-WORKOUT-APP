package com.example.a7minworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkoutapp.databinding.ActivityExerciseBinding
import com.example.a7minworkoutapp.databinding.ItemexerciseBinding

class exrciseadapter(val item: ArrayList<exercisemodel>):
    RecyclerView.Adapter<exrciseadapter.ViewHolder>() {

        class ViewHolder(binding: ItemexerciseBinding): RecyclerView.ViewHolder(binding.root){
            val tvitem=binding.tvtextview
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exrciseadapter.ViewHolder {
        return ViewHolder(ItemexerciseBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: exrciseadapter.ViewHolder, position: Int) {
        val model: exercisemodel = item[position]
        holder.tvitem.text=model.getid().toString()

        when{
            model.getisSelected()->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.itemcircularcolorselected)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))
            }
            model.getisCompleted()->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.itemcircularcolorcompleted)
                holder.tvitem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.itemcircularcolor)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))
            }
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }


}
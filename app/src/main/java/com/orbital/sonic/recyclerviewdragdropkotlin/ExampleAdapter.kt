package com.orbital.sonic.recyclerviewdragdropkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ExampleAdapter(private var mExampleList: ArrayList<ExampleItem>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(v, mListener!!)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val currentItem: ExampleItem = mExampleList[fromPosition]

        mExampleList.removeAt(fromPosition)
        mExampleList.add(toPosition,currentItem)

    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem: ExampleItem = mExampleList[position]
        holder.textViewName.text = currentItem.countryName
        holder.textViewPosition.text = "Position: $position"
        holder.mImojiImage.text = currentItem.emojiIcon
    }

    class ExampleViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var textViewPosition: TextView = itemView.findViewById(R.id.textViewPosition)
        var mImojiImage: TextView = itemView.findViewById(R.id.image_emoji)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }

        }


    }
}


package com.example.panoramic.app.ui.personalinformation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.remote.model.Item

class TransactionHistoryAdabter(private val list: List<Item>) : RecyclerView.Adapter<TransactionHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TransactionHistoryViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
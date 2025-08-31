package com.android_development.productscanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*

class HistoryAdapter :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(DIFF) {

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val qr = itemView.findViewById<TextView>(R.id.tvQrCode)
        val name = itemView.findViewById<TextView>(R.id.tvProductName)
        val time = itemView.findViewById<TextView>(R.id.tvTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false))

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.qr.text = "QR: ${item.qrCode}"
        holder.name.text = "Product: ${item.productName}"
        holder.time.text = DateFormat.getDateTimeInstance().format(Date(item.timestamp))
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(o: History, n: History) = o.id == n.id
            override fun areContentsTheSame(o: History, n: History) = o == n
        }
    }
}

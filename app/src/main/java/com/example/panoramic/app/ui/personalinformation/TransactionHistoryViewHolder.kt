package com.example.panoramic.app.ui.personalinformation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.data.entity.TransactionEntity

class TransactionHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_transaction, parent, false)) {
    private var mDateView: TextView? = null
    private var mTrackingCodeView: TextView? = null
    private var mScoreView: TextView? = null
    private var mPaidView: TextView? = null


    init {
        mDateView = itemView.findViewById(R.id.date)
        mTrackingCodeView = itemView.findViewById(R.id.tracking_code_value)
        mScoreView = itemView.findViewById(R.id.score_value)
        mPaidView = itemView.findViewById(R.id.paid_value)
    }

    fun bind(transaction: TransactionEntity) {
        mDateView?.text = transaction.date
        mTrackingCodeView?.text = transaction.trackingcode
        mScoreView?.text = transaction.score.toString()
        mPaidView?.text = transaction.paid
    }

}
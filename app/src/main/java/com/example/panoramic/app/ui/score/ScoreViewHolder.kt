package com.example.panoramic.app.ui.score

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.data.entity.ScoreEntity

class ScoreViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_score, parent, false)) {
    private var mModelNumberView: TextView? = null
    private var mSerialNumberView: TextView? = null
    private var mScoreView: TextView? = null
    private var mDateAndTimeView: TextView? = null
    private var mCheckBoxView: CheckBox? = null
    private var mCheckBoxTextView: TextView? = null

    private val register = "دستگاه توسط خریدار ثبت شده است"
    private val notRegister = "دستگاه هنوز توسط خریدار ثبت نشده است"


    init {
        mModelNumberView = itemView.findViewById(R.id.model_number)
        mSerialNumberView = itemView.findViewById(R.id.serial_value)
        mScoreView = itemView.findViewById(R.id.score_value)
        mDateAndTimeView = itemView.findViewById(R.id.date_value)
        mCheckBoxView = itemView.findViewById(R.id.checkBox)
        mCheckBoxTextView = itemView.findViewById(R.id.text_checkBox)
    }

    fun bind(score: ScoreEntity) {
        mModelNumberView?.text = score.modelNumber
        mSerialNumberView?.text = score.serialNumber
        mScoreView?.text = score.score.toString()
        mDateAndTimeView?.text = score.date + " " + score.time
        mCheckBoxView?.isChecked = score.register
        mCheckBoxTextView?.text = if (score.register) register else notRegister
    }

}
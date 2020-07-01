package com.example.panoramic.app.ui.personalinformation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.movies.MoviesAdabter
import com.example.panoramic.data.entity.MoviesEntity
import com.example.panoramic.data.entity.TransactionEntity
import com.example.panoramic.databinding.FragmentMoviesBinding

class TransactionHistoryDialogFragment : DialogFragment()  {

    private val mNicolasCageMovies = listOf(
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        ),
        TransactionEntity(
            "1999/12/6",
            "7398462946209882",
            50,
            "5,364,265"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_transaction_history_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.transaction_history_recyclerview)?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TransactionHistoryAdabter(mNicolasCageMovies)
        }

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1500)
    }
}
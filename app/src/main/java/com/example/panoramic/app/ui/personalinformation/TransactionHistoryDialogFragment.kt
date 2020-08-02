package com.example.panoramic.app.ui.personalinformation

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.Item
import com.example.panoramic.remote.model.TransactionDto
import com.example.panoramic.remote.service.TransactionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransactionHistoryDialogFragment : DialogFragment()  {

    private val mNicolasCageMovies = listOf(
        Item(
            "1999/12/6",
            "7398462946209882",
            "50",
            "5,364,265"
        ),
        Item(
            "1999/12/6",
            "7398462946209882",
            "50",
            "5,364,265"
        )
    )

    lateinit var transactionList: List<Item>

    private val _transactionSuccess = MutableLiveData<Boolean>()
    private val transactionSuccess: LiveData<Boolean>
        get() = _transactionSuccess


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_transaction_history_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        view.findViewById<RecyclerView>(R.id.transaction_history_recyclerview)?.apply {
            layoutManager = LinearLayoutManager(activity)
            transactionSuccess.observe(viewLifecycleOwner, Observer {
                if(it) {
                    adapter = TransactionHistoryAdabter(transactionList)
                    dialog?.window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1500)
                }
            })
        }

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
        val cookie = sharedPref!!.getString("COOKIE", "")
        getTransactionList(cookie)

        return dialog
    }

    private fun getTransactionList(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TransactionService::class.java)
        val call = service.getTransactionList(cookie!!)
        call.enqueue(object : Callback<TransactionDto> {
            override fun onResponse(
                call: Call<TransactionDto>,
                response: Response<TransactionDto>
            ) {
                if (response.code() == 200) {
                    Log.i("ddd", response.body().items.toString())
                    transactionList = response.body().items
                    _transactionSuccess.value = response.body().success
                    view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<TransactionDto>, t: Throwable) {
                view!!.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            }
        })
    }
}
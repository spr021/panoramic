package com.example.panoramic.app.ui.personalinformation

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.ChangePasswordBody
import com.example.panoramic.remote.model.ChangePasswordDto
import com.example.panoramic.remote.service.ChangePasswordService
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordDialogFragment : DialogFragment() {

    private val _changePassword = MutableLiveData<Boolean>()
    private val changePassword: LiveData<Boolean>
        get() = _changePassword

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.confirm).setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
            val cookie = sharedPref!!.getString("COOKIE", "")
            val oldPassword =
                view.findViewById<EditText>(R.id.current_password).text.toString().trim()
            val newPassword =
                view.findViewById<EditText>(R.id.new_password_change).text.toString().trim()
            val repeatNewPassword =
                view.findViewById<EditText>(R.id.repeat_new_password_change).text.toString().trim()
            if (newPassword == repeatNewPassword) {
                if (oldPassword.nonEmpty() && newPassword.nonEmpty() && repeatNewPassword.nonEmpty()) {
                    if (newPassword.minLength(8)) {
                        if(newPassword != oldPassword){
                            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                            view.findViewById<Button>(R.id.confirm).visibility = View.GONE
                            changePassword(oldPassword, newPassword, cookie)
                        } else {
                            val errorText = view.findViewById<TextView>(R.id.message)
                            errorText?.text = "رمز عبور جدید با رمز عبور فعلی یکی است"
                            errorText?.visibility = View.VISIBLE
                        }
                    } else {
                        val errorText = view.findViewById<TextView>(R.id.message)
                        errorText?.text = "رمز عبور باید حداقل ۸ کاراکتر باشد"
                        errorText?.visibility = View.VISIBLE
                    }
                } else {
                    val errorText = view.findViewById<TextView>(R.id.message)
                    errorText?.text = "همه ی فیلد هارا پر کنید"
                    errorText?.visibility = View.VISIBLE
                }
            } else {
                val errorText = view.findViewById<TextView>(R.id.message)
                errorText?.text = "رمز عبور جدید با تکرار رمز عبور یکی نیست"
                errorText?.visibility = View.VISIBLE
            }

        }
        changePassword.observe(viewLifecycleOwner, Observer {
            if (it) {
                CustomToast(requireActivity(), "رمز عبور با موفقیت تغغیر کرد", R.color.green)
                dialog?.dismiss()
            } else {
                CustomToast(requireActivity(), "درخواست رد شد", R.color.red)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_fragment_perssonal_information, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private fun changePassword(oldPassword: String, newPassword: String, cookie: String?) {
        val body = ChangePasswordBody(
            cookie!!,
            newPassword,
            oldPassword
        )
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ChangePasswordService::class.java)
        val call = service.changePassword(body)
        call.enqueue(object : Callback<ChangePasswordDto> {
            override fun onResponse(
                call: Call<ChangePasswordDto>,
                response: Response<ChangePasswordDto>
            ) {
                if (response.code() == 200) {
                    _changePassword.value = response.body()!!.success
                    view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE
                    view?.findViewById<Button>(R.id.confirm)!!.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ChangePasswordDto>, t: Throwable) {
            }
        })
    }
}
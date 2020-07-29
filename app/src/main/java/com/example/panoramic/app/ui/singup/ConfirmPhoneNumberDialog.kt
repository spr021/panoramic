package com.example.panoramic.app.ui.singup

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
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.singup.viewmodel.SMSViewModel


class ConfirmPhoneNumberDialog : DialogFragment()  {

    private lateinit var viewModel: SMSViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SMSViewModel::class.java)

        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
            .getString("COOKIE", "")

        val bundle = arguments

        val phoneNumber = bundle!!.getString("PhoneNumber")

        view.findViewById<TextView>(R.id.phone_number).text = phoneNumber

        view.findViewById<Button>(R.id.confirm).setOnClickListener {
            viewModel.onResendCodeClick(
                cookie,
                phoneNumber
            )
            dialog?.dismiss()
        }

        view.findViewById<Button>(R.id.edit).setOnClickListener {
            findNavController().navigate(
                R.id.action_SMSFragment_to_phoneFragment
            )
            dialog?.dismiss()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_confirm_phone_number, container, false)
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
}
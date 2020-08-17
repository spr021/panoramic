package com.example.panoramic.app.ui.personalinformation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.databinding.FragmentPersonalInformationBinding
import pub.devrel.easypermissions.EasyPermissions
import java.io.File


@Suppress("DEPRECATION")
class PersonalInformationFragment : Fragment(R.layout.fragment_personal_information), EasyPermissions.PermissionCallbacks {

    private var fragmentPersonalInformationBinding: FragmentPersonalInformationBinding? = null
    private lateinit var viewModel: PersonalInformationViewModel
    private lateinit var uri: Uri

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPersonalInformationBinding.bind(view)
        fragmentPersonalInformationBinding = binding

        viewModel = ViewModelProviders.of(this).get(PersonalInformationViewModel::class.java)

        binding.personalViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userInfo?.observe(viewLifecycleOwner, Observer {
            if (it!!.success) {
                binding.loading.visibility = View.GONE
            }
        })

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getUserInfo(cookie)

        binding.uploadProfilePictuer.setOnClickListener {
            val intent = Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_PICK)

            startActivityForResult(intent, 200)
        }

        viewModel.uploadPhoto?.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.profilePictuerScore.setImageDrawable(null)
                binding.profilePictuerScore.setImageURI(uri)
                CustomToast(requireActivity(), "آپلود عکس با موفقیت انجام شد", R.color.green)
            } else {
                CustomToast(requireActivity(), "آپلود عکس انجام نشد", R.color.red)
            }
        })

        binding.logout.setOnClickListener {
            viewModel.userInfo?.observe(viewLifecycleOwner, Observer {
                viewModel.logout(it!!.id, cookie!!)
            })
        }
        viewModel.userLogout?.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_personal_information_to_loginFragment)
            }
        })

        binding.changePassword.setOnClickListener {
            val newFragment = ChangePasswordDialogFragment()
            newFragment.show(requireFragmentManager(), "ChangingPassword")
        }

        binding.transactionHistory.setOnClickListener {
            val newFragment = TransactionHistoryDialogFragment()
            newFragment.show(requireFragmentManager(), "TransactionHistory")
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }




    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 200) {
            try {
                uri = data?.data!!
                if (EasyPermissions.hasPermissions(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    val filePath = getRealPathFromURIPath(uri, requireActivity())
                    val file = File(filePath)
                    // check image size under 1 MG
                    val length: Long = file.length()
                    if(length <= 1000000) {
                        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                            .getString("COOKIE", "")
                        viewModel.uploadImage(cookie!!, file, context)
                    } else {
                        CustomToast(requireActivity(), "حجم فایل باید کمتر از یک مگابایت باید", R.color.red)
                    }
                } else {
                    EasyPermissions.requestPermissions(
                        this,
                        "برای اپلود عکس جدید نیاز به اجازه ی شما هست",
                        300,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            } catch (e: Throwable) {

            }
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d("onPermissionsDenied", "Permission has been denied");
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        try {
            val filePath = getRealPathFromURIPath(uri, requireActivity())
            val file = File(filePath)
            val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                .getString("COOKIE", "")
            viewModel.uploadImage(cookie!!, file, context)
        }catch (e: Throwable){
        }

    }


    @SuppressLint("Recycle")
    fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String? {
        val cursor: Cursor? = activity.contentResolver.query(contentURI, null, null, null, null)
        return if (cursor == null) {
            contentURI.path
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }

    override fun onDestroyView() {
        fragmentPersonalInformationBinding = null
        super.onDestroyView()
    }

}
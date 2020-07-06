package com.example.panoramic.app.ui.registerproduct


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentBarcodeBinding
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.fragment_barcode.*
import java.util.*

class BarcodeFragment : Fragment(R.layout.fragment_barcode) {

    private var fragmentBarcodeBinding: FragmentBarcodeBinding? = null

    private lateinit var captureManager: CaptureManager
    private var torchState: Boolean = false
    lateinit var beepManager: BeepManager
    private var lastScan = Date()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBarcodeBinding.bind(view)
        fragmentBarcodeBinding = binding

        captureManager = CaptureManager(activity, barcodeView)
        captureManager.initializeFromIntent(Intent(), savedInstanceState)

        beepManager = BeepManager(activity)
        beepManager.isVibrateEnabled = true

        var callback = object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    val current = Date()
                    val diff = current.time - lastScan.time
                    if(diff >= 1000){
                        lastScan = current
                        beepManager.playBeepSoundAndVibrate()
                        if(it.text != null) {
                            findNavController().navigate(BarcodeFragmentDirections.actionBarcodeFragmentToRegisterProductFragment(it.text))
                        }
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            }
        }

        barcodeView.decodeContinuous(callback)

        btnTorch.setOnClickListener {
            if(torchState){
                torchState = false
                barcodeView.setTorchOff()
            } else {
                torchState = true
                barcodeView.setTorchOn()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        captureManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        captureManager.onResume()
    }

    override fun onDestroyView() {
        fragmentBarcodeBinding = null
        super.onDestroyView()
        barcodeView.barcodeView.stopDecoding()
        captureManager.onDestroy()
    }
}
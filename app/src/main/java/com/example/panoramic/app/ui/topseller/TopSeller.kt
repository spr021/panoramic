package com.example.panoramic.app.ui.topseller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.data.entity.TopSellerEntity

class TopSeller : Fragment() {

    private val mNicolasCageMovies = listOf(
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            1
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            2
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            3
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            4
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            5
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            6
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            7
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            8
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            9
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            10
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.top_seller_recyclerview).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TopSellerAdabter(mNicolasCageMovies)
        }

    }
}
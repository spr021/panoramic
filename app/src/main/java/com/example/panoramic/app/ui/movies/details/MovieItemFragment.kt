package com.example.panoramic.app.ui.movies.details

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentMovieItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toast_fragment_home.*

class MovieItemFragment : Fragment(R.layout.fragment_movie_item) {

    private var fragmentMovieItemBinding: FragmentMovieItemBinding? = null
    private lateinit var viewModel: MovieItemViewModel
    private lateinit var viewModelFactory: MovieItemViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory =
            MovieItemViewModelFactory(
                MovieItemFragmentArgs.fromBundle(
                    arguments!!
                )
            )
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieItemViewModel::class.java)

        val binding = FragmentMovieItemBinding.bind(view)
        fragmentMovieItemBinding = binding


        Picasso.get()
            .load(viewModel.args.image)
            .placeholder(R.drawable.placeholder_image)
            .into(binding.imageCover)

        binding.title.text = viewModel.args.title
        binding.text.text = viewModel.args.text


        binding.imageCover.setOnClickListener {
            view.findNavController().navigate(
                MovieItemFragmentDirections.actionMovieItemFragmentToPlayerFragment(
                    viewModel.args.title,
                    viewModel.args.image,
                    viewModel.args.text,
                    viewModel.args.video
                )
            )
        }
        if(arguments!!.getBoolean("seenVideo")){
            toast()
        }


    }

    private fun toast() {
        val toastText = "امتیاز ویدیو برای شما ثبت شد"
        val layout = layoutInflater.inflate(R.layout.toast_fragment_home,linearLayout)
        val myToast = Toast(activity)
        val textview = layout.findViewById<TextView>(R.id.custom_toast_message)
        textview.text = toastText
        myToast.duration = Toast.LENGTH_LONG
        myToast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
        myToast.view = layout
        myToast.show()
    }

    override fun onDestroyView() {
        fragmentMovieItemBinding = null
        super.onDestroyView()
    }
}
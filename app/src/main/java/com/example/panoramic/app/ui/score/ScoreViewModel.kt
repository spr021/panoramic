package com.example.panoramic.app.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request
import com.android.volley.Response
import com.example.panoramic.data.entity.ScoreEntity
import org.json.JSONObject

class ScoreViewModel : ViewModel() {
    private val _viewState: MutableLiveData<ScoreEntity> = MutableLiveData()
    val viewState: LiveData<ScoreEntity> = _viewState

    fun request() {
        //send request and get json convert to data class with Gson()
    }

    companion object{

    }
}
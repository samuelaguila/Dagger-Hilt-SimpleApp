package com.saam.dagger_hilt_basicapp.ui

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.saam.dagger_hilt_basicapp.R
import com.saam.dagger_hilt_basicapp.model.Blog
import com.saam.dagger_hilt_basicapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getBlogs().observe(this, Observer {dataState ->
            when(dataState){
                is Resource.Success -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                    Log.d(TAG, "Success: ${dataState.data}")
                }
                is Resource.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                    Log.d(TAG, "Error: ${dataState.exception.message}")
                }
                is Resource.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}




















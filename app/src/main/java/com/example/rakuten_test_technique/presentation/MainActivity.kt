package com.example.rakuten_test_technique.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rakuten_test_technique.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * The class entry point [MainActivity].
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
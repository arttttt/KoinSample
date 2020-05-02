package com.arttttt.koinsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.koinsample.ui.a.FragmentA

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, FragmentA())
                .commit()
        }
    }
}

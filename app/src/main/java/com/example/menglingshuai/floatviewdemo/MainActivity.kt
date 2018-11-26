package com.example.menglingshuai.floatviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

//    val addCameraPipActivity: AddCameraPipActivity
//        get() = PipLauncher.getInstance().getPipInstance("AddCameraPipActivity", AddCameraPipActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addCameraPipActivity = AddCameraPipActivity()
        addCameraPipActivity.showAddCamera()
    }
}

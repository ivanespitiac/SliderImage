package com.custom.sliderimage.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.custom.sliderimage.R
import com.custom.sliderimage.adapters.ViewPagerAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.layout_activity_full_screen_image.*

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Fresco.initialize(applicationContext)
        setContentView(R.layout.layout_activity_full_screen_image)
        getExtras()
    }

    fun getExtras() {
        val items = intent.extras.getStringArrayList("items")
        val position = intent.extras.getInt("position")
        viewPagerFullScreen.adapter = ViewPagerAdapter(context = this, items = items)
        viewPagerFullScreen.setCurrentItem(position, true)
        indicatorScreen.setViewPager(viewPagerFullScreen)
    }

    fun fullScreenMode() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun onclickComponents(view: View) {
        onBackPressed()
    }


}

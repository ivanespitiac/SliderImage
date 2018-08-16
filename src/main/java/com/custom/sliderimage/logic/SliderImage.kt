package com.custom.sliderimage.logic

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.custom.sliderimage.R
import com.custom.sliderimage.activities.FullScreenImageActivity
import com.custom.sliderimage.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_slider_image.view.*
import java.util.*

/**
 * Created by Ivan on 16/08/18.
 */

class SliderImage : LinearLayout {

    private var items = ArrayList<String>()

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_slider_image, this, true)
        orientation = VERTICAL
        vewPagerSlider.adapter = ViewPagerAdapter(context = context, items = items)
        indicator.setViewPager(vewPagerSlider)
    }

    fun getItems(): ArrayList<String> {
        return (vewPagerSlider.adapter as ViewPagerAdapter).items
    }

    fun setItems(items: ArrayList<String>) {
        (vewPagerSlider.adapter as ViewPagerAdapter).setItemsPager(items)
        vewPagerSlider.adapter!!.notifyDataSetChanged()
        indicator.setViewPager(vewPagerSlider)
    }

    fun openfullScreen(items: ArrayList<String>, position: Int) {
        val intent = Intent(context, FullScreenImageActivity::class.java)
        intent.putExtra("items", items)
        intent.putExtra("position", position)
        context.startActivity(intent)
    }

}
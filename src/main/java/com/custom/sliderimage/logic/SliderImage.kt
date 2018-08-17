package com.custom.sliderimage.logic

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.custom.sliderimage.R
import com.custom.sliderimage.activities.FullScreenImageActivity
import com.custom.sliderimage.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_slider_image.view.*
import me.relex.circleindicator.CircleIndicator
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

    companion object {

        fun openfullScreen(context: Context, items: ArrayList<String>, position: Int) {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra("items", items)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    fun getIndicator(): CircleIndicator {
        return indicator
    }

    fun getItems(): ArrayList<String> {
        return (vewPagerSlider.adapter as ViewPagerAdapter).items
    }

    fun setItems(items: ArrayList<String>) {
        (vewPagerSlider.adapter as ViewPagerAdapter).setItemsPager(items)
        vewPagerSlider.adapter!!.notifyDataSetChanged()
        indicator.setViewPager(vewPagerSlider)
    }

    fun onPageListener(onPageScroll: (position: Int, offSet: Float, offSetPixels: Int) -> Unit,
                       onPageSelected: (position: Int) -> Unit,
                       onPageStateChange: (state: Int) -> Void): ViewPager.OnPageChangeListener {
        return (object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                onPageScroll(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                onPageStateChange(state)
            }
        })
    }

    fun openfullScreen(items: ArrayList<String>, position: Int) {
        val intent = Intent(context, FullScreenImageActivity::class.java)
        intent.putExtra("items", items)
        intent.putExtra("position", position)
        context.startActivity(intent)
    }

}
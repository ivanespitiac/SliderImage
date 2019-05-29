package com.custom.sliderimage.logic

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.custom.sliderimage.R
import com.custom.sliderimage.activities.FullScreenImageActivity
import com.custom.sliderimage.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_slider_image.view.*
import me.relex.circleindicator.CircleIndicator

/**
 * Created by Ivan on 16/08/18.
 */

@SuppressWarnings("all")
class SliderImage : LinearLayout {

    // Array of Image URLs
    private var items: List<String> = listOf()

    // Timer schedule
    private var inTimer = false
    private var timeLong = 2000.toLong()

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

        viewPagerSlider.adapter = ViewPagerAdapter(context = context, items = items)
        indicator.setViewPager(viewPagerSlider)
    }

    /**
     * Get Circle indicator component
     * @return CircleIndicator
     */
    fun getIndicator(): CircleIndicator {
        return indicator
    }

    /**
     * Get items of view pager (Image URLs)
     * @return list of string
     */
    fun getItems(): List<String> {
        return items
    }

    /**
     * Add new items "Image URLs"
     * @param items
     */
    fun setItems(items: List<String>) {
        (viewPagerSlider.adapter as? ViewPagerAdapter)?.setItemsPager(items)
        viewPagerSlider.adapter?.notifyDataSetChanged()
        this@SliderImage.items = items
        indicator.setViewPager(viewPagerSlider)
    }

    /**
     * Page events of view pager
     * @param onPageScroll ->
     * @param onPageSelected ->
     * @param onPageStateChange ->
     */
    fun onPageListener(onPageScroll: (position: Int, offSet: Float, offSetPixels: Int) -> Unit,
                       onPageSelected: (position: Int) -> Unit,
                       onPageStateChange: (state: Int) -> Unit) {
        val onPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                onPageStateChange(state)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                onPageScroll(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                onPageSelected(position)
            }
        }
        viewPagerSlider.addOnPageChangeListener(onPageChangeListener)
    }

    /**
     * Add timer task for change the current page
     * @param time
     */
    fun addTimerToSlide(time: Long) {
        this.timeLong = time
        this.inTimer = true
        goNextPage()
    }

    /**
     * Remove timer task
     */
    fun removeTimerSlide() {
        this.inTimer = false
    }

    /**
     * Action go to next page of view pager
     */
    private fun goNextPage() {
        Handler().postDelayed({
            if (inTimer) {
                if (viewPagerSlider.currentItem == (items.size-1)) {
                    viewPagerSlider.setCurrentItem(0, true)
                } else {
                    viewPagerSlider.setCurrentItem(viewPagerSlider.currentItem + 1, true)
                }
            }
            goNextPage()
        }, timeLong)
    }

    /**
     * Open full screen activity
     * @param position
     */
    fun openfullScreen(position: Int = 0) {
        val intent = Intent(context, FullScreenImageActivity::class.java)
        intent.putExtra("items", items.toTypedArray())
        intent.putExtra("position", position)
        context.startActivity(intent)
    }

    companion object {

        /**
         * Open full screen activity from external form
         * @param context
         * @param items
         * @param position
         */
        fun openfullScreen(context: Context, items: List<String>, position: Int) {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra("items", items.toTypedArray())
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

}
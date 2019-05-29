package com.custom.sliderimage.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.custom.sliderimage.activities.FullScreenImageActivity
import com.custom.sliderimage.zoomable.DoubleTapGestureListener
import com.custom.sliderimage.zoomable.ZoomableDraweeView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Ivan on 16/08/18.
 */
class ViewPagerAdapter(val context: Context, var items: List<String>): PagerAdapter() {

    init {
        Fresco.initialize(context.applicationContext)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        return if (context is FullScreenImageActivity) {
            val item = ZoomableDraweeView(context)
            item.setAllowTouchInterceptionWhileZoomed(true)
            item.setIsLongpressEnabled(false)
            item.setTapListener(DoubleTapGestureListener(item))
            item.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            item.controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(items[position]))
                    .build()
            container.addView(item)
            item
        } else {
            val item = SimpleDraweeView(context)
            item.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            item.setImageURI(items[position])
            item.scaleType = ImageView.ScaleType.CENTER

            item.setOnClickListener {
                val intent = Intent(context, FullScreenImageActivity::class.java)
                intent.putExtra("items", items.toTypedArray())
                intent.putExtra("position", position)
                context.startActivity(intent)
            }

            container.addView(item)
            item
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return items.indexOf(`object`)
    }

    /**
     * Destroy unused element from view pager
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object` as View))
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1 as View
    }

    override fun getCount(): Int {
        return items.size
    }

    /**
     * Set new items for view pager
     * @param items
     */
    fun setItemsPager(items: List<String>) {
        this.items = items
        this.notifyDataSetChanged()
    }

}

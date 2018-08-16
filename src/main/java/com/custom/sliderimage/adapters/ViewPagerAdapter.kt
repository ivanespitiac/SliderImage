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
import com.bosong.frescozoomablelib.zoomable.ZoomableDraweeView
import com.custom.sliderimage.activities.FullScreenImageActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import java.util.*

/**
 * Created by Ivan on 16/08/18.
 */
class ViewPagerAdapter(val context: Context, var items: ArrayList<String>): PagerAdapter() {

    init {
        Fresco.initialize(context.applicationContext)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        return if (context is FullScreenImageActivity) {
            val item = ZoomableDraweeView(context)
            item.setAllowTouchInterceptionWhileZoomed(true)
            item.setTapListener({})
            item.setIsLongpressEnabled(false)
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
            item.setOnClickListener(openExpandImage(position))
            container.addView(item)
            item
        }
    }

    override fun getItemPosition(`object`: Any?): Int {
        return items.indexOf(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //if (context is FullScreenImageActivity) {
        //    container.removeViewAt(position)
        //}
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1 as View
    }

    override fun getCount(): Int {
        return items.size
    }

    fun setItemsPager(items: ArrayList<String>) {
        this.items = items
        this.notifyDataSetChanged()
    }

    fun openExpandImage(position: Int): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra("items", items)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }
}

private fun ZoomableDraweeView.setTapListener(function: () -> Unit) {
    Log.d("Librari", "tap gesture ")
}

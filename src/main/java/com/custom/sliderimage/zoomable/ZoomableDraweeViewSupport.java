package com.custom.sliderimage.zoomable;

/**
 * Created by Ivan on 14/09/18.
 */

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;

/**
 * DraweeView that has zoomable capabilities.
 * <p>
 * Once the image loads, pinch-to-zoom and translation gestures are enabled.
 */
public class ZoomableDraweeViewSupport extends ZoomableDraweeView {

    private static final Class<?> TAG = ZoomableDraweeViewSupport.class;

    public ZoomableDraweeViewSupport(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public ZoomableDraweeViewSupport(Context context) {
        super(context);
    }

    public ZoomableDraweeViewSupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomableDraweeViewSupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Class<?> getLogTag() {
        return TAG;
    }

    @Override
    protected ZoomableController createZoomableController() {
        return AnimatedZoomableControllerSupport.newInstance();
    }
}

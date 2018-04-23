package com.yq.zhihurecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/23.
 */

public class AdImageView extends android.support.v7.widget.AppCompatImageView {

    private int minDY;
    private int mDY;

    public AdImageView(Context context) {
        this(context, null);
    }

    public AdImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        minDY = h;
    }

    /**
     * 根据不同条件算出图片 drawable 绘制应该移动的距离
     * 1.算出 drawable 绘制最大可移动距离，即 (getDrawable().getBounds().height() - minDY
     * 2.算出 对应图片 item 可移动距离，即 maxHeight - minDY
     * 3.算出两者之间的比例
     * 4.比例乘以图片对应 item 实际滑动距离，即是绘制图片时画布要移动的距离。由于画布开始移动是在图片对应
     * item 完全显示出来的位置，所以，计算实际距离时要先减去 minDY算出此时相对于初始位置时的实际滑动距离
     * 再乘以两者的对应比例
     *
     * @param dy        图片所在 item 已经滑动的距离
     * @param maxHeight 图片所在 item 的最大滑动距离
     */
    public void setdY(int dy, int maxHeight) {
        if (getDrawable() == null) return;
        mDY = dy - minDY;
        if (dy < minDY)
            mDY = 0;
        else if (dy > maxHeight)
            mDY = (int) ((getDrawable().getBounds().height() - minDY) * 1.0f / (maxHeight - minDY) * (maxHeight - minDY) + 0.5f);
        else {
            mDY = (int) ((getDrawable().getBounds().height() - minDY) * 1.0f / (maxHeight - minDY) * (dy - minDY) + 0.5f);
        }
        Log.i("imageview", "drawableHeight = " + getDrawable().getBounds().height() + " mDY = " + mDY + " minDy = " + minDY);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        int w = getWidth();
        int h = (int) (w * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        //获取的是图片经过缩放后的实际像素值
        Log.i("imageview", "width = " + drawable.getIntrinsicWidth() + " height = " + drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, -mDY);
        super.onDraw(canvas);
        canvas.restore();
    }


}

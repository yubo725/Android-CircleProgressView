package com.test.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yubo on 2017/5/26.
 */

public class ProgressView extends View {

    private Paint progressPaint;
    private int width, height;
    private int strokeWidth;
    private int topBottomPadding;
    private int startY;
    private int stopX;
    private int leftRightPadding;
    private int progress = 0;
    private int maxProgress;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.progress_view_bg);
        progressPaint = new Paint();
        progressPaint.setColor(Color.parseColor("#EF9B14"));
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        leftRightPadding = height / 2;
        topBottomPadding = height / 6;
        maxProgress = width - 2 * leftRightPadding;
        stopX = leftRightPadding;
        strokeWidth = height - 2 * topBottomPadding;
        progressPaint.setStrokeWidth(strokeWidth);
        startY = height / 2;
    }

    public void setProgress(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 100) {
            n = 100;
        }
        this.progress = n;
        postInvalidate();
    }

    private void drawProgress(Canvas canvas) {
        // 这里的计算不能放在setProgress的postInvalidate()之前，否则计算的stopX不正确
        stopX = leftRightPadding + (int)(progress / 100.0 * maxProgress); 
        canvas.drawLine(leftRightPadding, startY, stopX , startY, progressPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawProgress(canvas);
    }
}

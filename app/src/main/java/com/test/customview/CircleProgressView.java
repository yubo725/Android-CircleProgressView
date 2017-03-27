package com.test.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2017/3/24.
 * 自定义的圆环进度条
 */

public class CircleProgressView extends View {

    //圆形进度条的宽高
    private int mWidth;
    private int mHeight;
    //圆心坐标
    private int mCenterX;
    private int mCenterY;
    //外环的半径
    private int mRadiusBig;
    //内环的半径
    private int mRadiusSmall;
    //进度条的半径
    private int mProgressRadius;
    //圆环的线宽度
    private int mStrokeWidth = 8;
    //圆环的画笔
    private Paint mCirclePaint;
    //进度的画笔
    private Paint mProgressPaint;
    //两个圆环间的宽度
    private int mCircleWidth = 50;
    //进度值（0-100）
    private int progress = 0;

    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化操作
    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(mStrokeWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(Color.parseColor("#6699FF"));

        mProgressPaint = new Paint();
        mProgressPaint.setColor(Color.RED);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mCircleWidth);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    //设置圆环的颜色
    public void setCircleColor(int color) {
        if (mCirclePaint != null)
            mCirclePaint.setColor(color);
    }

    //设置进度的颜色
    public void setProgressColor(int color) {
        if (mProgressPaint != null)
            mProgressPaint.setColor(color);
    }

    //设置进度
    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("progress value is invalid!");
        }
        this.progress = progress;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取ProgressView的宽高
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        //根据宽高中较小的一个值，除以3来确定外圆环的半径
        mRadiusBig = mWidth > mHeight ? mHeight / 3 : mWidth / 3;
        //计算内圆环的半径
        mRadiusSmall = mRadiusBig - mCircleWidth - mStrokeWidth;
        //计算进度圈的半径
        mProgressRadius = mRadiusSmall + mCircleWidth / 2 + mStrokeWidth / 2;
        //计算圆的中心点
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawProgress(canvas);
    }

    //画圆环
    private void drawCircle(Canvas canvas) {
        canvas.drawOval(mCenterX - mRadiusBig, mCenterY - mRadiusBig, mCenterX + mRadiusBig, mCenterY + mRadiusBig, mCirclePaint);
        canvas.drawOval(mCenterX - mRadiusSmall, mCenterY - mRadiusSmall, mCenterX + mRadiusSmall, mCenterY + mRadiusSmall, mCirclePaint);
    }

    //画进度
    private void drawProgress(Canvas canvas) {
        canvas.drawArc(mCenterX - mProgressRadius, mCenterY - mProgressRadius, mCenterX + mProgressRadius, mCenterY + mProgressRadius, -90f, progress * 1.0f / 100f * 360, false, mProgressPaint);
    }

}

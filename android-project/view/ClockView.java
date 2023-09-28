package com.example.myapp.view;

// 导入相关类
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

// 定义一个自定义的时钟控件类，继承自View
public class ClockView extends View {

    // 定义画笔
    private Paint mPaint;

    // 定义表盘的半径
    private float radius;

    // 定义时针、分针、秒针的长度
    private float hourLength, minuteLength, secondLength;

    // 定义时针、分针、秒针的角度
    private float hourAngle, minuteAngle, secondAngle;

    // 定义构造方法
    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化画笔
        mPaint = new Paint();
        // 设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        // 设置画笔颜色为黑色
        mPaint.setColor(Color.BLACK);
        // 设置画笔风格为描边
        mPaint.setStyle(Paint.Style.STROKE);
    }

    // 重写onMeasure方法，设置控件的宽高为表盘直径的两倍
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取控件的宽度和高度
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        // 计算表盘的半径，取宽度和高度中较小的值的一半
        radius = Math.min(width, height) / 2f;
        // 设置控件的宽高为表盘直径的两倍
        setMeasuredDimension((int) (radius * 2), (int) (radius * 2));
    }

    // 重写onDraw方法，绘制时钟控件
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取当前时间的时分秒
        long time = System.currentTimeMillis();
        int hour = (int) ((time / (1000 * 60 * 60)) % 24);
        int minute = (int) ((time / (1000 * 60)) % 60);
        int second = (int) ((time / 1000) % 60);

        // 计算时针、分针、秒针的角度（以12点钟方向为0°，顺时针为正方向）
        hourAngle = hour * 30 + minute * 0.5f; // 每小时转30°，每分钟转0.5°
        minuteAngle = minute * 6 + second * 0.1f; // 每分钟转6°，每秒转0.1°
        secondAngle = second * 6; // 每秒转6°

        // 计算时针、分针、秒针的长度（根据表盘半径和自己的喜好设定）
        hourLength = radius * 0.5f; // 时针长度为表盘半径的一半
        minuteLength = radius * 0.65f; // 分针长度为表盘半径的0.65倍
        secondLength = radius * 0.8f; // 秒针长度为表盘半径的0.8倍

        // 绘制表盘
        drawClock(canvas);

        // 绘制时针
        drawHourHand(canvas);

        // 绘制分针
        drawMinuteHand(canvas);

        // 绘制秒针
        drawSecondHand(canvas);

        // 刷新界面，实现时钟走动的效果
        invalidate();
    }

    // 绘制表盘的方法，参数为画布对象
    private void drawClock(Canvas canvas) {
        // 设置画笔宽度为表盘半径的0.02倍
        mPaint.setStrokeWidth(radius * 0.02f);
        // 在画布上绘制一个空心圆，作为表盘
        canvas.drawCircle(radius, radius, radius, mPaint);
        // 设置画笔宽度为表盘半径的0.01倍
        mPaint.setStrokeWidth(radius * 0.01f);
        // 循环绘制表盘上的刻度线，每隔6°绘制一条，共60条
        for (int i = 0; i < 60; i++) {
            // 保存画布当前状态
            canvas.save();
            // 将画布旋转i*6°，以表盘圆心为旋转中心
            canvas.rotate(i * 6, radius, radius);
            // 判断是否为整点刻度（i能否被5整除）
            if (i % 5 == 0) {
                // 如果是整点刻度，设置画笔宽度为表盘半径的0.02倍
                mPaint.setStrokeWidth(radius * 0.02f);
                // 在画布上绘制一条长刻度线，起点为表盘最外圈，终点距离表盘边缘表盘半径的0.1倍
                canvas.drawLine(radius, 0, radius, radius * 0.1f, mPaint);
                // 设置画笔颜色为红色
                mPaint.setColor(Color.RED);
                // 设置画笔风格为填充
                mPaint.setStyle(Paint.Style.FILL);
                // 设置文本大小为表盘半径的0.15倍
                mPaint.setTextSize(radius * 0.15f);
                // 计算整点数字（i/5+1）的宽度和高度
                float textWidth = mPaint.measureText(String.valueOf(i / 5 + 1));
                Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
                float textHeight = fontMetrics.descent - fontMetrics.ascent;
                // 在画布上绘制整点数字，位置为刻度线终点下方一定距离，使其居中对齐
                canvas.drawText(String.valueOf(i / 5 + 1), radius - textWidth / 2,
                        radius * 0.1f + textHeight + radius * 0.025f, mPaint);
                // 恢复画笔颜色为黑色
                mPaint.setColor(Color.BLACK);
                // 恢复画笔风格为描边
                mPaint.setStyle(Paint.Style.STROKE);
            } else {
                // 如果不是整点刻度，设置画笔宽度为表盘半径的0.01倍
                mPaint.setStrokeWidth(radius * 0.01f);
                // 在画布上绘制一条短刻度线，起点为表盘最外圈，终点距离表盘边缘表盘半径的0.05倍
                canvas.drawLine(radius, 0, radius, radius * 0.05f, mPaint);
            }
            // 恢复画布状态
            canvas.restore();
        }
    }

    // 绘制时针的方法，参数为画布对象
    private void drawHourHand(Canvas canvas) {
        // 设置画笔宽度为表盘半径的0.02倍
        mPaint.setStrokeWidth(radius * 0.02f);
        // 计算时针指向的位置的坐标（利用三角函数）
        float hourX = (float) (radius + hourLength * Math.sin(Math.toRadians(hourAngle)));
        float hourY = (float) (radius - hourLength * Math.cos(Math.toRadians(hourAngle)));
        // 在画布上绘制一条线段，作为时针，起点为表盘圆心，终点为计算出的坐标
        canvas.drawLine(radius, radius, hourX, hourY, mPaint);
    }

    // 绘制分针的方法，参数为画布对象
    private void drawMinuteHand(Canvas canvas) {
        // 设置画笔宽度为表盘半径的0.015倍
        mPaint.setStrokeWidth(radius * 0.015f);
        // 计算分针指向的位置的坐标（利用三角函数）
        float minuteX = (float) (radius + minuteLength * Math.sin(Math.toRadians(minuteAngle)));
        float minuteY = (float) (radius - minuteLength * Math.cos(Math.toRadians(minuteAngle)));
// 在画布上绘制一条线段，作为分针，起点为表盘圆心，终点为计算出的坐标
        canvas.drawLine(radius, radius, minuteX, minuteY, mPaint);
    }

    // 绘制秒针的方法，参数为画布对象
    private void drawSecondHand(Canvas canvas) {
        // 设置画笔宽度为表盘半径的0.01倍
        mPaint.setStrokeWidth(radius * 0.01f);
        // 设置画笔颜色为红色
        mPaint.setColor(Color.RED);
        // 计算秒针指向的位置的坐标（利用三角函数）
        float secondX = (float) (radius + secondLength * Math.sin(Math.toRadians(secondAngle)));
        float secondY = (float) (radius - secondLength * Math.cos(Math.toRadians(secondAngle)));
        // 在画布上绘制一条线段，作为秒针，起点为表盘圆心，终点为计算出的坐标
        canvas.drawLine(radius, radius, secondX, secondY, mPaint);
        // 恢复画笔颜色为黑色
        mPaint.setColor(Color.BLACK);
    }
}
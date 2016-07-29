package com.example.wangjinzhao.britatime;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangjinzhao on 2016/7/29.
 */
public class TimeView extends View {

    Context context;
    long leftDay;
    public TimeView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        this.context=context;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        long Y= canvas.getHeight()*leftDay/30;
        canvas.drawRect(canvas.getClipBounds().left,canvas.getClipBounds().bottom-Y, canvas.getClipBounds().right, canvas.getClipBounds().bottom, paint);
        Paint textPaint=new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(100);
        textPaint.setAntiAlias(true);
        long halfY=Y/2;
        int halfX=canvas.getWidth()/2;
        int left=canvas.getClipBounds().left+halfX-50;
        long top=canvas.getClipBounds().bottom-Y+halfY+50;
        canvas.drawText(String.valueOf(leftDay),left,top,textPaint);
        super.onDraw(canvas);
    }

    public void setLeftDay(long leftDay)
    {
        this.leftDay=leftDay;
    }
}

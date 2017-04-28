package com.abohomol.videolap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

public class TransparentLapView extends View {

    private static final int TEXT_SIZE = 1000;
    private static final int TEXT_ALPHA = 175;
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap textBitmap;
    private final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);

    public TransparentLapView(Context context) {
        super(context);
        init();
    }

    public TransparentLapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TransparentLapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setXfermode(xfermode);
        textPaint.setColor(getColorWithAlpha(getColor(R.color.colorAccent), TEXT_ALPHA));
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        textBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(textBitmap);
        canvas.drawColor(getColor(R.color.lap_background));
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        //TODO: parametrize text
        canvas.drawText("1", xPos, yPos, textPaint);
    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(textBitmap, 0, 0, null);
        super.onDraw(canvas);
    }

    private int getColor(int colorRes) {
        return getContext().getResources().getColor(colorRes);
    }

    private int getColorWithAlpha(int color, int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }
}

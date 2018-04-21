package com.nygellopez.roundimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by nygellopez on 2018-04-20.
 */

public class RoundImageView extends AppCompatImageView {

  private Paint mAntiAliasPaint;
  private Paint mImagePaint;
  private Bitmap mCircleBitmap;

  public RoundImageView(Context context) {
    this(context, null);
  }

  public RoundImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mAntiAliasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mImagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mImagePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    setLayerType(LAYER_TYPE_HARDWARE, null);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawBitmap(mCircleBitmap, 0, 0, mImagePaint);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    prepareCircleBitmap(w, h);
  }

  private void prepareCircleBitmap(int width, int height) {
    float x = width/2f;
    float y = height/2f;

    mCircleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas circleCanvas = new Canvas(mCircleBitmap);
    circleCanvas.drawCircle(x, y, x, mAntiAliasPaint);
  }
}

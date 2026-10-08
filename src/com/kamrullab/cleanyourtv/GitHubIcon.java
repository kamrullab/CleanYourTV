package com.kamrullab.cleanyourtv;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

// A tiny, resource-free Octocat-style mark for the personalized launcher header.
final class GitHubIcon extends Drawable {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path path = new Path();

    GitHubIcon() {
        paint.setColor(0xFF7DE3FF);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override public void draw(Canvas canvas) {
        float s = Math.min(getBounds().width(), getBounds().height()) / 100f;
        float x = getBounds().left, y = getBounds().top;
        path.reset();
        path.moveTo(x + 24*s, y + 39*s);   // left ear
        path.lineTo(x + 18*s, y + 17*s);
        path.lineTo(x + 40*s, y + 29*s);
        path.quadTo(x + 50*s, y + 25*s, x + 60*s, y + 29*s);
        path.lineTo(x + 82*s, y + 17*s);   // right ear
        path.lineTo(x + 76*s, y + 39*s);
        path.quadTo(x + 86*s, y + 50*s, x + 82*s, y + 65*s);
        path.quadTo(x + 77*s, y + 82*s, x + 59*s, y + 86*s);
        path.lineTo(x + 59*s, y + 72*s);
        path.quadTo(x + 59*s, y + 64*s, x + 50*s, y + 64*s);
        path.quadTo(x + 41*s, y + 64*s, x + 41*s, y + 72*s);
        path.lineTo(x + 41*s, y + 86*s);
        path.quadTo(x + 23*s, y + 82*s, x + 18*s, y + 65*s);
        path.quadTo(x + 14*s, y + 50*s, x + 24*s, y + 39*s);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override public void setAlpha(int alpha) { paint.setAlpha(alpha); }
    @Override public void setColorFilter(ColorFilter filter) { paint.setColorFilter(filter); }
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return 30; }
    @Override public int getIntrinsicHeight() { return 30; }
}

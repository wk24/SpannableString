package com.wuk.spannablestring

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv1:TextView = findViewById(R.id.tv1)
        var tv2:TextView = findViewById(R.id.tv2)
        var tvView:TextView = findViewById(R.id.tv_view)

        tvView.post {
            val spannableStr = getSpannableStr(tv1, tvView)
            tv1.setText(spannableStr)

            val spannableStr2 = getSpannableStr(tv2, tvView)
            tv2.setText(spannableStr2)
        }

    }

    private fun getSpannableStr(tv: TextView, tvView: TextView): SpannableString {
        val str = tv.text.toString()+"  "
        val spannableString = SpannableString(str)

        val viewBitmap: Bitmap = createBitmapFromView(tvView)
        val bitmapDrawable = BitmapDrawable(viewBitmap)
        bitmapDrawable.setBounds(0, 0, 60, 24)
        val imageSpan = MyImageSpan(bitmapDrawable)
        spannableString.setSpan(imageSpan, str.length - 1, str.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        return spannableString;
    }

    private fun createBitmapFromView(view: View): Bitmap {
        //是ImageView直接获取
        if (view is ImageView) {
            val drawable = view.drawable
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }
        }
        view.clearFocus()
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        if (bitmap != null) {
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
        }
        return bitmap!!
    }
}
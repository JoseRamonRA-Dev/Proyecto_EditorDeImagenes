package com.example.avance1_filtrosbasicos

import android.graphics.Bitmap
import android.graphics.Color

class Filtros {
    fun invert(bitmap: Bitmap):Bitmap{
        var src=bitmap
        val bitmapoutput = Bitmap.createBitmap(src.width, src.height, src.config)
        var a :Int
        var r :Int
        var g :Int
        var b :Int
        var pixel:Int
        val alto = src.height
        val ancho = src.width
        for (y in 0 until alto) {
            for (x in 0 until ancho) {
                pixel = src.getPixel(x,y)
                a = Color.alpha(pixel)
                r = 255- Color.red(pixel)
                g = 255- Color.green(pixel)
                b = 255- Color.blue(pixel)
                bitmapoutput.setPixel(x,x, Color.argb(a,r,g,b))
            }
        }
        return bitmapoutput
    }

}
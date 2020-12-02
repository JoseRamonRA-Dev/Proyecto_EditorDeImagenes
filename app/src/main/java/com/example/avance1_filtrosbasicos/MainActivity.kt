package com.example.avance1_filtrosbasicos

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagen: ImageView = findViewById(R.id.imageView)
        val imagen2: ImageView = findViewById(R.id.imageView2)
        val imagen3: ImageView = findViewById(R.id.imageView3)
        val imagen4: ImageView = findViewById(R.id.imageView4)
        val imagen5: ImageView = findViewById(R.id.imageView5)
        val imagen6: ImageView = findViewById(R.id.imageView6)
        val imagen7: ImageView = findViewById(R.id.imageView7)
        val imagen8: ImageView = findViewById(R.id.imageView8)
        val imagen9: ImageView = findViewById(R.id.imageView9)

        //i.invertido
        imagen.setColorFilter(ColorFilterGenerator.adjustInvert())

        //ii. Escala de grises
        imagen2.setColorFilter(ColorFilterGenerator.adjustGrayScale())

        //iii. Brillo
        imagen3.setColorFilter(ColorFilterGenerator.adjustBrightness(100f))

        //vi. Separación de canales (rojo, verde, azul)
        //azul
        imagen6.setColorFilter(ColorFilterGenerator.adjustBlue())
        //verde
        imagen5.setColorFilter(ColorFilterGenerator.adjustGreen())
        //rojo
        imagen4.setColorFilter(ColorFilterGenerator.adjustRed())

        //b. Filtros de convolución
        //i. Smoothing
        //ii. Gaussian Blur
        //iii. Sharpen
        //iv. Mean Removal
        //v. Embossing
        //vi. Edge Detection

        //val bitmap = Bitmap.createBitmap(imagen7.getWidth(), imagen7.getHeight(), Bitmap.Config.RGB_565)
        //val bitmapoutput = Bitmap.createBitmap(imagen7.getWidth(), imagen7.getHeight(), Bitmap.Config.RGB_565)

        val width = 400
        val height = 300
        val max = 255
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x, y, Color.argb(
                        max,
                        (max * x / width.toFloat()).toInt(),
                        (max * y / height.toFloat()).toInt(),
                        (max * x * y / (width.toFloat() * height)) as Int
                    )
                )
            }
        }
        //val imageView = ImageView(this)
        imagen7.setImageBitmap(bitmap)
        setContentView(imagen7)
    }
}
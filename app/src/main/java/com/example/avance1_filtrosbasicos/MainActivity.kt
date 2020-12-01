package com.example.avance1_filtrosbasicos

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
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
        val imagen10: ImageView = findViewById(R.id.imageView10)
        val imagen11: ImageView = findViewById(R.id.imageView11)
        val imagen12: ImageView = findViewById(R.id.imageView12)

        val bm = (imagen8.getDrawable() as BitmapDrawable).bitmap

        //i.invertido
        imagen.setImageBitmap(Filtros.invertido(bm))

        //ii. Escala de grises
        imagen2.setImageBitmap(Filtros.escalagris(bm))

        //iii. Brillo
        imagen3.setImageBitmap(Filtros.brillo(bm, 100))

        //iv. Contsrate
        imagen4.setImageBitmap(Filtros.contrast(bm, 100.0))
        imagen5.setImageBitmap(Filtros.contraste(bm,90))

        //v.gamma
        imagen6.setImageBitmap(Filtros.gamma(bm))
        imagen7.setImageBitmap(Filtros.gamma(bm, 0.0))
        imagen8.setImageBitmap(Filtros.gamma(bm, 5.0)) //oscuro
        imagen9.setImageBitmap(Filtros.gamma(bm, 10.0)) //normal
        imagen10.setImageBitmap(Filtros.gamma(bm, 15.0)) //claro

        //vi. Separación de canales (rojo, verde, azul)
        //azul
        imagen10.setImageBitmap(Filtros.azul(bm))
        //verde
        imagen12.setImageBitmap(Filtros.verde(bm))
        //rojo
        imagen11.setImageBitmap(Filtros.rojo(bm))

        //b. Filtros de convolución
        //i. Smoothing

        //ii. Gaussian Blur
        //iii. Sharpen
        //iv. Mean Removal
        //v. Embossing
        //vi. Edge Detection

    }
}
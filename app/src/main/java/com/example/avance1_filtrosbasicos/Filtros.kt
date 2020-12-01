package com.example.avance1_filtrosbasicos

import android.graphics.*
import kotlin.math.min
import kotlin.math.pow
import android.graphics.ColorFilter
import android.graphics.ColorMatrix

object Filtros {
    fun contraste(bitmap: Bitmap, valor: Float): Bitmap {
        //Saco el tamño de la imagen para poder ir pixel por picel
        var bitmap2 = bitmap
        val ancho = bitmap2.width
        val alto = bitmap2.height
        //Con los datos que saque del bitmap que me envian, creo un nuevo bitmap para hacerle los cambios
        val salida = Bitmap.createBitmap(ancho, alto, bitmap2.config)
        // creo un canvas para poderme auxiliar a la hora de hacer el cambio
        val canv = Canvas()
        canv.setBitmap(salida)

        // draw bitmap to bmOut from src bitmap so we can modify it
        canv.drawBitmap(bitmap2, 0f, 0f, Paint(Color.BLACK))
        //Para los valor de ARGB cree  las variables
        var a: Int
        var r: Int
        var g: Int
        var b: Int
        //Para ir cambiando pixel por pixel
        var pixel: Int
        val contraste = Math.pow(((100 + valor) / 100).toDouble(), 2.0)

        //recorrer pixel por pixel
        for (x in 0 until ancho) {
            for (y in 0 until alto) {
                pixel = bitmap2.getPixel(x, y)
                a = Color.alpha(pixel)
                // al ir pixel por pixel variando entre RGB
                r = Color.red(pixel)
                r = (((r / 255.0 - 0.5) * contraste + 0.5) * 255.0).toInt()
                if (r < 0) {
                    r = 0
                } else if (r > 255) {
                    r = 255
                }
                g = Color.green(pixel)
                g = (((g / 255.0 - 0.5) * contraste + 0.5) * 255.0).toInt()
                if (g < 0) {
                    g = 0
                } else if (g > 255) {
                    g = 255
                }
                b = Color.blue(pixel)
                b = (((b / 255.0 - 0.5) * contraste + 0.5) * 255.0).toInt()
                if (b < 0) {
                    b = 0
                } else if (b > 255) {
                    b = 255
                }

                // para el bitmap de salida
                salida.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }

        return salida
    }


    fun gamma(src: Bitmap): Bitmap {
        var src = src
        var red = (2) / 10.0
        var green = (2) / 10.0
        var blue = (2) / 10.0
        // create output image
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)
        // get image size
        val width = src.width
        val height = src.height
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        // constant value curve
        val MAX_SIZE = 256
        val MAX_VALUE_DBL = 255.0
        val MAX_VALUE_INT = 255
        val REVERSE = 1.0

        // gamma arrays
        val gammaR = IntArray(MAX_SIZE)
        val gammaG = IntArray(MAX_SIZE)
        val gammaB = IntArray(MAX_SIZE)

        // setting values for every gamma channels
        for (i in 0 until MAX_SIZE) {
            gammaR[i] = min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red) + 0.5).toInt()
            )
            gammaG[i] = min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green) + 0.5).toInt()
            )
            gammaB[i] = min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue) + 0.5).toInt()
            )
        }

        // apply gamma table
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                // look up gamma
                R = gammaR[Color.red(pixel)]
                G = gammaG[Color.green(pixel)]
                B = gammaB[Color.blue(pixel)]
                // set new color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }

        // return final image
        return bmOut
    }
    fun invertido(bitmap: Bitmap): Bitmap {
        var bitmap2 = bitmap

        var a: Int
        var r: Int
        var g: Int
        var b: Int
        val alto = bitmap2.height
        val ancho = bitmap2.width
        var pixel: Int
        //Creamos un nuevo bitmap
        val bitmapoutput = Bitmap.createBitmap(bitmap2.width, bitmap.height, bitmap2.config)

        for (y in 0 until alto) {
            for (x in 0 until ancho) {
                //Dandole los colores con RGB
                pixel = bitmap2.getPixel(x, y)
                a = Color.alpha(pixel)
                r = 255 - Color.red(pixel)
                g = 255 - Color.green(pixel)
                b = 255 - Color.blue(pixel)
                bitmapoutput.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }

        return bitmapoutput
    }

    fun brillo(imagenB: Bitmap, valor: Int): Bitmap {
        // Tamaño de la imagen
        var imagen = imagenB
        val ancho = imagen.width
        val alto= imagen.height
        //Crear el bitmap a enviar
        val salida = Bitmap.createBitmap(ancho, alto, imagen.config)
        // Color rgb
        var a: Int
        var r: Int
        var g: Int
        var b: Int
        var pixel: Int

        // Darle valor Pixel por pixel
        for (x in 0 until ancho) {
            for (y in 0 until alto) {
                // get pixel color
                pixel = imagen.getPixel(x, y)
                a = Color.alpha(pixel)
                r = Color.red(pixel)
                g = Color.green(pixel)
                b = Color.blue(pixel)

                r += valor
                if (r > 255) {
                    r = 255
                } else if (r < 0) {
                    r = 0
                }
                g += valor
                if (g > 255) {
                    g = 255
                } else if (g < 0) {
                    g = 0
                }
                b += valor
                if (b > 255) {
                    b = 255
                } else if (b < 0) {
                    b = 0
                }

                salida.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }

        return salida
    }
    fun escalagris(imagenB: Bitmap): Bitmap {
        //Arreglo, en lugar del for
        var imagen = imagenB
        val colorgris = floatArrayOf(
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f
        )
        val colorMatrixGray = ColorMatrix(colorgris)
        val w = imagen.width
        val h = imagen.height
        val bitmapResultado = Bitmap
            .createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvasResult = Canvas(bitmapResultado)
        val paint = Paint()
        val filter = ColorMatrixColorFilter(colorMatrixGray)
        paint.colorFilter = filter
        canvasResult.drawBitmap(imagen, 0f, 0f, paint)
        return bitmapResultado
    }
}
package com.example.avance1_filtrosbasicos

import android.graphics.*
import kotlin.math.min


object Filtros {
    fun gamma(src: Bitmap, color: Double): Bitmap {
        val red: Double = (color + 2) / 10.0
        val green: Double = (color + 2) / 10.0
        val blue: Double = (color + 2) / 10.0
        // create output image
        // create output image
        val bmOut =
            Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig())
        // get image size
        // get image size
        val width: Int = src.getWidth()
        val height: Int = src.getHeight()
        // color information
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        // constant value curve
        // constant value curve
        val MAX_SIZE = 256
        val MAX_VALUE_DBL = 255.0
        val MAX_VALUE_INT = 255
        val REVERSE = 1.0

        // gamma arrays

        // gamma arrays
        val gammaR = IntArray(MAX_SIZE)
        val gammaG = IntArray(MAX_SIZE)
        val gammaB = IntArray(MAX_SIZE)

        // setting values for every gamma channels

        // setting values for every gamma channels
        for (i in 0 until MAX_SIZE) {
            gammaR[i] = Math.min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red) + 0.5).toInt()
            )
            gammaG[i] = Math.min(
                MAX_VALUE_INT,
                (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green) + 0.5).toInt()
            )
            gammaB[i] = Math.min(
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

    fun contrast(src: Bitmap, value: Double): Bitmap {
        // image size
        var src = src
        val width = src.width
        val height = src.height

        // create output bitmap
        val bmOut = Bitmap.createBitmap(width, height, src.config)

        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int = 0

        // get contrast value
        //val contrast = Math.pow((100 + value) / 100, 2.0)

        var contrast = (100.0 + 50.0) / 100.0
        contrast = contrast * contrast

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel)
                R = (((R / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (R < 0) {
                    R = 0
                } else if (R > 255) {
                    R = 255
                }
                G = Color.green(pixel)
                G = (((G / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (G < 0) {
                    G = 0
                } else if (G > 255) {
                    G = 255
                }
                B = Color.blue(pixel)
                B = (((B / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (B < 0) {
                    B = 0
                } else if (B > 255) {
                    B = 255
                }
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }
        return bmOut
    }

    /* fun contraste(bitmap: Bitmap, valor: Int): Bitmap {
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
    }*/

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

    fun rojo(bitmap: Bitmap): Bitmap {
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
                r = Color.red(pixel)
                g = 0
                b = 0
                bitmapoutput.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }
        return bitmapoutput
    }

    fun azul(bitmap: Bitmap): Bitmap {
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
                r = 0
                g = 0
                b = Color.blue(pixel)
                bitmapoutput.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }
        return bitmapoutput
    }

    fun verde(bitmap: Bitmap): Bitmap {
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
                r = 0
                g = Color.green(pixel)
                b = 0
                bitmapoutput.setPixel(x, y, Color.argb(a, r, g, b))
            }
        }
        return bitmapoutput
    }

    fun brillo(imagenB: Bitmap, valor: Int): Bitmap {
        // Tamaño de la imagen
        var imagen = imagenB
        val ancho = imagen.width
        val alto = imagen.height
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
        val w = imagen.width + 1700
        val h = imagen.height + 1700
        val bitmapResultado = Bitmap
            .createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvasResult = Canvas(bitmapResultado)
        val paint = Paint()
        val filter = ColorMatrixColorFilter(colorMatrixGray)
        paint.colorFilter = filter
        canvasResult.drawBitmap(imagen, 10f, 0f, paint)
        return bitmapResultado
    }




    //funciones para los filtros de convolucion
    var size = 3

    fun Smooth(b: Bitmap, nWeight: Int /* default to 1 */): Bitmap {
        val m = ConvolutionMatrix(3)
        m.setAll(1.0)
        m.Offset = nWeight + 0.0
        m.Factor = nWeight + 8.0
        return ConvolutionMatrix.computeConvolution3x3(b, m)
    }

    fun gaussian(src: Bitmap): Bitmap {
        val GaussianBlurConfig = arrayOf(
            doubleArrayOf(1.0, 2.0, 1.0),
            doubleArrayOf(2.0, 4.0, 2.0),
            doubleArrayOf(1.0, 2.0, 1.0)
        )
        val matrix2 = applyConfig(GaussianBlurConfig)
        return computeConvolution3x3(src, matrix2, 16.0, 0.0)
    }

    fun sharpen(src: Bitmap): Bitmap {
        val SharpConfig = arrayOf(
            doubleArrayOf(0.0, -2.0, 0.0),
            doubleArrayOf(-2.0, 11.0, -2.0),
            doubleArrayOf(0.0, -2.0, 0.0)
        )
        val matrix2 = applyConfig(SharpConfig)
        return computeConvolution3x3(src, matrix2, 3.0, 0.0)
    }

    fun meanRe(src: Bitmap): Bitmap {
        val SharpConfig = arrayOf(
            doubleArrayOf(-1.0, -1.0, -1.0),
            doubleArrayOf(-1.0, 9.0, -1.0),
            doubleArrayOf(-1.0, -1.0, -1.0)
        )
        val matrix2 = applyConfig(SharpConfig)
        return computeConvolution3x3(src, matrix2, 1.0, 0.0)
    }

    fun embossing(src: Bitmap): Bitmap {
        val SharpConfig = arrayOf(
            doubleArrayOf(-1.0, 0.0, -1.0),
            doubleArrayOf(0.0, 4.0, 0.0),
            doubleArrayOf(-1.0, 0.0, -1.0)
        )
        val matrix2 = applyConfig(SharpConfig)
        return computeConvolution3x3(src, matrix2, 1.0, 127.0)
    }

    fun edgeDetection(src: Bitmap): Bitmap {
        val SharpConfig = arrayOf(
            doubleArrayOf(1.0, 1.0, 1.0),
            doubleArrayOf(0.0, 0.0, 0.0),
            doubleArrayOf(-1.0, -1.0, -1.0)
        )
        val matrix2 = applyConfig(SharpConfig)
        return computeConvolution3x3(src, matrix2, 1.0, 127.0)
    }





    fun applyConfig(config: Array<DoubleArray>): Array<DoubleArray> {
        val matrix = Array(size) { DoubleArray(size) }
        for (x in 0 until ConvolutionMatrix.SIZE) {
            for (y in 0 until ConvolutionMatrix.SIZE) {
                matrix[x][y] = config[x][y]
            }
        }
        return matrix
    }

    const val SIZE = 3
    fun computeConvolution3x3(src: Bitmap, Matrix: Array<DoubleArray>, Factor:Double, Offset:Double): Bitmap {
        var src = src
        val width = src!!.width
        val height = src.height
        val result = Bitmap.createBitmap(width, height, src.config)
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var sumR: Int
        var sumG: Int
        var sumB: Int
        val pixels = Array(SIZE) { IntArray(SIZE) }
        for (y in 0 until height - 2) {
            for (x in 0 until width - 2) {
                // get pixel matrix
                for (i in 0 until SIZE) {
                    for (j in 0 until SIZE) {
                        pixels[i][j] = src.getPixel(x + i, y + j)
                    }
                }
                // get alpha of center pixel
                A = Color.alpha(pixels[1][1])

                // init color sum
                sumB = 0
                sumG = sumB
                sumR = sumG

                // get sum of RGB on matrix
                for (i in 0 until SIZE) {
                    for (j in 0 until SIZE) {
                        sumR += (Color.red(pixels[i][j]) * Matrix[i][j]).toInt()
                        sumG += (Color.green(pixels[i][j]) * Matrix[i][j]).toInt()
                        sumB += (Color.blue(pixels[i][j]) * Matrix[i][j]).toInt()
                    }
                }

                // get final Red
                R = ((sumR / Factor) + Offset).toInt()
                if (R < 0) {
                    R = 0
                } else if (R > 255) {
                    R = 255
                }

                // get final Green
                G = ((sumG / Factor) + Offset).toInt()
                if (G < 0) {
                    G = 0
                } else if (G > 255) {
                    G = 255
                }

                // get final Blue
                B = ((sumB / Factor) + Offset).toInt()
                if (B < 0) {
                    B = 0
                } else if (B > 255) {
                    B = 255
                }
                // apply new pixel
                result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B))
            }
        }
        // final image
        return result
    }
}
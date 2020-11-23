package com.example.avance1_filtrosbasicos

import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import com.example.avance1_filtrosbasicos.ColorFilterGenerator
import android.graphics.ColorMatrixColorFilter

object ColorFilterGenerator {
    fun adjustHue(value: Float): ColorFilter {
        val cm = ColorMatrix()
        adjustHue(cm, value)
        return ColorMatrixColorFilter(cm)
    }

    fun adjustHue(cm: ColorMatrix, value: Float) {
        var value = value
        value = cleanValue(value, 180f) / 180f * Math.PI.toFloat()
        if (value == 0f) {
            return
        }
        val cosVal = Math.cos(value.toDouble()).toFloat()
        val sinVal = Math.sin(value.toDouble()).toFloat()
        val lumR = 0.213f
        val lumG = 0.715f
        val lumB = 0.072f
        val mat = floatArrayOf(
            lumR + cosVal * (1 - lumR) + sinVal * -lumR,
            lumG + cosVal * -lumG + sinVal * -lumG,
            lumB + cosVal * -lumB + sinVal * (1 - lumB),
            0f,
            0f,
            lumR + cosVal * -lumR + sinVal * 0.143f,
            lumG + cosVal * (1 - lumG) + sinVal * 0.140f,
            lumB + cosVal * -lumB + sinVal * -0.283f,
            0f,
            0f,
            lumR + cosVal * -lumR + sinVal * -(1 - lumR),
            lumG + cosVal * -lumG + sinVal * lumG,
            lumB + cosVal * (1 - lumB) + sinVal * lumB,
            0f,
            0f,
            0f, 0f, 0f, 1f, 0f,
            0f, 0f, 0f, 0f, 1f
        )
        cm.postConcat(ColorMatrix(mat))
    }

    fun adjustGrayScale(): ColorFilter {
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        return ColorMatrixColorFilter(cm)
    }

    fun adjustInvert(): ColorFilter {
        val cm = ColorMatrix()
        val mat = floatArrayOf(
            -1f, 0f, 0f, 0f, 255f,
            0f, -1f, 0f, 0f, 255f,
            0f, 0f, -1f, 0f, 255f,
            0f, 0f, 0f, 1f, 0f
        )
        cm.postConcat(ColorMatrix(mat))
        return ColorMatrixColorFilter(cm)
    }

    fun adjustBrightness(value: Float): ColorFilter {
        val cm = ColorMatrix()
        adjustBrightness(cm, value)
        return ColorMatrixColorFilter(cm)
    }

    fun adjustBrightness(cm: ColorMatrix, value: Float) {
        var value = value
        value = cleanValue(value, 100f)
        if (value == 0f) {
            return
        }
        val mat = floatArrayOf(
            1f, 0f, 0f, 0f, value,
            0f, 1f, 0f, 0f, value,
            0f, 0f, 1f, 0f, value,
            0f, 0f, 0f, 1f, 0f,
            0f, 0f, 0f, 0f, 1f
        )
        cm.postConcat(ColorMatrix(mat))
    }

    fun adjustRed(): ColorFilter {
        val cm = ColorMatrix()
        val mat = floatArrayOf(
            1f, 1f, 1f, 0f, 0f,
            0f, 0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
        cm.postConcat(ColorMatrix(mat))
        return ColorMatrixColorFilter(cm)
    }

    fun adjustGreen(): ColorFilter {
        val cm = ColorMatrix()
        val mat = floatArrayOf(
            0f, 0f, 0f, 0f, 0f,
            1f, 1f, 1f, 0f, 0f,
            0f, 0f, 0f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
        cm.postConcat(ColorMatrix(mat))
        return ColorMatrixColorFilter(cm)
    }

    fun adjustBlue(): ColorFilter {
        val cm = ColorMatrix()
        val mat = floatArrayOf(
            0f, 0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f, 0f,
            1f, 1f, 1f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
        cm.postConcat(ColorMatrix(mat))
        return ColorMatrixColorFilter(cm)
    }


    internal fun cleanValue(p_val: Float, p_limit: Float): Float {
        return Math.min(p_limit, Math.max(-p_limit, p_val))
    }
}
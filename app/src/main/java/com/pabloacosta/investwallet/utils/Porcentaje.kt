package com.pabloacosta.investwallet.utils

class Porcentaje {

    fun generateGanancia(value : String, porcentaje : String, typePorcentaje: Int) : Float {

        val inversion = value.toFloat()
        val porcen = porcentaje.toFloat()

        val totalGanancia = (porcen * inversion) / 100

        return if (typePorcentaje == 0){
            totalGanancia + inversion
        }else{
            totalGanancia * 12 + inversion
        }

    }

}
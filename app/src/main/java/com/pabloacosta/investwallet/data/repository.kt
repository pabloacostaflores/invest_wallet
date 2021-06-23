package com.pabloacosta.investwallet.data

import com.pabloacosta.investwallet.R

class Repository {

    fun getList() : List<Inversion>{
        val list : ArrayList<Inversion> = ArrayList()


        val data1 = Inversion(
            "Cetes","4.7", R.drawable.cetes_logo
        )
        val data2 = Inversion(
            "Dolar","19.5", R.drawable.cetes_logo
        )
        val data3 = Inversion(
            "BBVA Inversion","4.2", R.drawable.bbva_logo
        )
        val data4 = Inversion(
            "Cetes","4.7", R.drawable.cetes_logo
        )

        val data5 = Inversion(
            "Cetes","4.7", R.drawable.cetes_logo
        )

        val data6 = Inversion(
            "Cetes","4.7", R.drawable.cetes_logo
        )


        list.add(data1)
        list.add(data2)
        list.add(data3)
        list.add(data4)
        list.add(data5)
        list.add(data6)

        return list

    }

}
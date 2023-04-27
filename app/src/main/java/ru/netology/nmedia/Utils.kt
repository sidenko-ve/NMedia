package ru.netology.nmedia

object Utils {

    fun getBeautifulCount(count: Long): String {

        var bCount = count.toString()

        if (count in 1000..9999) {
            val firstSymb = count / 1000
            val secondSymb = ((count / 100) % 10).toInt()

            if (secondSymb.equals(0)) {
                bCount = firstSymb.toString() + "K"
            } else bCount = "${firstSymb}.${secondSymb}K"
        } else if (count in 10000..999999) {
            bCount = (count / 1000).toString() + "K"

        } else if (count in 1000000..999999999) {
            val firstSymb = count / 1000000
            val secondSymb = ((count / 100000) % 10).toInt()
            if (secondSymb.equals(0)) {
                bCount = firstSymb.toString() + "M"
            } else bCount = "${firstSymb}.${secondSymb}M"
        }
        return bCount
    }
}
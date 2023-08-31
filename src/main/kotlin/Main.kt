fun main() {
    val kard1 = "visa"
    val kard2 = "vk PAY"
    val kard3 = "mastercard"
    val transfer = 1000
    var transfermonth = 0
    var limit: Boolean

    transfermonth = transfermonth + transfer
    calculation(kard1, transfer, transfermonth)

    //правильно ли я понял логику  сначала проверяем по лимитам ,
// если проходит то считаем коммисию

}

// выесняем какая комиссия по карте
// !!!! есть подозрение что это лишний метод !!!!!!!!
//  можно перенести в fun calculation
fun komission(kard: String, transf: Int, transfmonth: Int): Double {
    var komision = 0.0
    when (kard) {
        "visa", "МИР" -> if ((transf * 0.0075) < 35) {
            komision = 35.0  // рублей
        } else komision = 0.0075  // кофициэнт
        "vk PAY" -> komision = 0.0
        "mastercard", "Maestro " -> if (transfmonth < 75_000) {
            komision = 0.0
        } else komision = (0.006) + 20
    }
    return komision
}

//проверяем на лимиты  если проходит продолжаем
//!!!!!!!!!!необходимо  учитывать  не только  предыдущие но и текущий
fun limited(kard: String, transf: Int, transfmonth: Int): Boolean {
    var limit: Boolean = false
    when (kard) {
        "visa", "МИР", "mastercard", "Maestro " -> if (transf <= 150_000 && transfmonth+transf <= 600_000) {
            limit = true
        } else println("лимит превышен")

        "vk PAY" -> if (transf <= 15_000 && transfmonth +transf <= 40_000) {
            limit = true
        } else println("лимит превышен")
    }
    return limit
}

fun calculation(kard: String="Vk Pay", transf: Int=3000, transfmonth: Int=0) {
    if (limited(kard, transf, transfmonth) == true) {
        var komision = komission(kard, transf, transfmonth)
        var result : Int
        println("Ваша комиссия  по тарифу $komision ")
        result = (transf - komision).toInt()
        println( "сумма после расчета коммиссии по $kard составит $result ")
    }else println("вы не прошли лимиты")

}

/*Создать программу, выполняющую следующий функционал:
- запрашивается количество строк и столбцов для двумерного массива
- вводится необходимое количество трехзначных чисел (числа могут повторяться)
- подсчитывается количество различных цифр в полученном массиве
- на консоль выводится двумерный массив из введенных чисел и количество различных цифр используемых в данном массиве
Например, для массива
100   951   101   950
519   999   155   501
510   911   144   554
выведется результат: В массиве использовано 5 различных цифр*/

fun main() {
    print("Введите количество строк: ")
    val rows = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Некорректный ввод") // исключение с соо об ошибке
    print("Введите количество столбцов: ")
    val columns = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Некорректный ввод")

    val array = Array(rows) { IntArray(columns) }

    for (i in 0 until rows) {
        for (j in 0 until columns) {
            print("Введите трехзначное число: ")
            val number = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Некорректный ввод")
            if (number < 100 || number > 999) {
                throw IllegalArgumentException("Введено число не трехзначное")
            }
            array[i][j] = number
        }
    }
    // подсчет количества цифр
    val digitSet = mutableSetOf<Int>()
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            val number = array[i][j]
            val hundredsDigit = number / 100
            val tensDigit = (number % 100) / 10
            val unitsDigit = number % 10
            digitSet.add(hundredsDigit)
            digitSet.add(tensDigit)
            digitSet.add(unitsDigit)
        }
    }

    for (i in 0 until rows) {
        for (j in 0 until columns) {
            print("${array[i][j]}\t")
        }
        println()
    }

    println("В массиве использовано ${digitSet.size} различных цифр")
}
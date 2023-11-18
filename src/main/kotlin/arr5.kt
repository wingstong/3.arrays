/*Создать приложение, в котором пользователь вводит массив из различных слов.
На выходе приложение должно показать слова сгруппированные по признаку "состоят из одинаковых букв".
 Например, на входе ["eat", "tea", "tan", "ate", "nat", "bat"]. Получаем группы:
"ate", "eat", "tea"
"nat", "tan"
"bat" */

fun main() {
    val words = getInput()
    val groupedWords = wordsByLetters(words)
    printGroups(groupedWords)
}

fun getInput(): List<String> {
    print("Введите слова через пробел:")
    val input = readLine()
    return input?.split(" ")?.map { it.trim() } ?: emptyList()
}

fun wordsByLetters(words: List<String>): Map<String, List<String>> {
    return words.groupBy { it.toCharArray().sorted().joinToString("") }
}

fun printGroups(groups: Map<String, List<String>>) {
    for ((key, value) in groups) {
        println(value.joinToString())
    }
}
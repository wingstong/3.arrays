/*Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку).
Символы алфавита нумеруются от 1 до 33. Каждое число используется только один раз.
Сообщение шифруется с помощью ключевого слова, задаваемого пользователем.
Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита.
Составить программу шифровки и дешифровки строкового выражения
(то есть программа спрашивает - зашифровать или расшифровать текст и ключевое слово).
Первый массив считать закольцованным. Регистр букв не имеет значения. Например:

А  Б  В  Г  Д  Е  Ё  Ж  З  И  Й  К  Л  М  Н  О  П  Р  С  Т  У  Ф  Х  Ц  Ч  Ш  Щ  Ь  Ы  Ъ  Э  Ю  Я
21  13  4  20  22  1  25  12  24  14  2  28  9  23  3  29  6  16  15  11  26  5  30  27  8  18  10  33  31  32  19  7  17
Ключевое слово - ПОЛЕ
Исходный текст - СООБЩЕНИЕ
Зашифрованный текст - АЁФИРХЖСЮ*/

fun main() {
    val alphabet = mapOf(
        'А' to 21, 'Б' to 13, 'В' to 4, 'Г' to 20, 'Д' to 22, 'Е' to 1, 'Ё' to 25, 'Ж' to 12,
        'З' to 24, 'И' to 14, 'Й' to 2, 'К' to 28, 'Л' to 9, 'М' to 23, 'Н' to 3, 'О' to 29,
        'П' to 6, 'Р' to 16, 'С' to 15, 'Т' to 11, 'У' to 26, 'Ф' to 5, 'Х' to 30, 'Ц' to 27,
        'Ч' to 8, 'Ш' to 18, 'Щ' to 10, 'Ь' to 33, 'Ы' to 31, 'Ъ' to 32, 'Э' to 19, 'Ю' to 7, 'Я' to 17
    )

    print("Введите режим (шифрование - 1, расшифровка - 2): ")
    val mode = readLine()?.toIntOrNull()

    if (mode == 1) {
        print("Введите ключевое слово: ")
        val keyword = readLine()?.toUpperCase()

        print("Введите исходный текст: ")
        val inputText = readLine()?.toUpperCase() ?: ""

        if (inputText != null && keyword != null) {
            val encryptedText = encrypt(inputText, keyword, alphabet)
            println("Зашифрованный текст: $encryptedText")
        } else {
            println("Неверный ввод.")
        }
    }
    else if (mode == 2) {
        print("Введите ключевое слово: ")
        val keyword = readLine()?.toUpperCase()

        print("Введите зашифрованный текст: ")
        val inputText = readLine()?.toUpperCase()

        if (inputText != null && keyword != null) {
            val decryptedText = decrypt(inputText, keyword, alphabet)
            println("Расшифрованный текст: $decryptedText")
        } else {
            println("Неверный ввод.")
        }
    } else {
        println("Неверный ввод.")
    }
}

fun encrypt(inputText: String, keyword: String, alphabet: Map<Char, Int>): String {
    val encryptedText = StringBuilder()
    val keywordIndices = keyword.map { alphabet[it] ?: 0 }

    for (i in inputText.indices) {
        val char = inputText[i]
        if (char in alphabet) {
            val alphabetIndex = alphabet[char] ?: 0
            val shift = keywordIndices[i % keyword.length]
            val newIndex = (alphabetIndex + shift - 1) % alphabet.size
            encryptedText.append(alphabet.keys.toList()[newIndex])
        } else {
            encryptedText.append(char)
        }
    }

    return encryptedText.toString()
}

fun decrypt(inputText: String, keyword: String, alphabet: Map<Char, Int>): String {
    val decryptedText = StringBuilder()
    val keywordIndices = keyword.map { alphabet[it] ?: 0 }

    for (i in inputText.indices) {
        val char = inputText[i]
        if (char in alphabet) {
            val alphabetIndex = alphabet[char] ?: 0
            val shift = keywordIndices[i % keyword.length]
            val newIndex = (alphabetIndex - shift + 1 + alphabet.size) % alphabet.size
            decryptedText.append(alphabet.keys.toList()[newIndex])
        } else {
            decryptedText.append(char)
        }
    }

    return decryptedText.toString()
}

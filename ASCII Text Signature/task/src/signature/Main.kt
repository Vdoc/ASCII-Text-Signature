package signature

import java.util.*

/*
Stage 2/4: Star quality
 */
fun main() {
    val scanner = Scanner(System.`in`)
    val str = scanner.nextLine()

    printStr1(str)
}

fun printStr1(str: String) {
    val len = str.length
    var output = ""

    repeat(len + 4) {
        output += '*'
    }
    output += "\n* "
    output += str
    output += " *\n"
    repeat(len + 4) {
        output += '*'
    }
    print(output)
}



/*
Stage 1/4: Primitive Art

fun main() {
    println(" _____________\n" +
            "| Hyper Skill |\n" +
            " •••••••••••••")
}
*/
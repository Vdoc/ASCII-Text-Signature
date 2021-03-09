package signature

import java.io.File
import java.util.*
/*
Stage 4/4: ASCII New Roman
 */

fun main() {
    var data = readInpt()
    printText(data)
}

fun readInpt(): Array<String> {

    print("Enter name and surname: ")
    val scanner = Scanner(System.`in`)
    val name = scanner.nextLine()

    print("Enter person's status: ")
    val status = scanner.nextLine()
    return arrayOf(name, status)
}

fun printText(array: Array<String>) {
    val output = Out(array)

    output.addEights()
    output.addName()
    output.addStatus()
    output.addEights()

    output.print()
}

class Out {
    val name: String
    val status: String
    var put: String = ""
    var maxLength: Int = 10

    var lengthName: Int = 0
    var indentName: Int = 0
    var indentNameLeft: Int = 0
    var indentNameRight: Int = 0

    var lengthStatus: Int = 0
    var indentStatus: Int = 0
    var indentStatusLeft: Int = 0
    var indentStatusRight: Int = 0

    val nameFont: Array<RomanSimbol>
    val statusFont: Array<MediumSimbol>
    val border: Int = 2 // width border without *


    constructor(array: Array<String>) {
        name = array[0]
        nameFont = initStringNameByFontAndCountLengthName()

        status = array[1]
        statusFont = initStringStatusByFontAndCountLengthStatus()

        maxLength = if (lengthName > lengthStatus) {
            indentStatus = lengthName - lengthStatus
            indentStatusLeft = indentStatus / 2
            indentStatusRight = indentStatus - indentStatusLeft
            lengthName + 8
        } else {
            indentName = lengthStatus - lengthName
            indentNameLeft = indentName / 2
            indentNameRight = indentName - indentNameLeft
            lengthStatus + 8    // 8 = 2 borders * 4 width
        }
    }

    private fun initStringNameByFontAndCountLengthName(): Array<RomanSimbol> {
        val sim = Simbols()
        var arr: Array<RomanSimbol> = emptyArray<RomanSimbol>()

        for (i in 0 until name.length) {
            arr += sim.getSimbol(name[i])
            lengthName += arr[i].lengthOfChar
        }
        return arr
    }

    private fun initStringStatusByFontAndCountLengthStatus(): Array<MediumSimbol> {
        var arr: Array<MediumSimbol> = emptyArray<MediumSimbol>()

        for (i in 0 until status.length) {
            arr += MediumSimbol(status[i])
            lengthStatus += arr[i].lengthOfChar
        }
        return arr
    }

    fun addName() {
        put +="\n"
        for (i in 0..9) {
            addLeftBorder(border + indentNameLeft)
            addStringName(i)
            addRightBorder(indentNameRight + border)
        }
    }

    private fun addStringName(numberOfString: Int) {
        put += nameFont[0].chr[numberOfString]
        for (i in 1 until nameFont.size) {
            put += nameFont[i].chr[numberOfString]
        }
    }

    fun addStatus() {
        for (i in 0..2) {
            addLeftBorder(border + indentStatusLeft)
            addStringStatus(i)
            addRightBorder(indentStatusRight + border)
        }
    }

    private fun addStringStatus(numberOfString: Int) {
        put += statusFont[0].chr[numberOfString]
        for (i in 1 until statusFont.size) {
            put += statusFont[i].chr[numberOfString]
        }
    }

    private fun addLeftBorder(indent: Int = 2) {
        put += "88"
        repeat(indent) {
            put += " "
        }
    }

    private fun addRightBorder(indent: Int = 2) {
        repeat(indent) {
            put += " "
        }
        put += "88\n"
    }

    fun addEights() {
        repeat(maxLength) {
            put += '8'
        }
    }

    fun print() {
        print(put)
    }
}

class Simbols {
    var simb = emptyArray<String>()
    constructor() {
        val file = File("F:\\IdeaProjects\\ASCII Text Signature\\ASCII Text Signature\\task\\src\\signature\\roman.txt")

        val aaa: List<String> = file.readLines(Charsets.US_ASCII)

        for (i in 0 until aaa.size) {
            simb += aaa.get(i).replace("+", " ")
        }
    }

    fun getSimbol(ch: Char): RomanSimbol {
        return RomanSimbol(ch, simb)
    }
}

class RomanSimbol {
    val ch: Char
    var chr: Array<String> = emptyArray<String>()
    val lengthOfChar: Int

    constructor(ch: Char, simbols: Array<String>) {
        this.ch = ch

        var charNumb: Int = 52 // space
        val a = ch.toInt()
        if (a in 65..90) {      // AB..YZ
            charNumb = a - 65    // charNumb = 0..25
        }
        else if (a in 97..123) { // ab..yz
            charNumb = a - 71    // charNumb = 26..52
        }

        lengthOfChar = simbols[charNumb * 11].split(" ")[1].toInt()

        for (i in 1 until 11) {
            chr += simbols[charNumb * 11 + i]
        }
    }
}


class MediumSimbol {
    val ch: Char
    val chr: Array<String>
    val lengthOfChar: Int

    constructor(ch: Char) {
        this.ch = ch
        chr = getFont()
        lengthOfChar = chr[0].length
    }

    fun getFont() = when (ch.toUpperCase()) {
        'A' -> arrayOf( "____ ",
                        "|__| ",
                        "|  | ")
        'B' -> arrayOf( "___  ",
                        "|__] ",
                        "|__] ")
        'C' -> arrayOf( "____ ",
                        "|    ",
                        "|___ ")
        'D' -> arrayOf( "___  ",
                        "|  \\ ",
                        "|__/ ")
        'E' -> arrayOf( "____ ",
                        "|___ ",
                        "|___ ")
        'F' -> arrayOf( "____ ",
                        "|___ ",
                        "|    ")
        'G' -> arrayOf( "____ ",
                        "| __ ",
                        "|__] ")
        'H' -> arrayOf( "_  _ ",
                        "|__| ",
                        "|  | ")
        'I' -> arrayOf( "_ ",
                        "| ",
                        "| ")
        'J' -> arrayOf( " _ ",
                        " | ",
                        "_| ")
        'K' -> arrayOf( "_  _ ",
                        "|_/  ",
                        "| \\_ ")
        'L' -> arrayOf( "_    ",
                        "|    ",
                        "|___ ")
        'M' -> arrayOf( "_  _ ",
                        "|\\/| ",
                        "|  | ")
        'N' -> arrayOf( "_  _ ",
                        "|\\ | ",
                        "| \\| ")
        'O' -> arrayOf( "____ ",
                        "|  | ",
                        "|__| ")
        'P' -> arrayOf( "___  ",
                        "|__] ",
                        "|    ")
        'Q' -> arrayOf( "____ ",
                        "|  | ",
                        "|_\\| ")
        'R' -> arrayOf( "____ ",
                        "|__/ ",
                        "|  \\ ")
        'S' -> arrayOf( "____ ",
                        "[__  ",
                        "___] ")
        'T' -> arrayOf( "___ ",
                        " |  ",
                        " |  ")
        'U' -> arrayOf( "_  _ ",
                        "|  | ",
                        "|__| ")
        'V' -> arrayOf( "_  _ ",
                        "|  | ",
                        " \\/  ")
        'W' -> arrayOf( "_ _ _ ",
                        "| | | ",
                        "|_|_| ")
        'X' -> arrayOf( "_  _ ",
                        " \\/  ",
                        "_/\\_ ")
        'Y' -> arrayOf( "_   _ ",
                        " \\_/  ",
                         "  |   ")
        'Z' -> arrayOf( "___  ",
                        "  /  ",
                        " /__ ")
        else -> arrayOf("     ",
                        "     ",
                        "     ")
    }
}




/*
Stage 3/4: Make it notable

fun main() {
    val data = readInpt()
    printText(data)
}

fun readInpt(): Array<String> {

    print("Enter name and surname: ")
    val scanner = Scanner(System.`in`)
    val name = scanner.nextLine()

    print("Enter person's status: ")
    val status = scanner.nextLine()
    return arrayOf(name, status)
}

fun printText(array: Array<String>) {
    val output = Out(array)

    output.addStars()
    output.addName()
    output.addStatus()
    output.addStars()

    output.print()
}

class Out {
    val name: String
    var lengthName: Int = 0
    var indentName: Int = 0
    var indentNameLeft: Int = 0
    var indentNameRight: Int = 0

    val status: String
    var lengthStatus: Int = 0
    var indentStatus: Int = 0
    var indentStatusLeft: Int = 0
    var indentStatusRight: Int = 0

    var maxLength: Int
    val border: Int = 2 // width border without *
    val nameFont: Array<Simbol>
    var put: String = ""

    constructor(array: Array<String>) {
        name = array[0]
        nameFont = initStringNameByFontAndCountLengthName()

        status = array[1]
        lengthStatus = status.length

        maxLength = if (lengthName > lengthStatus) {
            indentStatus = lengthName - lengthStatus
            indentStatusLeft = indentStatus / 2
            indentStatusRight = indentStatus - indentStatusLeft
            lengthName + 6
        } else {
            indentName = lengthStatus  - lengthName
            indentNameLeft = indentName / 2
            indentNameRight = indentName - indentNameLeft
            lengthStatus + 6    // 6 = 2 borders * 3 width
        }
    }

    private fun initStringNameByFontAndCountLengthName(): Array<Simbol> {
        var arr: Array<Simbol> = emptyArray<Simbol>()
        for (i in 0 until name.length) {
            arr += Simbol(name[i])
            lengthName += arr[i].lengthOfChar
        }
        lengthName += name.length - 1    // + spaces between simbols
        return arr
    }

    fun addName() {
        for (i in 0..2) {
            addLeftBorder(border + indentNameLeft)
            addStringName(i)
            addRightBorder(indentNameRight + border)
        }
    }

    private fun addStringName(nomberOfString: Int) {
        put += nameFont[0].chr[nomberOfString]
        for (i in 1 until nameFont.size) {
            put += " " + nameFont[i].chr[nomberOfString]
        }
    }

    fun addStatus() {
        addLeftBorder(border + indentStatusLeft)
        addStringStatus()
        addRightBorder(indentStatusRight + border)
    }

    private fun addStringStatus() {
        put += status
    }

    private fun addLeftBorder(indent: Int = 2) {
        put += "*"
        repeat(indent) {
            put += " "
        }
    }

    private fun addRightBorder(indent: Int = 2) {
        repeat(indent) {
            put += " "
        }
        put += "*\n"
    }

    fun addStars() {
        repeat(maxLength) {
            put += '*'
        }
        put += "\n"
    }

    fun print() = print(put)
}

class Simbol {
    val ch: Char
    val chr: Array<String>
    val lengthOfChar: Int

    constructor(ch: Char) {
        this.ch = ch
        chr = getFont()
        lengthOfChar = chr[0].length
    }

    fun getFont() = when (ch.toUpperCase()) {
        'A' -> arrayOf( "____",
                "|__|",
                "|  |")
        'B' -> arrayOf( "___ ",
                "|__]",
                "|__]")
        'C' -> arrayOf( "____",
                "|   ",
                "|___")
        'D' -> arrayOf( "___ ",
                "|  \\",
                "|__/")
        'E' -> arrayOf( "____",
                "|___",
                "|___")
        'F' -> arrayOf( "____",
                "|___",
                "|   ")
        'G' -> arrayOf( "____",
                "| __",
                "|__]")
        'H' -> arrayOf( "_  _",
                "|__|",
                "|  |")
        'I' -> arrayOf( "_",
                "|",
                "|")
        'J' -> arrayOf( " _",
                " |",
                "_|")
        'K' -> arrayOf( "_  _",
                "|_/ ",
                "| \\_")
        'L' -> arrayOf( "_   ",
                "|   ",
                "|___")
        'M' -> arrayOf( "_  _",
                "|\\/|",
                "|  |")
        'N' -> arrayOf( "_  _",
                "|\\ |",
                "| \\|")
        'O' -> arrayOf( "____",
                "|  |",
                "|__|")
        'P' -> arrayOf( "___ ",
                "|__]",
                "|   ")
        'Q' -> arrayOf( "____",
                "|  |",
                "|_\\|")
        'R' -> arrayOf( "____",
                "|__/",
                "|  \\")
        'S' -> arrayOf( "____",
                "[__ ",
                "___]")
        'T' -> arrayOf( "___",
                " | ",
                " | ")
        'U' -> arrayOf( "_  _",
                "|  |",
                "|__|")
        'V' -> arrayOf( "_  _",
                "|  |",
                " \\/ ")
        'W' -> arrayOf( "_ _ _",
                "| | |",
                "|_|_|")
        'X' -> arrayOf( "_  _",
                " \\/ ",
                "_/\\_")
        'Y' -> arrayOf( "_   _",
                " \\_/ ",
                "  |  ")
        'Z' -> arrayOf( "___ ",
                "  / ",
                " /__")
        else -> arrayOf("    ",
                "    ",
                "    ")
    }
}
*/


/*
Stage 2/4: Star quality
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
*/


/*
Stage 1/4: Primitive Art
fun main() {
    println(" _____________\n" +
            "| Hyper Skill |\n" +
            " •••••••••••••")
}
*/
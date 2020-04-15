import java.io.*
import java.util.*

object Main {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        print("Input Location: ")
        val input = readLine()
        print("Output Location: ")
        val output = readLine()

        println("Modifying lines...")

        val fw = FileWriter(output)
        val start = System.currentTimeMillis()
        var x = 1

        File(input).forEachLine {
            var tempStr = it
            val list: MutableList<String> = ArrayList()

            for (i in 0 until it.length - it.replace("\t", "").length) {
                list.add(tempStr.substring(0, tempStr.indexOf("\t")))
                tempStr = tempStr.substring(tempStr.indexOf("\t") + 1)
            }

            if (list.size > 10) {
                if (list[1].toInt() and 32 == 32) {
                    list[3] = (list[3].toInt() + 4).toString()
                    list[8] = (list[8].toInt() - 9).toString()
                    list[9] = list[9].substring(4)
                    list[10] = list[10].substring(4)

                    var n: Int? = null
                    for (i in 0 until list[5].length) {
                        n = if (Character.isDigit(list[5][i])) {
                            i + 1
                        } else {
                            break
                        }
                    }
                    if (n != null) {
                        val re = list[5].substring(0, n).toInt()
                        val before = list[5]
                        list[5] = (re - 4).toString() + before.substring(n)
                    }
                }

                if (list[1].toInt() and 16 == 16) {
                    list[7] = (list[7].toInt() + 4).toString()
                    list[8] = (list[8].toInt() + 9).toString()
                    list[9] = list[9].substring(0, list[9].length - 5)
                    list[10] = list[10].substring(0, list[10].length - 5)

                    var n: Int? = null
                    for (i in list[5].length - 2 downTo 0) {
                        n = if (Character.isDigit(list[5][i])) {
                            i
                        } else {
                            break
                        }
                    }
                    if (n != null) {
                        val re = list[5].substring(n, list[5].length - 1).toInt()
                        val before = list[5]
                        list[5] = before.substring(0, n) + (re - 5) + before.substring(before.length - 1)
                    }
                }
            }

            for (i in list.indices) {
                fw.write(list[i])
                fw.write("\t")
            }

            fw.write("\n")
            x += 1
        }

        fw.close();
        val finish = System.currentTimeMillis() - start
        val elapsed = finish / 1000f
        if (x-1 != 0) {
            println("All lines complete, time elapsed: " + elapsed + " seconds")
        } else {
            println("Input file is empty")
        }
    }

}

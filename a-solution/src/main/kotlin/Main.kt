fun main() {

    println(
        listOf(2, 91, 981, 1, 4, 3, 5555, 6, 29, 8, 1).sort()
    )

//    val nums = readln().split(" ").map { it.toInt() }.toMutableList()
//    val results = mutableListOf<Int>()
//    repeat(nums.size, results::add)
//
//    var i = 1
//    var k = 1
//    while (nums.isNotEmpty()) {
//        k++
//        val maxValue = nums.maxOrNull()
//        val indexOfMax = nums.indexOf(maxValue)
//        results.add(indexOfMax, i)
//        nums.removeAt(indexOfMax)
//        if (k % 2 == 0) {
//            i++
//        }
//    }
//
//    results.forEach{
//        print("$it ")
//    }
}

fun List<Int>.sort(acc: List<Int> = this): List<Int> {
    return if (size < 2) acc else {
        val pivot = first()
        val less = subList(1, size).filter { it <= pivot }
        val greater = subList(1, size).filter { it > pivot }
        less.sort() + pivot + greater.sort()
    }
}

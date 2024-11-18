
import kotlin.math.abs

class Solution {

    fun decrypt(code: IntArray, subarraySize: Int): IntArray {
        val decryptedCode = IntArray(code.size)
        if (subarraySize == 0) {
            return decryptedCode
        }

        val decryptFromPreceedingSubarray = subarraySize < 0
        val subarraySize = abs(subarraySize)
        var currentDecryption = initializeCurrentDecryption(code, subarraySize)

        for (i in code.indices) {
            if (decryptFromPreceedingSubarray) {
                decryptedCode[(i + subarraySize) % code.size] = currentDecryption
            }

            currentDecryption -= code[i]
            currentDecryption += code[(i + subarraySize) % code.size]

            if (!decryptFromPreceedingSubarray) {
                decryptedCode[i] = currentDecryption
            }
        }

        return decryptedCode
    }

    private fun initializeCurrentDecryption(code: IntArray, subarraySize: Int): Int {
        var currentDecryption = 0
        for (i in 0..<subarraySize) {
            currentDecryption += code[i]
        }
        return currentDecryption
    }
}

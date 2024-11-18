
package main

import (
    "fmt"
    "math"
)

func decrypt(code []int, subarraySize int) []int {
    decryptedCode := make([]int, len(code))
    if subarraySize == 0 {
        return decryptedCode
    }

    decryptFromPreceedingSubarray := subarraySize < 0
    subarraySize = int(math.Abs(float64(subarraySize)))
    currentDecryption := initializeCurrentDecryption(code, subarraySize)

    for i := range code {
        if decryptFromPreceedingSubarray {
            decryptedCode[(i + subarraySize) % len(code)] = currentDecryption
        }

        currentDecryption -= code[i]
        currentDecryption += code[(i + subarraySize) % len(code)]

        if !decryptFromPreceedingSubarray {
            decryptedCode[i] = currentDecryption
        }
    }

    return decryptedCode
}

func initializeCurrentDecryption(code []int, subarraySize int) int {
    currentDecryption := 0
    for i := 0; i < subarraySize; i++ {
        currentDecryption += code[i]
    }
    return currentDecryption
}

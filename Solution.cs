
using System;

public class Solution
{
    public int[] Decrypt(int[] code, int subarraySize)
    {
        int[] decryptedCode = new int[code.Length];
        if (subarraySize == 0)
        {
            return decryptedCode;
        }

        bool decryptFromPreceedingSubarray = subarraySize < 0;
        subarraySize = Math.Abs(subarraySize);
        int currentDecryption = InitializeCurrentDecryption(code, subarraySize);

        for (int i = 0; i < code.Length; ++i)
        {
            if (decryptFromPreceedingSubarray)
            {
                decryptedCode[(i + subarraySize) % code.Length] = currentDecryption;
            }

            currentDecryption -= code[i];
            currentDecryption += code[(i + subarraySize) % code.Length];

            if (!decryptFromPreceedingSubarray)
            {
                decryptedCode[i] = currentDecryption;
            }
        }

        return decryptedCode;
    }

    private int InitializeCurrentDecryption(int[] code, int subarraySize)
    {
        int currentDecryption = 0;
        for (int i = 0; i < subarraySize; ++i)
        {
            currentDecryption += code[i];
        }
        return currentDecryption;
    }
}

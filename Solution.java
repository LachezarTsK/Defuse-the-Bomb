
public class Solution {

    public int[] decrypt(int[] code, int subarraySize) {
        int[] decryptedCode = new int[code.length];
        if (subarraySize == 0) {
            return decryptedCode;
        }

        boolean decryptFromPreceedingSubarray = subarraySize < 0;
        subarraySize = Math.abs(subarraySize);
        int currentDecryption = initializeCurrentDecryption(code, subarraySize);

        for (int i = 0; i < code.length; ++i) {
            if (decryptFromPreceedingSubarray) {
                decryptedCode[(i + subarraySize) % code.length] = currentDecryption;
            }

            currentDecryption -= code[i];
            currentDecryption += code[(i + subarraySize) % code.length];

            if (!decryptFromPreceedingSubarray) {
                decryptedCode[i] = currentDecryption;
            }
        }

        return decryptedCode;
    }

    private int initializeCurrentDecryption(int[] code, int subarraySize) {
        int currentDecryption = 0;
        for (int i = 0; i < subarraySize; ++i) {
            currentDecryption += code[i];
        }
        return currentDecryption;
    }
}

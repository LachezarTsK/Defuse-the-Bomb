
function decrypt(code: number[], subarraySize: number): number[] {
    const decryptedCode: number[] = new Array(code.length).fill(0);
    if (subarraySize === 0) {
        return decryptedCode;
    }

    const decryptFromPreceedingSubarray = subarraySize < 0;
    subarraySize = Math.abs(subarraySize);
    let currentDecryption = initializeCurrentDecryption(code, subarraySize);

    for (let i = 0; i < code.length; ++i) {
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
};


function initializeCurrentDecryption(code: number[], subarraySize: number): number {
    let currentDecryption = 0;
    for (let i = 0; i < subarraySize; ++i) {
        currentDecryption += code[i];
    }
    return currentDecryption;
}


#include <span>
#include <cmath>
#include <vector>
using namespace std;

class Solution {

public:
    vector<int> decrypt(const vector<int>& code, int subarraySize) const {
        vector<int> decryptedCode(code.size());
        if (subarraySize == 0) {
            return decryptedCode;
        }

        bool decryptFromPreceedingSubarray = subarraySize < 0;
        subarraySize = abs(subarraySize);
        int currentDecryption = initializeCurrentDecryption(code, subarraySize);

        for (size_t i = 0; i < code.size(); ++i) {
            if (decryptFromPreceedingSubarray) {
                decryptedCode[(i + subarraySize) % code.size()] = currentDecryption;
            }

            currentDecryption -= code[i];
            currentDecryption += code[(i + subarraySize) % code.size()];

            if (!decryptFromPreceedingSubarray) {
                decryptedCode[i] = currentDecryption;
            }
        }

        return decryptedCode;
    }

private:
    int initializeCurrentDecryption(span<const int> code, int subarraySize) const {
        int currentDecryption = 0;
        for (size_t i = 0; i < subarraySize; ++i) {
            currentDecryption += code[i];
        }
        return currentDecryption;
    }
};

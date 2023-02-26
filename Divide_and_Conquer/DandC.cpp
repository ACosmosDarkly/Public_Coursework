#include <iostream>
#include <vector>
#include <ctime> // ctime is only required for test case. Not a part of the algorithm

using namespace std;

const int ZERO = 0;
const int ONE = 1;
const int TWO = 2;

void searchFunction(vector<int>& initialVector, vector<int>& minMaxVector) {

    vector<int> firstHalf;
    vector<int> secondHalf;
    vector<int> minMaxVector2;
    int center = ZERO;

    // Base case: If one value in array, it is the min and max
    if(initialVector.size() == ONE ) {
        minMaxVector.push_back(initialVector[0]); // pushback twice to make it easier to cout in main
        minMaxVector.push_back(initialVector[0]);
    }
    // If two values in array, compare and return the min and max
    else if(initialVector.size() == TWO) {
        if(initialVector[0] <= initialVector[1]) {
            minMaxVector.push_back(initialVector[0]);
            minMaxVector.push_back(initialVector[1]);
        }
        else {
            minMaxVector.push_back(initialVector[1]);
            minMaxVector.push_back(initialVector[0]);
        }
    }
    // If more than two items in the array, find the center, push first half to vector
    // then push second half to another vector, then call recursive functions.
    // Use the output from recursive calls to do two final comparisons and end the function.
    else {
        center = initialVector.size()/2;
        for(int i = 0; i < center; i++) {
            firstHalf.push_back(initialVector[i]);
        }
        for(int i = center; i < initialVector.size(); i++) {
            secondHalf.push_back(initialVector[i]);
        }
        searchFunction(firstHalf, minMaxVector);
        searchFunction(secondHalf, minMaxVector2);

        if(minMaxVector[0] >= minMaxVector2[0]) {
            minMaxVector[0] = minMaxVector2[0];
        }
        if(minMaxVector[1] <= minMaxVector2[1]) {
            minMaxVector[1] = minMaxVector2[1];
        }
    }
    return;
}

int main() {
    
    srand(time(0));
    vector<int> initialVector;
    vector<int> minMaxVector;

    // Test case: pseudo-random numbers 1 through 100 generated into array of 13 (or any n) integers
    // Fills the array then prints the numbers in the array before finding min/max.
    for(int i = 0; i < 13; i++) {
        initialVector.push_back((rand()%100)+1);
    }
    for(int i = 0; i < 13; i++) {
        cout << initialVector[i] << " ";
    }

    cout<<endl;
    searchFunction(initialVector, minMaxVector);

    cout << "The max in array is: " << minMaxVector[1] << endl;
    cout << "The min in array is: " << minMaxVector[0] << endl;

    return 0;
}
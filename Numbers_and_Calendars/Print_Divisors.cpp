#include <iostream>
#include <cmath>
using namespace std;

const int ZERO = 0;
const int ONE = 1;

void printDivisors(int num);

int main() {

    int num = ZERO;

    cout<<"Please enter a positive integer >= 2: ";
    cin>>num;

    printDivisors(num);

    return 0;
}

void printDivisors(int num) {

    // Finds the divisors that are less than sqrt(num) in ascending order
    for(int i = ONE; i <= sqrt(num); i++) {
        if(num%i == ZERO) {
            if(num/i == i) {
                cout<<i<<" ";
            }
            else {
                cout<<i<<" ";
            }
        }
    }

    // Finds that divisors that are greater than sqrt(num) in ascending order
    for(int i = sqrt(num); i >= ONE; i--) {
        if(num%i == ZERO && num/i != i) {
            cout<<num/i<<" ";
        }
    }

    cout<<endl;

    return;

}
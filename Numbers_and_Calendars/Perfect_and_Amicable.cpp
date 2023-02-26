#include <iostream>
#include <cmath>
using namespace std;

const int ZERO = 0;
const int ONE = 1;
const int TWO = 2;

void analyzeDividors(int num, int& outCountDivs, int& outSumDivs);
bool isPerfect(int num);

int main() {
    
    int userNumber = ZERO;

    cout<<"Please enter a positive integer. Integer must be 2 or larger: ";
    cin>>userNumber;

    while (userNumber < TWO) {
        cout<<"Please enter a positive integer. Integer must be 2 or larger: ";
        cin>>userNumber;
    }

    cout<<"Perfect numbers between 2 and "<<userNumber<<endl;

    for(int i = TWO; i <= userNumber; i++) {
        if(isPerfect(i) > ZERO && i > ONE) {
            cout<<i<<" is a perfect number."<<endl;
        }
    }

    cout<<endl;
    cout<<"Amicable numbers between 2 and "<<userNumber<<endl;

    for(int i = TWO; i <= userNumber; i++) {

        int divCount1 = ZERO, sumCount1 = ZERO;
        int divCount2 = ZERO, sumCount2 = ZERO;

        analyzeDividors(i, divCount1, sumCount1);
        analyzeDividors(sumCount1, divCount2, sumCount2);

        if((i == sumCount2) && (i != sumCount1) && (sumCount1 < sumCount2)) {
            cout<<i<<" and "<<sumCount1<<endl;
        }
    }

    cout<<endl;

    return 0;
}

void analyzeDividors(int num, int& outCountDivs, int& outSumDivs) {

    for(int i = ONE; i <= sqrt(num); i++) {
        if(num%i == ZERO) {
            if(num/i != num) {
                outCountDivs += TWO;
                outSumDivs += i;
                outSumDivs += num/i;
            }
            else {
                outCountDivs ++;
                outSumDivs += i;
            }
        }
    }

    return;
}

bool isPerfect(int num) {

    int count = ZERO;
    int numberSum = ZERO;

    analyzeDividors(num, count, numberSum);

    return (num == numberSum);
}
#include <iostream>
using namespace std;

const int ZERO = 0;
const double ZERO_D = 0.0;
const double ONE_D = 1.0;

double eApprox(int n);

int main() {
    cout.precision(30);

    for (int n = 1; n <= 15; n++) {
        cout<<"n = "<<n<<'\t'<<eApprox(n)<<endl;
    }

    return 0;
}

double eApprox(int n) {

    double e = ONE_D;
    double factorial = ONE_D;

    for(int i = ONE_D; i < n; i++) {
     
     factorial *= i;
     e += ONE_D / factorial;

    }

    return e;
}
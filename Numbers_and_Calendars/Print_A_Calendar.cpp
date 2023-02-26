#include <iostream>
using namespace std;

// Day constant values
const int MONDAY = 1;
const int TUESDAY = 2;
const int WEDNESDAY = 3;
const int THURSDAY = 4;
const int FRIDAY = 5;
const int SATURDAY = 6;
const int SUNDAY = 7;

// Month constant values
const int JANUARY = 1;
const int FEBRUARY = 2;
const int MARCH = 3;
const int APRIL = 4;
const int MAY = 5;
const int JUNE = 6;
const int JULY = 7;
const int AUGUST = 8;
const int SEPTEMBER = 9;
const int OCTOBER = 10;
const int NOVEMBER = 11;
const int DECEMBER = 12;

// Standard value constants for general use
const int ZERO = 0;
const int ONE = 1;
const int FOUR = 4;
const int SEVEN = 7;
const int TWELVE = 12;
const int TWENTY_EIGHT = 28;
const int TWENTY_NINE = 29;
const int THIRTY = 30;
const int THIRTY_ONE = 31;
const int ONE_HUNDRED = 100;
const int FOUR_HUNDRED = 400;

// Character constant values
const char BLANK = ' ';
const char TAB = '\t';

int printMonthCalendar (int numofDays, int startingDay);
void printYearCalendar (int year, int startingDay);
void printMonth (int num);
bool isLeapYear (int year);

int main() {

    int calendarYear = ZERO;
    int calendarDay = ZERO;

    cout<<"Please enter the calendar year to print: "<<endl;
    cin>>calendarYear;
    cout<<"Please enter the first day of the year as a number: (Mon-Sun = 1-7)"<<endl;
    cin>>calendarDay;

    printYearCalendar(calendarYear, calendarDay);

    return 0;

}

int printMonthCalendar (int numOfDays, int startingDay) {

    int dayOfWeek = ZERO;
    int monthBlanks = ZERO;

    cout<<"Mon"<<TAB<<"Tue"<<TAB<<"Wed"<<TAB<<"Thr"<<TAB<<"Fri"<<TAB<<"Sat"<<TAB<<"Sun"<<endl;

    switch (startingDay)
    {
        case MONDAY:
            dayOfWeek = MONDAY;
            break;
        case TUESDAY:
            dayOfWeek = TUESDAY;
            break;
        case WEDNESDAY:
            dayOfWeek = WEDNESDAY;
            break;
        case THURSDAY:
            dayOfWeek = THURSDAY;
            break;
        case FRIDAY:
            dayOfWeek = FRIDAY;
            break;
        case SATURDAY:
            dayOfWeek = SATURDAY;
            break;
        case SUNDAY:
            dayOfWeek = SUNDAY;
            break;
        default:
            break;
    }

    for(int i = ZERO; i < dayOfWeek - ONE; i++) {
        cout<<BLANK<<TAB;
    }

    int weekCount = dayOfWeek;
    int dayCount = ONE;
    int lastDayOfMonth = dayOfWeek;

    for (int i = ZERO; i < numOfDays; i++) {

        if(weekCount > SEVEN) {
            cout<<endl;
            weekCount = ONE;
            lastDayOfMonth = ZERO;
        }

        cout<<dayCount<<TAB;
        dayCount++;
        weekCount++;
        lastDayOfMonth++;

    }

    cout<<endl;

    if(lastDayOfMonth == SEVEN) {
        lastDayOfMonth = ONE;
    }
    else {
        lastDayOfMonth += ONE;
    }

    return lastDayOfMonth;

}

void printYearCalendar (int year, int startingDay) {

    isLeapYear(year);

    for(int i = ONE; i <= TWELVE; i++) {

        printMonth(i);
        cout<<" "<<year<<endl;

        if(i == JANUARY || i == MARCH || i == MAY || i == JULY || i == AUGUST || i == OCTOBER || i == DECEMBER) {
            startingDay = printMonthCalendar(THIRTY_ONE, startingDay);
        }
        else if (i == APRIL || i == JUNE || i == SEPTEMBER || i == NOVEMBER) {
            startingDay = printMonthCalendar(THIRTY, startingDay);
        }
        else if (i == FEBRUARY){
            if(isLeapYear(year)) {
                startingDay = printMonthCalendar(TWENTY_NINE, startingDay);
            }
            else{
                startingDay = printMonthCalendar(TWENTY_EIGHT, startingDay);
            }
        }

        cout<<endl;
    }

    return;
}

void printMonth (int num) {

    switch (num)
        {
        case JANUARY:
            cout<<"January";
            break;
        case FEBRUARY:
            cout<<"February";
            break;
        case MARCH:
            cout<<"March";
            break;
        case APRIL:
            cout<<"April";
            break;
        case MAY:
            cout<<"May";
            break;
        case JUNE:
            cout<<"June";
            break;
        case JULY:
            cout<<"July";
            break;
        case AUGUST:
            cout<<"August";
            break;
        case SEPTEMBER:
            cout<<"September";
            break;
        case OCTOBER:
            cout<<"October";
            break;
        case NOVEMBER:
            cout<<"November";
            break;
        case DECEMBER:
            cout<<"December";
            break;
        default:
            break;
        }

    return;

}

bool isLeapYear (int year) {

    return ((year % FOUR == ZERO) && (year % ONE_HUNDRED != ZERO)) || ((year % FOUR == ZERO) && (year % ONE_HUNDRED == ZERO) && (year % FOUR_HUNDRED == ZERO));

}
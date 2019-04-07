package UTILS

// ---- m a i n ----

println("Investing in Prestige Gaming Club")

int InitialAmount = 10000
int InterestRate = 10
int TotalMonths = 24
int MyInvestment = InitialAmount
int Interest = 0

for ( int i=0; i<TotalMonths; i++ ) {

    println("Month: " + i + " Interest: " + Interest + " MyInvestment: " + MyInvestment)

    Interest = MyInvestment * InterestRate / 100

    MyInvestment = MyInvestment + Interest

    if ( i == 12 ) {
        //MyInvestment += 10000
    }

}



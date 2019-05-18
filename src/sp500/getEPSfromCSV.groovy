package sp500

// ------- M A I N --------
beginProcessing()
// ------------------------


void beginProcessing() {
    //println("Parsing SP500 CSV")

    readCSV()
}


void readCSV() {
    File fDir = new File("/home/nileshmune/workspace/FinancialAnalysis/data/sp500")

    // print the headers
    println("Stock,E1,E2,E3,E4,E5,E6,P1,P2,P3,P4,P5,P6,PE1,PE2,PE3,PE4,PE5,PE6")

    int iCount = 0
    fDir.eachFile { fName ->
        if ( fName.name.endsWith(".csv") ) {
            iCount++
            getEPS( fName )
        }
    }
    //println("Total CSV processed : " + iCount)
}


void getEPS( File fName ) {

    ArrayList<String> fLines = new ArrayList<String>()
    fLines = fName.readLines()
    int fSize = fLines.size()
    if ( fSize <= 1 ) {
        return
    }

    String [] sTokens
    String sDate

    int iCount = 0
    ArrayList<Float> fEPS = new ArrayList<Float>()
    ArrayList<Float> fPrice = new ArrayList<Float>()
    ArrayList<Float> fPEratio = new ArrayList<Float>()
    String tEPS, tPrice, tPEratio

    // parse the lines read
    for ( int i=0; i<fSize; i++ ) {

        // skip the first line
        if ( i == 0 ) { continue    }

        // tokenize the record
        sTokens = fLines.get( i ).split(",")

        // date is 0, eps diluted is 17 and price is 25, PE ratio is 32
        tEPS = sTokens[17]
        if ( tEPS == "None" ) { tEPS = 0 }
        fEPS[iCount] = Float.parseFloat( tEPS )

        tPrice = sTokens[25]
        if ( tPrice == "None" ) { tPrice = 0 }
        fPrice[iCount] = Float.parseFloat( tPrice )

        tPEratio = sTokens[32]
        if ( tPEratio == "None" ) { tPEratio = 0 }
        fPEratio[iCount] = Float.parseFloat( tPEratio )

        iCount++

        if ( iCount >=6 ) { break }
    }

    // display the filename, EPS array and Price array and PE ratio array
    println( fName.name + "," + fEPS.reverse().join(",") + ", " + fPrice.reverse().join(",") + ", " + fPEratio.reverse().join(","))
}

package RESTapi

String fName="../../data/EPS_AvgPctChange.csv"

// read this file
File fEPS = new File( fName )
String[] sTokens
String sTicker
float fPctChange
int iNumOfEntries

fEPS.eachLine { line ->
    // each line contains:
    // Ticker.out, EPS percentage change, number of entries
    // CX.out,7851.0,50
    // I need Tickers for percentage above 300 and num > 40

    sTokens = line.split(",")

    sTicker = sTokens[0]
    fPctChange = Float.parseFloat( sTokens[1] )
    iNumOfEntries = Integer.parseInt( sTokens[2] )

    if ( fPctChange >= 300.0 && iNumOfEntries > 40 ) {
        println(sTicker + "," + fPctChange + "," + iNumOfEntries)
    }


}


package EPS

// ----- M A I N

StockRecords stockRecords = new StockRecords()

String Dir="/home/nileshmune/workspace/MktValModel/data/EPS"
//String Dir="/home/nileshmune/workspace/MktValModel/data/testEPS"
File filesEPS = new File ( Dir )


filesEPS.eachFile { fName ->

    if ( fName.toString().contains("out") ) {

        stockRecords = fReadEPS(fName)

        //println("StockRecords length " + stockRecords.getSize() )
        stockRecords.display()
    }

}

//println("Total files : " + i)


// --------------------------------------------------------------------

StockRecords fReadEPS( File fName ) {

    StockRecords stockRecords = new StockRecords()

    int iThreshold = 5
    int iCount = 0

    int fSize = fName.size()
    if ( fSize <= 20 ) {  return  }

    String sMonthDate, sMonth
    int iDate, iYear
    float fEPS
    String [] sTokens
    float fStockPrice
    String sSymbol = fName.name.replace(".out","")
    stockRecords.setsSymbol( sSymbol )

    fName.eachLine { line ->
        if ( line.size() >= 1 ) {
            // tokenize the string
            sTokens = line.split(",")

            if ( sTokens[0] != "Minimum" && sTokens[0] != "Maximum" && sTokens[0] != "Average" ) {

                //println("Size : " + sTokens.size())
                // process only recent iThreshold number of records
                if (sTokens.size() == 3 && iCount <= iThreshold) {

                    //println("Processing line : " + line)

                    // process this record containing format : Dec. 31, 2018, 0.86
                    sMonthDate = sTokens[0].trim()
                    iYear =  (sTokens[1].trim().length() != 0 ) ? Integer.parseInt(sTokens[1].trim()) : null
                    fEPS =   (sTokens[2].trim().length() != 0 ) ? Float.parseFloat(sTokens[2].trim()) : null

                    if ( sMonthDate.size() != 0 && iYear != null && fEPS != null ) {
                        stockRecords.AddRecord(sMonthDate + "," + iYear, fEPS)
                        iCount++
                    }
                }
            }

        }
    }

    return stockRecords
}
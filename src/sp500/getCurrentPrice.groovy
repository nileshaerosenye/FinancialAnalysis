package sp500

// ----- M A I N -----
beginProcessing()
// ------- END -------

void beginProcessing () {

    //println("Reading all Tickers")

    getCurrentStockPrice()
}

void getCurrentStockPrice() {

    String sBase, sResult

    File fRead = new File("../../data/sp500")
    fRead.eachFile { fName ->
        String sTicker = fName.name.replace(".csv","").trim()

        // Call the API to get the ticker details
        sBase = "https://api.iextrading.com/1.0/stock/" + sTicker + "/price"

        try {
            sResult = sBase.toURL().text
        }
        catch ( Exception e ) {
            sResult = "Err"
        }

        println(sTicker + ", " + sResult)
    }

}

package Cron

import groovy.json.JsonSlurper

// ----- M A I N -----
beginProcessing()
// ------- END -------

void beginProcessing () {

    //println("Reading all Tickers")

    getIEXmostActiveStocks()
}

void getIEXmostActiveStocks() {

    JsonSlurper jsonSlurper = new JsonSlurper()
    File fDir = new File("/home/nileshmune/workspace/FinancialAnalysis/data/mostActive/")

    ArrayList<String> allRecords = new ArrayList<String>()
    String fDate

    fDir.eachFile { fName ->
        if ( fName.name.endsWith("json")) {

            //println("------ ${fName} ------")
            Object jsonRecs = jsonSlurper.parse( fName )

            jsonRecs.each { rec ->
                //println( "Symbol: " + rec.symbol + ", Volume: " + rec.latestVolume + ", MarketCap: " + rec.marketCap + ", peRatio: " + rec.peRatio + ", Name: " + rec.companyName + ", Close: " + rec.close )
                fDate = fName.name.toString()
                allRecords.add(fDate + " | " + "Symbol: " + rec.symbol + ", Volume: " + rec.latestVolume + ", MarketCap: " + rec.marketCap + ", peRatio: " + rec.peRatio + ", Name: " + rec.companyName + ", Close: " + rec.close)
            }
        }
    }

    ArrayList<String> sortedRecords = allRecords.sort()
    sortedRecords.each { record ->
        println(record)
    }

}

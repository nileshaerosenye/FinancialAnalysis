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

    def date = new Date()
    String sInFile = "/home/nileshmune/workspace/FinancialAnalysis/data/Cron/mostActive." + date.format("yyyyMMdd") + ".json"

    JsonSlurper jsonSlurper = new JsonSlurper()

    File fIN = new File(sInFile)

    Object jsonRecs = jsonSlurper.parse( fIN )

    jsonRecs.each { rec ->
        println( "Symbol: " + rec.symbol + ", Name: " + rec.companyName + ", Close: " + rec.close + ", Volume: " + rec.latestVolume + ", MarketCap: " + rec.marketCap + ", peRatio: " + rec.peRatio)
        println()
    }

}

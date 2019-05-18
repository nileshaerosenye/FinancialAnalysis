package Cron

import groovy.json.JsonOutput
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
    String sOutFile = "/home/nileshmune/workspace/FinancialAnalysis/data/Cron/mostActive." + date.format("yyyyMMdd") + ".json"
    String sBase
    HttpURLConnection httpURLConnection
    JsonSlurper jsonSlurper = new JsonSlurper()

    File out = new File(sOutFile)

    // Call the API to get the ticker details
    sBase = "https://cloud.iexapis.com/stable/stock/market/list/mostactive?token=pk_35b4d7c47d1b4a9ba01624eb7f43fc5f"

    httpURLConnection = new URL( sBase ).openConnection()
    def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())


    out.write( JsonOutput.toJson ( result ) )

}

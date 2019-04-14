package RESTapi

import groovy.json.JsonSlurper

// M A I N
println("Reading all Tickers")
String sOut = "../../data/EPS_above300_PEratio"
File fOut = new File( sOut )

ArrayList<String> aMtkCap = readTickers()
println("Main | total symbols : " + aMtkCap.size())
println("Writing to : " + sOut)
aMtkCap.each { value ->
    //fOut.write( value )
    fOut.append( value + "\n")
}



// ----- Supporting functions

ArrayList<String> readTickers () {

    def date = new Date()
    String today = date.format("yyyyMMdd")

    String fName = "../../data/EPS_above300"
    //String fName = "../../data/sample"
    File fTicks = new File( fName )
    String[] sTokens
    String sTicker, sBase, sCompanyName
    float fEPS
    float fStockPrice
    float fPEratio
    ArrayList<String> aMktCap = new ArrayList<String>()

    HttpURLConnection httpURLConnection
    JsonSlurper jsonSlurper = new JsonSlurper()

    int iLineCount = 0
    fTicks.eachLine { line ->

        // split the csv line
        sTokens = line.split(",")
        sTicker = sTokens[0]
        printf "%5d\r", iLineCount

        // Call the API to get the ticker details
        sBase = "https://api.iextrading.com/1.0/stock/" + sTicker + "/stats"
        httpURLConnection = new URL( sBase ).openConnection()

        try {
            def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())
            sCompanyName = result.companyName
            fEPS = result.latestEPS

            //aMktCap.add(sTicker + "| " + result.companyName + "| " + result.fEPS)
            //iLineCount++
        }
        catch ( Exception e ) {
            println("Error processing : " + sTicker)
        }

        // Call the API to get the stock price details
        sBase = "https://api.iextrading.com/1.0/stock/" + sTicker + "/chart/date/" + today + "?chartLast=1"
        println("Base is : " + sBase)
        httpURLConnection = new URL( sBase ).openConnection()

        try {
            def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())
            fStockPrice = result.average[0]

            fPEratio = fStockPrice / fEPS
            aMktCap.add(sTicker + "| " + fEPS + "| " + fStockPrice + "| " + fPEratio )


            iLineCount++
        }
        catch ( Exception e ) {
            println("Error processing : " + sTicker)
            println(e.toString())
        }
    }

    return aMktCap
}


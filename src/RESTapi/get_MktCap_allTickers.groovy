package RESTapi

import groovy.json.JsonSlurper

// M A I N
println("Reading all Tickers")
String sOut = "../../data/MktCap_allTickers.csv"
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

    String fName = "../../data/NASDAQ_NYSE_tickers.csv"
    //String fName = "../../data/sample"
    File fTicks = new File( fName )
    String[] sTokens
    String sTicker, sBase, sCompanyName
    int iMktCap
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
            iMktCap = result.marketcap

            aMktCap.add(sTicker + "| " + result.companyName + "| " + result.marketcap)

            iLineCount++
        }
        catch ( Exception e ) {
            println("Error processing : " + sTicker)
        }
    }

    return aMktCap
}


package EPS

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

// ----- M A I N

StockRecords stockRecords = new StockRecords()

String Dir="/home/nileshmune/workspace/MktValModel/data/EPS"
//String Dir="/home/nileshmune/workspace/MktValModel/data/testEPS"
File filesEPS = new File ( Dir )


filesEPS.eachFile { fName ->

    if ( fName.toString().contains("out") ) {
        try {
            println(fName)
            fDownload(fName)
        }
        catch( Exception e ) {
            println( fName +  " : failed")
        }
    }

}

//println("Total files : " + i)


// --------------------------------------------------------------------

void fDownload( File fName ) {

    int fSize = fName.size()
    if ( fSize <= 20 ) {  return  }

    String sSymbol = fName.name.replace(".out","")

    String sBase="https://api.iextrading.com/1.0/stock/" + sSymbol + "/chart/5y"
    JsonSlurper jsonSlurper = new JsonSlurper()
    HttpURLConnection httpURLConnection
    httpURLConnection = new URL( sBase ).openConnection()

    Object result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())

    String sOut = "../../data/historical_data/" + sSymbol + ".csv"
    File fOut = new File( sOut )

    fOut.write( new JsonBuilder(result).toPrettyString() )
}
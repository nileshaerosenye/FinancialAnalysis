package EPS

import groovy.json.JsonSlurper

class StockRecords {

    private String sSymbol
    private ArrayList< StockInfo > aStockInfo

    StockRecords () {
        aStockInfo = new ArrayList<StockInfo>()
    }

    void setsSymbol( String sSymbol ) {
        this.sSymbol = sSymbol
    }

    int getSize () {
        return aStockInfo.size()
    }

    void AddRecord( String sDate, float fEPS ) {

        // get the formatted date
        String formattedDate = getFormattedDate ( sDate )

        // get the stock price for this date
        float fStockPrice = getStockPrice( formattedDate )

        aStockInfo.add( new StockInfo(formattedDate, fEPS, fStockPrice) )
    }

    float getStockPrice ( String sDate ) {
        float fStockPrice = 0.0f

        // Split the sDate
        String [] sTokens = sDate.split("-")

        String YYYY_MM = sTokens[0] + "-" + sTokens[1]

        // get the day of the month part from formatted date
        int dayOfMonth = Integer.parseInt( sTokens[2].trim() )
        //println("Searching stock price for date : " + sDate + " Day : " + dayOfMonth)

        String sBase="https://api.iextrading.com/1.0/stock/" + this.sSymbol + "/chart/5y"
        JsonSlurper jsonSlurper = new JsonSlurper()
        HttpURLConnection httpURLConnection
        httpURLConnection = new URL( sBase ).openConnection()

        Object result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())
        Object outResult = null
        String searchDate

        while ( outResult == null && dayOfMonth > 1 ) {

            searchDate = YYYY_MM + "-" + dayOfMonth.toString()
            //println("SearchDate : " + searchDate)
            outResult = result.find { rec ->
                rec.date == searchDate
            }
            dayOfMonth--
        }

        return (outResult != null) ? outResult.close : 0
    }

    String getFormattedDate ( String InDate ) {
        // extract the month, date, year from date
        String[] sTokens = InDate.split(",")
        int iYear = Integer.parseInt( sTokens[1].trim() )
        String sDate

        switch (sTokens[0].trim()) {
            case "Jan. 31":
            case "January 31":
                sDate = iYear + "-01-31"
                break

            case "Feb. 28":
            case "February 28":
                sDate = iYear + "-02-28"
                break

            case "Feb. 29":
            case "February 29":
                sDate = iYear + "-02-29"
                break

            case "Mar. 31":
            case "March 31":
                sDate = iYear + "-03-31"
                break

            case "Apr. 30":
            case "April 30":
                sDate = iYear + "-04-30"
                break

            case "May. 31":
            case "May 31":
                sDate = iYear + "-05-31"
                break

            case "Jun. 30":
            case "June 30":
                sDate = iYear + "-06-30"
                break

            case "July. 31":
            case "July 31":
                sDate = iYear + "-07-31"
                break

            case "Aug. 31":
            case "August 31":
                sDate = iYear + "-08-31"
                break

            case "Sept. 30":
            case "September 30":
                sDate = iYear + "-09-30"
                break

            case "Oct. 31":
            case "October 31":
                sDate = iYear + "-10-31"
                break

            case "Nov. 30":
            case "November 30":
                sDate = iYear + "-11-30"
                break

            case "Dec. 31":
            case "December 31":
                sDate = iYear + "-12-31"
                break
        }

        return sDate
    }

    void display() {
        int len = aStockInfo.size()
        if ( len == 0 ) {
            println("Empty")
        }
        else {
            print ( this.sSymbol )
            for ( StockInfo sT : aStockInfo ) {
                sT.StockPrint()
            }
            println()
        }
    }

}

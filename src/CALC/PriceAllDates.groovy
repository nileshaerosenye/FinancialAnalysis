package CALC

import groovy.json.JsonSlurper

// ----------------------------------------
//
// Read ../../data/Dates_from2016.dat file containing all valid dates from 2016 onwards

// Entry point ----
main()
// Exit point

void main () {
    ArrayList<String> sDates = new ArrayList<String>()
    ArrayList<PriceObj> PriceObjects = new ArrayList<PriceObj>()

    sDates = readDates()
    //println("Total dates read : " + sDates.size())
    PriceObjects = getHistoricalPrices( sDates )

    //println("Total PriceObjects : " + PriceObjects.size() )

    print("Stock,")
    // display top row of dates
    sDates.each { iDate ->
        print( iDate + "," )
    }
    println()

    // show values for each stock and their prices with dates
    PriceObjects.each { PO ->
        PO.display( sDates )
    }
}

ArrayList<PriceObj> getHistoricalPrices( ArrayList<String> sDates ) {

    ArrayList<PriceObj> priceObjects = new ArrayList<PriceObj>()

    // For each file in the historical data directory,
    String sPath = "../../data/historical_data"
    //String sPath = "../../data/historical_data_sample"
    File fDir = new File( sPath )
    Object jsonParsed

    JsonSlurper jsonSlurper = new JsonSlurper()

    fDir.eachFile { fName ->
        jsonParsed = jsonSlurper.parse( fName )

        Map<String,Float> datePrice = new HashMap<String,Float>()
        jsonParsed.each { rec ->
            sDates.each { iDate ->
                if ( iDate == rec.date ) {
                    datePrice[ iDate ] = rec.close
                    //println( iDate + " : " + rec.close)
                }
            }
        }

        priceObjects.add( new PriceObj( fName.name.replace(".csv",""), datePrice) )

    }

    // read the file as json
    // for each sDate, get the price
    // if price is null then insert 0
    // display the record with value for each of the 829 entries and create csv file
    return priceObjects
}


ArrayList<String> readDates()
{
    ArrayList<String> sDates = new ArrayList<String>()
    String sName = "../../data/Dates_from2016"
    File fDates = new File( sName )
    fDates.eachLine { date ->
        sDates.add( date.trim() )
    }
    return sDates
}


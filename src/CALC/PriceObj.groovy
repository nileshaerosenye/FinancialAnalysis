package CALC

class PriceObj {

    String StockName
    Map<String, Float> datePrice = new HashMap<String, Float>()

    PriceObj( String StockName, Map<String, Float> datePrice ) {
        this.StockName = StockName
        this.datePrice = datePrice
    }

    void display( ArrayList<String> sDates) {

        String cDate
        Float cValue

        print( this.StockName + ",")
        sDates.each { iDate ->
            cValue = this.datePrice[ iDate ]
            if ( cValue == null ) {
                print("0")
            }
            else {
                print( cValue + ",")
            }
        }


        println()
    }
}

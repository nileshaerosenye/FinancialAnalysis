package EPS

class StockRecords {

    private String sSymbol
    private ArrayList< StockInfo > aStockInfo

    StockRecords () {
        aStockInfo = new ArrayList<StockInfo>()
    }

    void setsSymbol( String sSymbol ) {
        this.sSymbol = sSymbol.replace(".out","")
    }

    int getSize () {
        return aStockInfo.size()
    }

    void AddRecord( String sDate, float fEPS ) {
        aStockInfo.add( new StockInfo(sDate, fEPS) )
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

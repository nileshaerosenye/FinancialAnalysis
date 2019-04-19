package EPS

class StockInfo {

    private String sDate
    private float fEPS
    private float fPrice

    StockInfo () {
        sDate = "NA"
    }

    StockInfo ( String sDate, float fEPS ) {

        this.fEPS = fEPS
        this.sDate = sDate
    }

    StockInfo ( String sDate, float fEPS, float fPrice ) {

        this.fEPS = fEPS
        this.sDate = sDate
        this.fPrice = fPrice
    }

    void StockPrint() {
        print( " | " + sDate + " : " + fEPS + " : " + fPrice )
    }
}

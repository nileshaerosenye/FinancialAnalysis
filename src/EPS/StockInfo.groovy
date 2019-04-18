package EPS

class StockInfo {

    private String sDate
    private float fEPS
    private float fPrice

    StockInfo () {
        sDate = "NA"
    }

    StockInfo ( String sDate, float fEPS ) {

        this.sDate = sDate
        this.fEPS = fEPS

        // extract the month, date, year from date
        String[] sTokens = sDate.split(",")
        int iYear = Integer.parseInt( sTokens[1].trim() )

        switch (sTokens[0].trim()) {
            case "Jan. 31":
            case "January 31":
                this.sDate = iYear + "-01-31"
                break

            case "Feb. 28":
            case "February 28":
                this.sDate = iYear + "-02-28"
                break

            case "Feb. 29":
            case "February 29":
                this.sDate = iYear + "-02-29"
                break

            case "Mar. 31":
            case "March 31":
                this.sDate = iYear + "-03-31"
                break

            case "Apr. 30":
            case "April 30":
                this.sDate = iYear + "-04-30"
                break

            case "May. 31":
            case "May 31":
                this.sDate = iYear + "-05-31"
                break

            case "Jun. 30":
            case "June 30":
                this.sDate = iYear + "-06-30"
                break

            case "July. 31":
            case "July 31":
                this.sDate = iYear + "-07-31"
                break

            case "Aug. 31":
            case "August 31":
                this.sDate = iYear + "-08-31"
                break

            case "Sept. 30":
            case "September 30":
                this.sDate = iYear + "-09-30"
                break

            case "Oct. 31":
            case "October 31":
                this.sDate = iYear + "-10-31"
                break

            case "Nov. 30":
            case "November 30":
                this.sDate = iYear + "-11-30"
                break

            case "Dec. 31":
            case "December 31":
                this.sDate = iYear + "-12-31"
                break
        }

        //println("Adding date as : " + this.sDate)
    }

    void StockPrint() {
        print( " | " + sDate + " : " + fEPS + " : " + fPrice )
    }
}

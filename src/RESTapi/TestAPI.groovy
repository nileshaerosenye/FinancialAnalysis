package RESTapi

import groovy.json.JsonSlurper

println("testing API")

String sym="aapl"
String base="https://api.iextrading.com/1.0/stock/" + sym + "/stats"

HttpURLConnection httpURLConnection = new URL( base ).openConnection()
JsonSlurper jsonSlurper = new JsonSlurper()
def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())

println("Result : " + result)
println(result.marketcap)

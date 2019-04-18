package UTILS

import groovy.json.JsonSlurper

String sBase = "https://api.iextrading.com/1.0/stock/aapl/chart/date/20190411?chartLast=1"
JsonSlurper jsonSlurper = new JsonSlurper()
HttpURLConnection httpURLConnection
httpURLConnection = new URL( sBase ).openConnection()


def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())

println("RES: " + result)
println("RES: " + result.average)


println(" run the API to get last 5 years data ")
sBase="https://api.iextrading.com/1.0/stock/aapl/chart/5y"
httpURLConnection = new URL( sBase ).openConnection()

result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())

println("RES: " + result)
println("RES: " + result.average)

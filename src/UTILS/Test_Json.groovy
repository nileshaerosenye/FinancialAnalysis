package UTILS

import groovy.json.JsonSlurper

String sBase = "https://api.iextrading.com/1.0/stock/aapl/chart/date/20190411?chartLast=1"
JsonSlurper jsonSlurper = new JsonSlurper()
HttpURLConnection httpURLConnection
httpURLConnection = new URL( sBase ).openConnection()


def result = jsonSlurper.parse(httpURLConnection.inputStream.newReader())

println("RES: " + result)
println("RES: " + result.average)

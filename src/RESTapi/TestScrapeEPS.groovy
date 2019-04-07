package RESTapi

import groovy.util.XmlSlurper

println("testing API")

String sym="aapl"
String base="https://ycharts.com/companies/" + sym.toUpperCase() + "/eps"

HttpURLConnection httpURLConnection = new URL( base ).openConnection()
XmlSlurper xmlSlurper = new XmlSlurper()

String result = httpURLConnection.inputStream.newReader().text

def XMLresult = xmlSlurper.parseText (result)

println("Result : " + XMLresult)


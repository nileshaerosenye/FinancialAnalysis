package UTILS

def date = new Date()
String today = date.format("yyyymmdd").toString()
println("Today is : " + today + " | " + date.format("yyyyMMdd"))
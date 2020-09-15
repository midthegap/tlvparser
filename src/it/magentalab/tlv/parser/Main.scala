package it.magentalab.tlv.parser

object Main extends App {
  val tlvHex = "F1090102030405FF070809F20201D0F30100F500F613166980161A3215F3165B31EE5e6f2133a1234C"
  try {
    val data = TLVParser.parse(tlvHex)
    println("Result:")
    data.foreach(println)
  } catch {
    case ex: Exception =>
      println("ERROR: parse failed")
      println(ex.getMessage)
  }
}

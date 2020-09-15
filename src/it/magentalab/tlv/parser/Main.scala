package it.magentalab.tlv.parser

object Main extends App {
  val tlvHex = "F1090102030405FF070809F20201D0F30100"
  val data = TLVParser.parse(tlvHex)

  println("Result:")
  data.foreach(println)
}

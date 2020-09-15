package it.magentalab.tlv.parser

case class TLVData(tag: Byte, length: Byte, value: List[Byte]) {
  override def toString: String = "TAG: %02X, length=%02X, data=%s".format(
    tag, length, value.foldLeft("": String)((s, b) => "%s%02X ".format(s, b))
  )
}



package it.magentalab.tlv.parser

import scala.annotation.tailrec

object TLVParser {

  protected def toHex(s:String): List[Byte] = toHex(s, List[Byte]())

  protected def toHex(s: String, acc: List[Byte]): List[Byte] = {
    if (s.length == 0) acc
    else if (s.length<2) throw new Exception("String is not a valid HEX representation (length is odd)")
    else {
      val b = Integer.parseInt(s.substring(0,2), 16)
      b.toByte :: toHex(s.substring(2), acc)
    }
  }

  protected def extract(bytes: List[Byte]): Option[TLVData] = {
    if (bytes.isEmpty) Option.empty
    else if (bytes.length < 3)
      throw new Exception("Not a valid TLV, lengths are wrong")
    else {
      val tag = bytes.head
      val length = bytes.tail.head
      val data = bytes.tail.tail.take(length)
      Option(TLVData(tag, length, data))
    }
  }

  @tailrec
  private def skip(l: List[Byte], toSkip: Int): List[Byte] = {
    if (toSkip == 0 || l.isEmpty) l
    else skip(l.tail, toSkip - 1)
  }

  protected def split(bytes: List[Byte]): List[TLVData] = {
    val tlv = extract(bytes)
    if (tlv.isDefined) {
      tlv.get :: split(skip(bytes, tlv.get.length + 2))
    } else {
      List[TLVData]()
    }
  }

  /**
   * Parse a TLV string in HEX format.
   *
   * @param tlvHexFormat TLV data in hexadecimal, two digits per byte
   * @return parsed data
   */
  def parse(tlvHexFormat: String): List[TLVData] = {
    val bytes = toHex(tlvHexFormat)
    split(bytes)
  }
}

# tlvparser
Tag-Length-Value format is a binary encoding scheme, see https://en.wikipedia.org/wiki/Type-length-value

This simple parser take in input a string with HEX representation, e.g.: 'F10105F2050102030405'
Result is a list of TLV data:

* TAG: F1, Lenght: 01, Data: 05
* TAG: F2, Length: 05, Data: 0102030405

Run App main to see an example.

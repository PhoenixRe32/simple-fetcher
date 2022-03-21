package com.pittacode.apihelper.json


import com.google.gson.JsonParser
import spock.lang.Specification

class ObjectFlattenerTest extends Specification {

    var underTest = new ObjectFlattener()

    def "flatten json object with one nested object"() {
        given:
        var jsonString = """
{
  "1-1": {
    "2-1": "21"
  }
}
"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 1
        result.keySet().contains("1-1.2-1")
    }

    def "flatten json object with deeply nested object"() {
        given:
        var jsonString = """
{
  "l1": {
    "l2": {
      "l3": {
        "l4": {
          "l5": {
            "l6": {
              "l7": {
                "l8": {
                  "l9": -9999
                }}}}}}}}}
"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 1
        result.keySet().contains("l1.l2.l3.l4.l5.l6.l7.l8.l9")
    }

    def "flatten json object with one nested object and other types"() {
        given:
        var jsonString = """
{
  "1": 11,
  "2": {
    "21": {
      "31": {
        "41": "deeeeeep"
      }
    }
  },
  "3": null,
  "4": "some text",
  "5": ["more text", "some more text"]
}
"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 5
        result.keySet().containsAll("1", "2.21.31.41", "3", "4", "5")
    }

    def "flatten empty object"() {
        given:
        var jsonString = """{}"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().isEmpty()
    }

    def "flatten nested empty object"() {
        given:
        var jsonString = """{"1": "v1", "2":{}}"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 1
        result.keySet().contains("1")
    }

    def "flatten deply nested empty object"() {
        given:
        var jsonString = """{"1": "v1", "2":{ "3" : { "4": {}}}, "topLevel":0}"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 2
        result.keySet().containsAll("1", "topLevel")
    }

    def "flatten deeeeeeeeeeeply nested empty object"() {
        given:
        var jsonString = """{"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"value":"value"}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}"""
        var jsonObject = JsonParser.parseString(jsonString).asJsonObject

        when:
        var result = underTest.flatten(jsonObject)
        println(result.toString())

        then:
        result.keySet().size() == 1
    }
}

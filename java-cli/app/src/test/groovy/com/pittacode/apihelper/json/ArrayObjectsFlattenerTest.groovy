package com.pittacode.apihelper.json

import com.google.gson.JsonParser
import spock.lang.Specification

class ArrayObjectsFlattenerTest extends Specification {

    var underTest = new ArrayObjectsFlattener(new ObjectFlattener())

    def "flatten objects in an array"() {
        given:
        var jsonString = """
[
    {
      "k11": {
        "k21": "some-text",
        "k22": 1985,
        "k23": null
      },
      "k12": {
        "k21": "other-text",
        "k22": 711,
        "k23": null
      }
    },
    {
      "k11": {
        "k21": "some-more-text",
        "k22": 7111985
      },
      "k12": {
        "k21": "some-other-text",
        "k25": true
      }
    }
]
"""
        var jsonArray = JsonParser.parseString(jsonString).asJsonArray
        assert jsonArray.get(0).asJsonObject.keySet().size() == 2
        assert jsonArray.get(1).asJsonObject.keySet().size() == 2

        when:
        var result = underTest.flattenObjectsIn(jsonArray)
        println(result.toString())

        then:
        var firstObjectKeys = result.get(0).asJsonObject.keySet()
        firstObjectKeys.size() == 6
        firstObjectKeys.containsAll("k11.k21", "k11.k22", "k11.k23", "k12.k21", "k12.k22", "k12.k23")
        var secondObjectKeys = result.get(1).asJsonObject.keySet()
        secondObjectKeys.size() == 4
        secondObjectKeys.containsAll("k11.k21", "k11.k22", "k12.k21", "k12.k25")
    }

    def "flatten deeeeep nested objects in an array"() {
        given:
        var jsonString = """
[
    {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"value":1}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}},
    {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"value":2}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}},
    {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": { "l1": {"l2": {"l3": {"l4": {"l5": {"l6": {"l7": {"l8": {"l9": {"l10": {"l11": {"l12": {"l13": {"l14": {"l15": {"l16": {"l17": {"l18": {"l19": {"l20": {"l21": {"l22": {"l23": {"l24": {"l25": {"l26": {"l27": {"l28": {"l29": {"l30": {"l31": {"l32": {"l33": {"l34": {"l35": {"l36": {"l37": {"l38": {"l39": {"l40": {"value":3}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
]
"""
        var jsonArray = JsonParser.parseString(jsonString).asJsonArray

        when:
        var result = underTest.flattenObjectsIn(jsonArray)
        println(result.toString())

        then:
        var firstObjectEntries = result.get(0).asJsonObject.entrySet()
        firstObjectEntries.size() == 1
        firstObjectEntries.getAt(0).value.asInt == 1
        var secondObjectEntries = result.get(1).asJsonObject.entrySet()
        secondObjectEntries.size() == 1
        secondObjectEntries.getAt(0).value.asInt == 2
        var thirdObjectEntries = result.get(2).asJsonObject.entrySet()
        thirdObjectEntries.size() == 1
        thirdObjectEntries.getAt(0).value.asInt == 3
    }
}

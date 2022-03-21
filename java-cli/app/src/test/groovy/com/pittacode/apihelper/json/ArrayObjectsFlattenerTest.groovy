package com.pittacode.apihelper.json

import com.google.gson.JsonParser
import spock.lang.Specification

class ArrayObjectsFlattenerTest extends Specification {

    var underTest = new ArrayObjectsFlattener(new ObjectFlattener())

    def "flatten objects into array"() {
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
        var jsonArray = JsonParser.parseString(jsonString).getAsJsonArray()
        assert jsonArray.get(0).getAsJsonObject().keySet().size() == 2
        assert jsonArray.get(1).getAsJsonObject().keySet().size() == 2

        when:
        var result = underTest.flattenObjectsIn(jsonArray)
        println(result.toString())

        then:
        var firstObjectKeys = result.get(0).getAsJsonObject().keySet()
        firstObjectKeys.size() == 6
        firstObjectKeys.containsAll("k11.k21", "k11.k22", "k11.k23", "k12.k21", "k12.k22", "k12.k23")
        var secondObjectKeys = result.get(1).getAsJsonObject().keySet()
        secondObjectKeys.size() == 4
        secondObjectKeys.containsAll("k11.k21", "k11.k22", "k12.k21", "k12.k25")
    }
}

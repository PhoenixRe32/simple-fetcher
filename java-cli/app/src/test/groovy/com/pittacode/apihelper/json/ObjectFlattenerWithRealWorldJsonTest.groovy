package com.pittacode.apihelper.json

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Files
import java.nio.file.Path

class ObjectFlattenerWithRealWorldJsonTest extends Specification {

    def underTest = new ObjectFlattener()

    @Unroll
    def "#filename can be flattened"() {
        given:
        def jsonObject = JsonParser.parseReader(new FileReader(filename)).getAsJsonObject()
        assert countNestedObjects(jsonObject) != 0

        when:
        def result = underTest.flatten(jsonObject)

        then:
        countNestedObjects(result) == 0

        where:
        filename << Files.find(
                Path.of(getClass().getClassLoader().getResource("realWorldJsonObjects").toURI()),
                1,
                (f, a) -> f.toString().endsWith("json"))
                .map(p -> p.toAbsolutePath())
                .map(p -> p.toString())
                .toList()
    }

    def "json object with no nested objects stays the same"() {
        given:
        def file = new File(getClass().getClassLoader().getResource("no-nested-objects.json").toURI())
        def jsonObject = JsonParser.parseReader(new FileReader(file)).getAsJsonObject()
        assert countNestedObjects(jsonObject) == 0

        when:
        def result = underTest.flatten(jsonObject)

        then:
        result == jsonObject
    }

    private Number countNestedObjects(JsonObject jsonObject) {
        jsonObject.entrySet().count { entry -> entry.value.isJsonObject() }
    }
}

package com.pittacode.apihelper

import com.pittacode.apihelper.domain.Method
import picocli.CommandLine
import spock.lang.Specification
import spock.lang.Unroll

class ApiHelperArgumentsTest extends Specification {

    @Unroll
    def "recognises method regardless of case: #methodArg"() {
        given:
        var args = new String[]{"-u", "https://url.to.api", "-m", methodArg}
        var underTest = new ApiHelper(null, null)

        when:
        new CommandLine(underTest).parseArgs(args)

        then:
        underTest.method == expectedMethod

        where:
        methodArg | expectedMethod
        "get"     | Method.GET
        "Get"     | Method.GET
        "post"    | Method.POST
        "Post"    | Method.POST
        "head"    | Method.HEAD
        "Head"    | Method.HEAD
    }

    def "defaults to get method if not specified"() {
        given:
        var args = new String[]{"-u", "https://url.to.api"}
        var underTest = new ApiHelper(null, null)

        when:
        new CommandLine(underTest).parseArgs(args)

        then:
        underTest.method == Method.GET
    }

    def "throws"() {
        given:
        var args = new String[]{"-u", "https://url.to.api", "--method", "unsupportedMethod"}
        var underTest = new ApiHelper(null, null)

        when:
        new CommandLine(underTest).parseArgs(args)

        then:
        thrown(CommandLine.ParameterException.class)
    }
}

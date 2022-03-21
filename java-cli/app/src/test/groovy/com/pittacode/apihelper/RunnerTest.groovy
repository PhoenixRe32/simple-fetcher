package com.pittacode.apihelper

import spock.lang.Ignore
import spock.lang.Specification

@Ignore
class RunnerTest extends Specification {

    def "golden girls"() {
        setup:
        def method = "GET"
        def url = "https://api.tvmaze.com/search/shows?q=golden%20girls"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "girls"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation, params)

        then:
        params != null
    }

    def "beers"() {
        setup:
        def method = "GET"
        def url = "https://api.punkapi.com/v2/beers"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "beers"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation, params)

        then:
        params != null
    }

    def "brands"() {
        setup:
        def method = "GET"
        def url = "https://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "brands"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation)

        then:
        params != null
    }

    def "public holidays"() {
        setup:
        def method = "GET"
        def url = "https://date.nager.at/api/v2/publicholidays/2022/CY"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "pub"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation, "")

        then:
        params != null
    }

    def "exch rate"() {
        setup:
        def method = "GET"
        def url = "https://v6.exchangerate-api.com/v6/53854bc86b5a463adc5bddbb/latest/USD"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "rate"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation)

        then:
        params != null
    }

    def "recalls"() {
        setup:
        def method = "GET"
        def url = "https://api.fda.gov/food/enforcement.json?limit=10"
        def apiKey = "IPqqDGraIERGyIpuH34h5YLZhc2o3ErpeChSNfco"
        def saveLocation = "food"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation)

        then:
        params != null
    }

    def "assets"() {
        setup:
        def method = "GET"
        def url = "https://api.opensea.io/api/v1/assets?format=json"
        def apiKey = "53854bc86b5a463adc5bddbb"
        def saveLocation = "assets"
        def params = ""

        when:
        Runner.main(method, url, apiKey, saveLocation)

        then:
        params != null
    }
}

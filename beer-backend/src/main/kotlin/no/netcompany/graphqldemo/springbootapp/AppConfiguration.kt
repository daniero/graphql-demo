package no.netcompany.graphqldemo.springbootapp

import no.netcompany.graphqldemo.businesslogic.BeerService
import no.netcompany.graphqldemo.businesslogic.BreweryService
import no.netcompany.graphqldemo.businesslogic.readBeersFromCsvFile
import no.netcompany.graphqldemo.businesslogic.readBreweriesFromCsvFile
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
open class AppConfiguration(
    @Value("classpath:data/beers.csv") private val beerResource: Resource,
    @Value("classpath:data/breweries.csv") private val breweryResource: Resource
) {

    @Bean
    open fun brewerySerice(): BreweryService {
        val csvFile = breweryResource.file
        val breweries = readBreweriesFromCsvFile(csvFile)
        return BreweryService(breweries)
    }

    @Bean
    open fun beerService(): BeerService {
        val csvFile = beerResource.file
        val beers = readBeersFromCsvFile(csvFile)
        return BeerService(beers)
    }

}

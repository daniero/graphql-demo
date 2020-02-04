package no.netcompany.graphqldemo.graphql

import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import no.netcompany.graphqldemo.businesslogic.Beer
import no.netcompany.graphqldemo.businesslogic.BeerService
import no.netcompany.graphqldemo.businesslogic.Brewery
import no.netcompany.graphqldemo.businesslogic.BreweryService

class RuntimeWiringBuilder(
    private val beerService: BeerService,
    private val breweryService: BreweryService
) {
    fun buildWiring(): RuntimeWiring = newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("beer") { environment ->
                beerService.getBeerById(environment.getArgument("id"))
            }
            .dataFetcher("allBeers") {
                beerService.getAllBeers()
            }
            .dataFetcher("brewery") { environment ->
                breweryService.getBreweryById(environment.getArgument("id"))
            }
            .dataFetcher("allBreweries") {
                breweryService.getAllBreweries()
            }

        )
        .type(newTypeWiring("Beer")
            .dataFetcher("brewery") { environment ->
                val beer = environment.getSource<Beer>()
                breweryService.getBreweryById(beer.breweryId)
            }
        )
        .type(newTypeWiring("Brewery")
            .dataFetcher("beers") { environment ->
                val brewery = environment.getSource<Brewery>()
                beerService.getBeersByBreweryIds(listOf(brewery.id))[brewery.id]
            }
        )
        .build()
}

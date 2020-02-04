package no.netcompany.graphqldemo.springbootapp

import graphql.GraphQL
import no.netcompany.graphqldemo.businesslogic.BeerService
import no.netcompany.graphqldemo.businesslogic.BreweryService
import no.netcompany.graphqldemo.graphql.GraphQLBuilder
import no.netcompany.graphqldemo.graphql.RuntimeWiringBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
open class GraphQLProvider(
    private val runtimeWiringBuilder: RuntimeWiringBuilder,

    @Value("classpath:schema.graphqls")
    private val schemaResource: Resource
) {
    @Bean
    open fun graphQL(): GraphQL {
        val schema = schemaResource.file.readText()
        return GraphQLBuilder(runtimeWiringBuilder).build(schema)
    }

}

@Configuration
open class RuntimeWiringProvider(
    private val beerService: BeerService,
    private val breweryService: BreweryService
) {
    @Bean
    open fun runtimeWiringBuilder() = RuntimeWiringBuilder(beerService, breweryService)
}

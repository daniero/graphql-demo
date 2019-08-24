package no.netcompany.graphqldemo.graphql

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class GraphQLProvider(
    @Value("classpath:schema.graphqls")
    private val schemaResource: Resource,
    private val runtimeWiringBuilder: RuntimeWiringBuilder
) {
    private lateinit var graphQL: GraphQL

    @Bean
    fun graphQL() = graphQL

    @PostConstruct
    fun init() {
        val schema = schemaResource.file.readText(Charsets.UTF_8)
        val graphQLSchema = buildSchema(schema)

        this.graphQL = GraphQL
            .newGraphQL(graphQLSchema)
            .build()
    }

    private fun buildSchema(sdl: String): GraphQLSchema {
        val typeRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = runtimeWiringBuilder.buildWiring()

        return SchemaGenerator()
            .makeExecutableSchema(typeRegistry, runtimeWiring)
    }
}
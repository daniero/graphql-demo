package no.netcompany.graphqldemo.graphql

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser


class GraphQLBuilder(
    private val runtimeWiringBuilder: RuntimeWiringBuilder
) {

    fun build(schema: String): GraphQL {
        val graphQLSchema = buildSchema(schema)

        return GraphQL
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
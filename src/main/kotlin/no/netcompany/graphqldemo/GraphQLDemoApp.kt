package no.netcompany.graphqldemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GraphQLDemoApp

fun main(args: Array<String>) {
    runApplication<GraphQLDemoApp>(*args)
}

package no.netcompany.graphqldemo.businesslogic

import java.io.File

data class Brewery(
    val id: String,
    val name: String,
    val city: String,
    val state: String
)

class BreweryService(
    private val breweries: Map<String, Brewery>
) {

    fun getBreweryById(id: String): Brewery? {
        return breweries[id]
    }

    fun getAllBreweries(): Collection<Brewery> {
        return breweries.values.toSet()
    }

}

fun readBreweriesFromCsvFile(csv: File): Map<String, Brewery> {
    return csv
        .readLines()
        .drop(1)
        .map { line ->
            val fields = line.split(",")
            Brewery(
                id = fields[4],
                name = fields[1],
                city = fields[2],
                state = fields[3]
            )
        }
        .associateBy(Brewery::id)
}
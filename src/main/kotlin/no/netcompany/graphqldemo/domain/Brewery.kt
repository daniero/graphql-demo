package no.netcompany.graphqldemo.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

data class Brewery(
    val id: String,
    val name: String,
    val city: String,
    val state: String
)

@Service
class BreweryService(
    @Value("classpath:data/breweries.csv")
    private val resource: Resource
) {
    private lateinit var breweries: MutableMap<String, Brewery>

    fun getBreweryById(id: String): Brewery? {
        return breweries[id]
    }

    fun getAllBreweries(): Collection<Brewery> {
        return breweries.values.toSet()
    }

    @PostConstruct
    fun initializeBreweries() {
        breweries =
            resource
                .file
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
                .toMutableMap()
    }

}
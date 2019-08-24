package no.netcompany.graphqldemo.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.annotation.PostConstruct

data class Beer(
    val id: String,
    val breweryId: String,
    val name: String,
    val abv: BigDecimal?,
    val ibu: BigDecimal?,
    val style: String,
    val ounces: BigDecimal?
)

@Service
class BeerService(
    @Value("classpath:data/beers.csv")
    private val resource: Resource
) {
    private lateinit var beers: MutableMap<String, Beer>

    fun getAllBeers(): MutableCollection<Beer> {
        return beers.values
    }

    fun getBeerById(id: String): Beer? {
        return beers[id]
    }

    fun getBeersByBreweryIds(breweryIds: List<String>): Map<String, List<Beer>> {
        val breweryIdSet = breweryIds.toHashSet()
        return beers
            .values
            .filter { beer -> breweryIdSet.contains(beer.breweryId) }
            .groupBy { beer -> beer.breweryId }
    }

    fun addBeer(beer: Beer): Beer {
        beers[beer.id] = beer
        return beer
    }

    @PostConstruct
    fun initializeBeers() {
        beers =
            resource
                .file
                .readLines()
                .drop(1)
                .map { line ->
                    val fields = line.split(",")
                    Beer(
                        id = fields[3],
                        breweryId = fields[6],
                        name = fields[4],
                        abv = fields[1].toBigDecimalOrNull(),
                        ibu = fields[2].toBigDecimalOrNull(),
                        style = fields[5],
                        ounces = fields[7].toBigDecimalOrNull()
                    )
                }
                .associateBy(Beer::id)
                .toMutableMap()
    }

}

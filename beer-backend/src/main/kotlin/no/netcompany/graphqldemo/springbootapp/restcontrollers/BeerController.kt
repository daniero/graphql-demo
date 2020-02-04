package no.netcompany.graphqldemo.springbootapp.restcontrollers

import no.netcompany.graphqldemo.businesslogic.Beer
import no.netcompany.graphqldemo.businesslogic.BeerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BeerController(
    private val beerService: BeerService
) {

    @GetMapping("/beers")
    fun getBeers(): Collection<Beer> {
        return beerService.getAllBeers()
    }

    @PostMapping("/beer")
    fun addBeer(@RequestBody input: BeerInput): ResponseEntity<Beer> {
        val newBeer = beerService.addBeer(
            Beer(input.name, input.brewery, "", null, null, "", null)
        )

        return ResponseEntity(newBeer, HttpStatus.CREATED)
    }

    @PutMapping("/beer/{beerId}")
    fun editBeer(
        @PathVariable("beerId") beerId: String,
        @RequestBody input: BeerInput
    ): ResponseEntity<Beer> {
        val beer = beerService.getBeerById(beerId)

        if (beer == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val newBeer = beerService.addBeer(
            Beer(
                input.name,
                input.brewery,
                beer.id,
                null, null, "", null
            )
        )

        return ResponseEntity(newBeer, HttpStatus.CREATED)
    }

}

data class BeerInput(
    val name: String,
    val brewery: String
)



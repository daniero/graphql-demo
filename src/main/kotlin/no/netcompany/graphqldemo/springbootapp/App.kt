package no.netcompany.graphqldemo.springbootapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
open class App {

    @Bean
    open fun corsConfigurer(): WebMvcConfigurer =
        object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("/graphql")
                    .allowedOrigins("http://localhost:3000")
            }
        }

}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

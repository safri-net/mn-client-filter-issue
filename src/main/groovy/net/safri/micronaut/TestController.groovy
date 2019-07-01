package net.safri.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient


@Controller("/test")
class TestController {

    final ApplicationContext ctx

    TestController(ApplicationContext ctx) {
        this.ctx = ctx
    }

    @Get("/")
    HttpStatus index() {
        def c = ctx.createBean(RxHttpClient, "https://www.google.com")

        def r = c.exchange(HttpRequest.GET("/search?q=micronaut"), String)

        println r.blockingFirst().body()


        return HttpStatus.OK
    }
}
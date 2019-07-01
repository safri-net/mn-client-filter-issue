package net.safri.micronaut


import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher

@Filter(serviceId = "test")
class TestFilter implements HttpClientFilter {

    @Override
    Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {

        if (request.uri.toString().startsWith("https://www.google.com")) {
            println("Should not happen: for uri $request.uri")
        } else {
            println("Should happen: for uri $request.uri")
        }

        return chain.proceed(request)
    }
}

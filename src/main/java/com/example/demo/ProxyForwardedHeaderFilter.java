package com.example.demo;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

public class ProxyForwardedHeaderFilter implements WebFilter {

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
        final URI uri = UriComponentsBuilder.fromHttpRequest(exchange.getRequest())
                                            .scheme("https")
                                            .build()
                                            .toUri();

        final ServerWebExchange withChangedUri = exchange.mutate().request(builder -> builder.uri(uri)).build();
        return chain.filter(withChangedUri);
    }
}

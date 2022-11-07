package com.example.kakao.router;

import com.example.kakao.handler.PersonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Configuration
public class PersonRouter {

    private final PersonHandler handler;

    @Bean
    public RouterFunction<?> personRouter() {
        String bar = "asdf";
        return route()
                .GET("/person/{id}", accept(APPLICATION_JSON), handler::get)
                .GET("/person", accept(APPLICATION_JSON), handler::list)
                .POST("/person", handler::create)
                .build();
    }
}

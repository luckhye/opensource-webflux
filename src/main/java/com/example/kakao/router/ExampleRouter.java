package com.example.kakao.router;

import com.example.kakao.handler.Examplehandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class ExampleRouter {

    @Bean
    public RouterFunction<ServerResponse> routeExample(Examplehandler exampleHandler) {
        return route(RequestPredicates.GET("/example").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::hello);
    }

}

package com.dskushnir.testExercise.router;

import com.dskushnir.testExercise.handler.NodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class NodeRouter {
    @Bean

    public RouterFunction<ServerResponse> route(NodeHandler nodeHandler) {
        return RouterFunctions
                .route(GET("/nodes").and(accept(MediaType.APPLICATION_JSON)),
                        nodeHandler::findAllNodes)
                .andRoute(POST("/node").and(accept(MediaType.APPLICATION_JSON)),
                        nodeHandler::saveNode);
    }
}

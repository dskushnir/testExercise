package com.dskushnir.testExercise.handler;

import com.dskushnir.testExercise.documentDTO.NodeDTO;
import com.dskushnir.testExercise.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class NodeHandler {
    private final NodeService nodeService;

    public Mono<ServerResponse> saveNode(ServerRequest request) {
        var nodeMono = nodeService.convertToNode(request.bodyToMono(NodeDTO.class)).flatMap(nodeService::save);
        var nodeDTOMonoResponse = Mono.from(nodeMono.map(d -> new NodeDTO(d.getId(), d.getNodeRoot(), d.getNodeDesc())));
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(nodeDTOMonoResponse, NodeDTO.class);
    }

    public Mono<ServerResponse> findAllNodes(ServerRequest request) {
        var nodeDTOFluxList = nodeService.convertToDTO(nodeService.findAll());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(nodeDTOFluxList, NodeDTO.class);
    }
}




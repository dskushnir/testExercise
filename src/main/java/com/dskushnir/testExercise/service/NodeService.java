package com.dskushnir.testExercise.service;

import com.dskushnir.testExercise.document.Node;
import com.dskushnir.testExercise.documentDTO.NodeDTO;
import com.dskushnir.testExercise.repository.NodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
@AllArgsConstructor
public class NodeService {
    private final NodeRepository nodeRepository;

    public Mono<Node> save(Node node) {
        return nodeRepository.save(node);

    }

    public Flux<Node> findAll() {
        return nodeRepository.findAll();

    }

    public Mono<Void> deleteAll() {
        return nodeRepository.deleteAll();
    }

    public Mono<Node> convertToNode(Mono<NodeDTO> nodeDTO) {
        return nodeDTO.map(n -> new Node(n.getId(), n.getNodeRoot(), n.getNodeDesc()));
    }

    public Flux<NodeDTO> convertToDTO(Flux<Node> nodeFlux) {
        return nodeFlux.map(d -> new NodeDTO(d.getId(), d.getNodeRoot(), d.getNodeDesc()));
    }

}



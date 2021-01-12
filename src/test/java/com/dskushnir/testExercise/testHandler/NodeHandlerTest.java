package com.dskushnir.testExercise.testHandler;

import com.dskushnir.testExercise.document.Node;
import com.dskushnir.testExercise.service.NodeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NodeHandlerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private NodeService nodeService;


    private List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        list.add(new Node("12345", "Node1", "Description1"));
        list.add(new Node("678910", "Node2", null));

        return list;
    }

    @BeforeEach
    public void setUp() {
        nodeService.deleteAll()
                .thenMany(Flux.fromIterable(getNodes()))
                .flatMap(node -> nodeService.save(node))
                .doOnEach(System.out::println)
                .then()
                .block();
    }

    @Test
    public void shouldFindAllNodesTest() {
        webTestClient.get().uri("/nodes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Node.class)
                .consumeWith(System.out::println);
    }

    @Test
    public void shouldSaveNodeTest() {
        Node node = new Node("54321", "Node4", "Description4");
        webTestClient.post().uri("/node")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(node), Node.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nodeRoot").isEqualTo("Node4")
                .jsonPath("$.nodeDesc").isEqualTo("Description4")
                .consumeWith(System.out::println);
    }
}



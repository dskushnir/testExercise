package com.dskushnir.testExercise.repository;

import com.dskushnir.testExercise.document.Node;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends ReactiveMongoRepository<Node, String> {
}

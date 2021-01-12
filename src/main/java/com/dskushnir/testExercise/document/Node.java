package com.dskushnir.testExercise.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "node")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Node {
    @Id
    private String id;
    private String nodeRoot;
    private String nodeDesc;
}

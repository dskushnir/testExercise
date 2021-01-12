package com.dskushnir.testExercise.documentDTO;
import lombok.Data;
import java.util.Optional;


@Data

public class NodeDTO {
    private String id;
    private String nodeRoot;
    private String nodeDesc;

    public Optional<String> getNodeDesc() {
        return Optional.ofNullable(nodeDesc);}

}

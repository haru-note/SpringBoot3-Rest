package net.harunote.spring3rest.hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Hello model")
@Setter
@Getter
public class Hello {
    @JsonProperty(value="id", required=true, index = 1, defaultValue = "1")
    @Schema(description = "Unique identifier of the Hello.", example = "1", required = true)
    private final long id;
    @JsonProperty(value="content", required=true, index = 2, defaultValue = "World")
    @Schema(description = "content of the hello application.", example = "world!!!", required = true)
    private final String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

}

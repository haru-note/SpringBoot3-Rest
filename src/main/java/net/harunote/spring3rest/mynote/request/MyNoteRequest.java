package net.harunote.spring3rest.mynote.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author CodeVillains
 */
@Setter
@Getter
@ToString
public class MyNoteRequest {
    @Schema(description = "제목", example = "마이노트 제목입니다.", required = true)
    @NotBlank
    private String title;

    @Schema(description = "내용", example = "마이노트 내용입니다.", required = true)
    @NotBlank
    private String content;

    @Schema(description = "완료 여부", example = "true")
    private boolean done;
    @Schema(description = "태그 리스트", example = "[ {\"deleted\" : \"false\", \"tagName\" : \"Kubernetes\"}, {\"deleted\" : \"false\", \"tagName\" : \"Docker\"} ]")
    private List<TagRequest> tag;
}

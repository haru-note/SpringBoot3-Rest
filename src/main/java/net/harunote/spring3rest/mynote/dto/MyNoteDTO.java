package net.harunote.spring3rest.mynote.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author CodeVillains
 */
@Setter
@Getter
@NoArgsConstructor
public class MyNoteDTO {
    public MyNoteDTO(Long myNoteId, String title, String content, boolean done, Date createdAt) {
        this.myNoteId = myNoteId;
        this.title = title;
        this.content = content;
        this.done = done;
        this.createdAt = createdAt;
    }

    private Long myNoteId;
    private String title;
    private String content;
    private boolean done;
    private Date createdAt;
    private List<TagDTO> tags;
}

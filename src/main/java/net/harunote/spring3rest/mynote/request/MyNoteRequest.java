package net.harunote.spring3rest.mynote.request;

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
    private String title;

    private String content;

    private boolean done;

    private List<TagRequest> tag;
}

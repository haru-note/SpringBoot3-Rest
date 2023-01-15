package net.harunote.spring3rest.hello.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hello {
    private final long id;
    private final String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

}

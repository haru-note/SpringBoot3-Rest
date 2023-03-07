package net.harunote.spring3rest.mynote.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author CodeVillains
 */

@Entity
@Table(name = "tag")
@NoArgsConstructor
@Setter
@Getter
public class TagEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "my_tag_name")
    private String myTagName;

    @Column(name = "my_note_id")
    private Long myNoteId;

    @Column(name = "deleted")
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity that = (TagEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

package net.harunote.spring3rest.mynote.repository;

import net.harunote.spring3rest.mynote.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CodeVillains
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}

package net.harunote.spring3rest.mynote.service;

import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.exception.MyNoteNotFoundException;
import net.harunote.spring3rest.mynote.dto.MyNoteDTO;
import net.harunote.spring3rest.mynote.dto.TagDTO;
import net.harunote.spring3rest.mynote.entity.MyNoteEntity;
import net.harunote.spring3rest.mynote.entity.TagEntity;
import net.harunote.spring3rest.mynote.repository.MyNoteRepository;
import net.harunote.spring3rest.mynote.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MyNoteService {

    private final MyNoteRepository myNoteRepository;
    private final TagRepository tagRepository;

    public MyNoteService(MyNoteRepository myNoteRepository, TagRepository tagRepository) {
        this.myNoteRepository = myNoteRepository;
        this.tagRepository = tagRepository;
    }

    public List<MyNoteEntity> findAll() {
        return myNoteRepository.findAll();
    }

    public MyNoteEntity save(MyNoteEntity myNoteEntity) {
        myNoteEntity.setCreatedAt(new Date());
        return myNoteRepository.save(myNoteEntity);
    }

    public MyNoteDTO findById(Long id) {
        MyNoteEntity entity = myNoteRepository.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
        MyNoteDTO myNoteDTO;
        TagDTO tagDTO;
        List<TagDTO> tagList = new ArrayList<>();

        myNoteDTO = new MyNoteDTO(entity.getId(), entity.getTitle(), entity.getContent(), entity.isDone(), entity.getCreatedAt());

        List<TagEntity> tags = entity.getTag();

        if (tags != null) {
            for (TagEntity tag : tags) {
                tagDTO = new TagDTO(tag.getId(), tag.getMyTagName(), tag.isDeleted());
                tagList.add(tagDTO);
            }
            myNoteDTO.setTags(tagList);
        }
        return myNoteDTO;
    }

    public MyNoteEntity update(Long id, MyNoteEntity requestNote) {
        Optional<MyNoteEntity> myNoteEntity = myNoteRepository.findById(id);

        if (myNoteEntity.isPresent()) {
            myNoteEntity.get().setTitle(requestNote.getTitle());
            myNoteEntity.get().setContent(requestNote.getContent());
            myNoteEntity.get().setDone(requestNote.isDone());

            return myNoteRepository.save(myNoteEntity.get());
        } else {
            log.info("My Note가 존재하지 않음 id = {}", id);
            return new MyNoteEntity();
        }
    }

    public void delete(Long id) {
        myNoteRepository.deleteById(id);
    }

    public List<TagEntity> findMyTagAll() {
        return tagRepository.findAll();
    }

    public MyNoteDTO findByTag() {
        Optional<TagEntity> tag = tagRepository.findById(1L);
        MyNoteDTO myNote = null;
        log.info("# tag.isPresent() = {}", tag.isPresent());
        if (tag.isPresent()) {
            MyNoteEntity note = tag.get().getMyNote();
            myNote = new MyNoteDTO(note.getId(), note.getTitle(), note.getContent(), note.isDone(), note.getCreatedAt());
        }
        return myNote;
    }
}

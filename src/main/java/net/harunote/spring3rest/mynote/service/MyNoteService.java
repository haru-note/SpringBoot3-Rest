package net.harunote.spring3rest.mynote.service;

import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.exception.MyNoteNotFoundException;
import net.harunote.spring3rest.mynote.dto.MyNoteDTO;
import net.harunote.spring3rest.mynote.dto.TagDTO;
import net.harunote.spring3rest.mynote.entity.MyNoteEntity;
import net.harunote.spring3rest.mynote.entity.TagEntity;
import net.harunote.spring3rest.mynote.repository.MyNoteRepository;
import net.harunote.spring3rest.mynote.repository.TagRepository;
import net.harunote.spring3rest.mynote.request.MyNoteRequest;
import net.harunote.spring3rest.mynote.request.TagRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public MyNoteRequest save(MyNoteRequest noteRequest) {
        MyNoteEntity entity = toEntity(noteRequest);

        if (!noteRequest.getTag().isEmpty()) {
            List<TagEntity> tagEntities = new ArrayList<>();
            for (TagRequest t : noteRequest.getTag()) {
                TagEntity tag = new TagEntity();
                tag.setMyTagName(t.getTagName());
                tag.setMyNote(entity);
                tagEntities.add(tag);
                entity.setTag(tagEntities);
            }
        }
        myNoteRepository.save(entity);
        return noteRequest;
    }

    private MyNoteEntity toEntity(MyNoteRequest request) {
        MyNoteEntity entity = new MyNoteEntity();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setCreatedAt(new Date());
        entity.setDone(request.isDone());

        return entity;
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

    public void update(Long id, MyNoteRequest myNoteRequest) {
        MyNoteEntity entity = myNoteRepository.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
        entity.setTitle(myNoteRequest.getTitle());
        entity.setContent(myNoteRequest.getContent());
        entity.setDone(myNoteRequest.isDone());

        for (TagRequest tagRequest : myNoteRequest.getTag()) {
            Optional<TagEntity> tagOptional = entity.getTag().stream()
                    .filter(t -> t.getId().equals(tagRequest.getId()))
                    .findFirst();

            if (tagOptional.isPresent()) {
                TagEntity tag = tagOptional.get();
                tag.setMyTagName(tagRequest.getTagName());
                tag.setDeleted(tagRequest.isDeleted());
            } else {
                TagEntity tag = new TagEntity();
                tag.setMyTagName(tagRequest.getTagName());
                tag.setMyNote(entity);
                entity.getTag().add(tag);
            }
        }
        myNoteRepository.save(entity);
    }

    /**
    private List<TagEntity> addTag(MyNoteEntity entity, List<String> tags) {
        List<TagEntity> entities = new ArrayList<>();
        if (!tags.isEmpty()) {
            for (String tag : tags) {
                TagEntity tagEntity= new TagEntity();
                tagEntity.setMyNote(entity);
                tagEntity.setMyTagName(tag);
                tagEntity.setDeleted(false);
            }
        }
        return null;
    }
     */

    public void delete(Long id) {
        myNoteRepository.deleteById(id);
    }

    public List<TagEntity> findMyTagAll() {
        return tagRepository.findAll();
    }

    public MyNoteDTO findByTag() {
        Optional<TagEntity> tag = tagRepository.findById(1L);
        MyNoteDTO myNote = null;
        if (tag.isPresent()) {
            MyNoteEntity note = tag.get().getMyNote();
            myNote = new MyNoteDTO(note.getId(), note.getTitle(), note.getContent(), note.isDone(), note.getCreatedAt());
        }
        return myNote;
    }
}

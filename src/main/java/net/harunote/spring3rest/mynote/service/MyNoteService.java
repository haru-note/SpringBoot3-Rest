package net.harunote.spring3rest.mynote.service;

import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.mynote.entity.MyNoteEntity;
import net.harunote.spring3rest.mynote.exception.MyNoteNotFoundException;
import net.harunote.spring3rest.mynote.repository.MyNoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MyNoteService {

    private final MyNoteRepository myNoteRepository;

    public MyNoteService(MyNoteRepository myNoteRepository) {
        this.myNoteRepository = myNoteRepository;
    }

    public List<MyNoteEntity> findAll() {
        return myNoteRepository.findAll();
    }

    public MyNoteEntity save(MyNoteEntity myNoteEntity) {
        myNoteEntity.setCreatedAt(new Date());
        return myNoteRepository.save(myNoteEntity);
    }

    public Optional<MyNoteEntity> findById(Long id) {
        return myNoteRepository.findById(id);
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
}

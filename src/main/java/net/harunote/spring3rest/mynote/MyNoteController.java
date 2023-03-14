package net.harunote.spring3rest.mynote;

import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.mynote.dto.MyNoteDTO;
import net.harunote.spring3rest.mynote.entity.MyNoteEntity;
import net.harunote.spring3rest.mynote.entity.TagEntity;
import net.harunote.spring3rest.mynote.request.MyNoteRequest;
import net.harunote.spring3rest.mynote.service.MyNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


/**
 * @author CodeVillains
 */
@Slf4j
@RestController
public class MyNoteController {

    private MyNoteService myNoteService;

    public MyNoteController(MyNoteService myNoteService) {
        this.myNoteService = myNoteService;
    }

    @GetMapping("/mynotes")
    List<MyNoteEntity> getAll() {
        log.info("# mynote getAll()");
        return myNoteService.findAll();
    }

    @PostMapping("/mynote")
    ResponseEntity<MyNoteRequest> addNoteAndTag(@RequestBody MyNoteRequest noteRequest) {
        MyNoteRequest save = myNoteService.save(noteRequest);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/mynote/{id}")
    ResponseEntity<Void> updateNote(@PathVariable Long id, @RequestBody MyNoteRequest noteRequest) {
        myNoteService.update(id, noteRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/mynote/{id}")
    void deleteNote(@PathVariable Long id) {
        myNoteService.delete(id);
    }


    @GetMapping("/mynote/{id}")
    MyNoteDTO getNote(@PathVariable Long id) {
        return myNoteService.findById(id);
    }

    @GetMapping("/mynote/tags")
    List<TagEntity> getTags() {
        return myNoteService.findMyTagAll();
    }
    @GetMapping("/mynote/tag")
    MyNoteDTO getTag() {
        return myNoteService.findByTag();
    }



}

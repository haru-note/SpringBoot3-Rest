package net.harunote.spring3rest.mynote;

import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.mynote.entity.MyNoteEntity;
import net.harunote.spring3rest.mynote.exception.MyNoteNotFoundException;
import net.harunote.spring3rest.mynote.service.MyNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    MyNoteEntity addNote(@RequestBody MyNoteEntity myNoteEntity) {
        return myNoteService.save(myNoteEntity);
    }

    @GetMapping("/mynote/{id}")
    MyNoteEntity getNote(@PathVariable Long id) {
        return myNoteService.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
    }

    @PutMapping("/mynote/{id}")
    MyNoteEntity updateNote(@PathVariable Long id, @RequestBody MyNoteEntity requestNote) {
        return myNoteService.update(id, requestNote);
    }

    @DeleteMapping("/mynote/{id}")
    void deleteNote(@PathVariable Long id) {
        myNoteService.delete(id);
    }
}

package net.harunote.spring3rest.mynote;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyNoteController {
    Logger log = LoggerFactory.getLogger(MyNoteController.class);
}

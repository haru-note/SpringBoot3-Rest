package net.harunote.spring3rest.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.harunote.spring3rest.hello.model.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@Tag(name = "Hello", description = "the heath check API")
public class HelloController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Operation(operationId = "Hello World!", summary = "Health check", description = "서버가 살아있는지 체크한다.", tags = {"Hello"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Hello.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "ID not found", content = @Content) })
    @GetMapping("/hello")
    public Hello helloWorld(@Parameter(description = "파라미터의 설명을 기입한다.", required = true) @RequestParam(value = "name", defaultValue = "World") String name) {
        return new Hello(counter.incrementAndGet(), String.format(template, name));
    }
}

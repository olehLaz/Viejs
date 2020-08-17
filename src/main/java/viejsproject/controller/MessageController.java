package viejsproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import viejsproject.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});


    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }
    @GetMapping("{id}")
    public Map<String, String> getOne(
            @PathVariable String id
    ) {
        return getMessage(id);

    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }


    //fetch('/message', {method: 'POST', headers: {'Content-Type' : 'application/json'}, body: JSON.stringify({text:'Five message'})}).then(result => console.log(result))
    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    //fetch('/message/4', {method: 'PUT', headers: {'Content-Type' : 'application/json'}, body: JSON.stringify({text:'Fourth message(4)', id: 10})}).then(result => console.log(result))
    @PutMapping("{id}")
    public Map<String, String> update(
            @PathVariable String id,
            @RequestBody Map<String, String> message){
       Map<String, String> messageFromDb = getMessage(id);
       messageFromDb.putAll(message);
       messageFromDb.put("id", id);
        return messageFromDb;
    }

    //fetch('/message/4', { method: 'DELETE' }).then(result => console.log(result))
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }




}

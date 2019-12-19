package io.paltracker.greeting;


import io.paltracker.greeting.model.GreetingGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/v1/greetings")
public class GreetingGeneratorController {

    @Autowired
    GreetingGeneratorRepository greetingGeneratorRepository;

    @GetMapping("/")
    public List<GreetingGenerator> getAllGreetings(){

        return new ArrayList<>(greetingGeneratorRepository.list());

    }

    @GetMapping("/random")
    public GreetingGenerator getRandomGreeting(){
        List<GreetingGenerator> greetingGenerators = getAllGreetings();
        Random random = new Random();
        return   greetingGenerators.get(random.nextInt(greetingGenerators.size()));
    }

    @PostMapping
    public ResponseEntity<GreetingGenerator> createGreeting(@RequestBody GreetingGenerator greetingGenerator){

        GreetingGenerator greetingGenerator1 = greetingGeneratorRepository.create(greetingGenerator);
         if(greetingGenerator1!=null) {
             return new ResponseEntity<>(greetingGenerator1, HttpStatus.CREATED);
         }else{
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
    }
    @GetMapping("/{id}")
    public ResponseEntity<GreetingGenerator> getGreetings(@PathVariable Long id){

        GreetingGenerator greetingGenerator = greetingGeneratorRepository.find(id);
        if(greetingGenerator!=null){
           return  new ResponseEntity<>(greetingGenerator,HttpStatus.OK);
        }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

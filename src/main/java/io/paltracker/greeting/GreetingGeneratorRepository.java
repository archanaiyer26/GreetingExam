package io.paltracker.greeting;

import io.paltracker.greeting.model.GreetingGenerator;

import java.util.List;

public interface GreetingGeneratorRepository {

   GreetingGenerator create(GreetingGenerator greetingGenerator);
   GreetingGenerator update(GreetingGenerator greetingGenerator, Long id);
   void delete(long id);
   GreetingGenerator find(long id);
   List<GreetingGenerator> list();

}

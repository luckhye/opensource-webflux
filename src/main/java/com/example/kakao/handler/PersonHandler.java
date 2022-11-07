package com.example.kakao.handler;

import com.example.kakao.entity.Person;
import com.example.kakao.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class PersonHandler {

    private final Validator validator;  //LocalValidatorFactoryBean
    private final PersonRepository personRepository;

    public Mono<ServerResponse> list(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(personRepository.findAll(), List.class);
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return personRepository.findById(id)
                .flatMap(person -> ok().contentType(APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        System.out.println("request = "+ request);
        Mono<Person> person = request.bodyToMono(Person.class).doOnNext(this::validate);
        System.out.println("person = " + person);

        return person.flatMap(personRepository::save)
                .flatMap(entity -> ok().contentType(APPLICATION_JSON).bodyValue(person));
    }

    private void validate(Person person) {
        Errors errors = new BeanPropertyBindingResult(person, Person.class.getName());
        validator.validate(person, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }
}
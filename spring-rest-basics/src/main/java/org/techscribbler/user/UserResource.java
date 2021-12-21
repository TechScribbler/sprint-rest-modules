package org.techscribbler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.techscribbler.user.exception.UserNotFoundException;
import org.techscribbler.user.bean.User;
import org.techscribbler.user.service.UserDaoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> findAll(){
        return service.findAll();
    }
    @GetMapping("/users/{id}")
    public EntityModel<User> findOne(@PathVariable  int id){
        User user= service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id - " +id);
        }
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody  User user){
        User savedUser = service.save(user);
        URI locationURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locationURI).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user==null)
            throw new UserNotFoundException("id-"+ id);
    }
    @PatchMapping("/users/{id}")
    public User save(@PathVariable int id,@RequestBody User user){
        return service.update(id,user);
    }
}

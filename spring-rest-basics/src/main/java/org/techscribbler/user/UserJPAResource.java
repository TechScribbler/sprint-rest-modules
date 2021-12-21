package org.techscribbler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.techscribbler.user.bean.Post;
import org.techscribbler.user.bean.User;
import org.techscribbler.user.exception.UserNotFoundException;
import org.techscribbler.user.repository.PostRepository;
import org.techscribbler.user.repository.UserRepository;
import org.techscribbler.user.service.UserDaoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository service;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> findAll(){
        return service.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findOne(@PathVariable  int id){
         Optional<User> user= service.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id - " +id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> findPostsById(@PathVariable  int id){
        Optional<User> user= service.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id - " +id);
        }
        return user.get().getPosts();

    }
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody  User user){
        User savedUser = service.save(user);
        URI locationURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locationURI).build();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody  Post post){
        Optional<User> userOptional  = service.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found - "+id);
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI locationURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(locationURI).build();
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);

    }

}

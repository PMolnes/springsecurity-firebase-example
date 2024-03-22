package gg.cratus.springfirebaseauthdemo.user;

import gg.cratus.springfirebaseauthdemo.auth.UserManagementService;
import gg.cratus.springfirebaseauthdemo.auth.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserManagementService userManagementService;

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody CreateUserDto userDto) {
    try {
      var user = userManagementService.createUser(userDto);
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}

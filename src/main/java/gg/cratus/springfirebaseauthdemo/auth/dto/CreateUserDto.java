package gg.cratus.springfirebaseauthdemo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDto {

  private String email;
  private String password;
  private String username;

}

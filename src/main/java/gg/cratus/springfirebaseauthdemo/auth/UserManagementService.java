package gg.cratus.springfirebaseauthdemo.auth;

import java.util.List;
import java.util.Map;

import com.google.firebase.auth.UserRecord;
import gg.cratus.springfirebaseauthdemo.auth.dto.CreateUserDto;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;

import gg.cratus.springfirebaseauthdemo.auth.enums.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManagementService {

  private final FirebaseAuth firebaseAuth;

  private final List<Role> defaultRoles = List.of(Role.USER);

  public <T> void setUserClaims(String uid, List<T> values, String claimName) throws FirebaseAuthException {
    List<String> stringValues = values.stream().map(Object::toString).toList();

    Map<String, Object> claims = Map.of(claimName, stringValues);

    firebaseAuth.setCustomUserClaims(uid, claims);
  }

  public UserRecord createUser(CreateUserDto createUserDto) throws FirebaseAuthException {
      var userRecord = firebaseAuth.createUser(new CreateRequest()
          .setEmail(createUserDto.getEmail())
          .setPassword(createUserDto.getPassword())
          .setDisplayName(createUserDto.getUsername())
      );
      setDefaultRoles(userRecord.getUid());

      return userRecord;
  }

  public void setDefaultRoles(String uid) throws FirebaseAuthException {
    setUserClaims(uid, defaultRoles, "roles");
  }

}
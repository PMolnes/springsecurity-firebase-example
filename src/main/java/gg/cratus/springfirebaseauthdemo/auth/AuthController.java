package gg.cratus.springfirebaseauthdemo.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;

import gg.cratus.springfirebaseauthdemo.auth.enums.Role;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserManagementService userManagementService;

  @GetMapping("/test")
  public String test(Principal principal) {
    return "Principal: " + principal.getName();
  }

  @GetMapping("/authorities")
  public String authorities() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var authorities = authentication.getAuthorities();

    return authorities.toString();
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String admin() {
    return "Admin";
  }

  @PostMapping("/user-claims/{uid}")
  public void setUserClaims(@PathVariable String uid, @RequestBody List<Role> requestedRoles)
      throws FirebaseAuthException {
    userManagementService.setUserClaims(uid, requestedRoles, "roles");
  }

}

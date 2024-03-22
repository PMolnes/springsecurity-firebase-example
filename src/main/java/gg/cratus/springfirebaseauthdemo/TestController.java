package gg.cratus.springfirebaseauthdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/hello")
public class TestController {

  @GetMapping
  public String Hello() {
    return "Hello World";
  }

}

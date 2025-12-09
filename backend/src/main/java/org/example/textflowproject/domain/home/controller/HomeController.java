package org.example.textflowproject.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.home.dto.HomeResponse;
import org.example.textflowproject.domain.home.service.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

  private final HomeService homeService;

  @GetMapping
  public ResponseEntity<HomeResponse> getHome() {
    return ResponseEntity.ok(homeService.getHome());
  }
}

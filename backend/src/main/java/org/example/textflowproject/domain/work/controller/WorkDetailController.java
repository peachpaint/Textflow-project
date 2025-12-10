package org.example.textflowproject.domain.work.controller;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.work.dto.WorkDetailDto;
import org.example.textflowproject.domain.work.service.WorkDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
@RequiredArgsConstructor
public class WorkDetailController {

  private final WorkDetailService workDetailService;

  @GetMapping("/{workId}")
  public ResponseEntity<WorkDetailDto> getWorkDetail(@PathVariable Long workId) {
    WorkDetailDto dto = workDetailService.getWorkDetailById(workId);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<WorkDetailDto> getWorkDetailBySlug(@PathVariable String slug) {
    WorkDetailDto dto = workDetailService.getWorkDetailBySlug(slug);
    return ResponseEntity.ok(dto);
  }
}

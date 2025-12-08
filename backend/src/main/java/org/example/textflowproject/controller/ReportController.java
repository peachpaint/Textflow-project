package org.example.textflowproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @PostMapping("/comment/{commentId}")
  public ResponseEntity<Void> reportComment(@PathVariable Long commentId,
                            @RequestParam Long userId,
                            @RequestParam String reason) {
    reportService.reportComment(userId, commentId, reason);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/work/{workId}")
  public ResponseEntity<Void> reportWork(@PathVariable Long workId,
                         @RequestParam Long userId,
                         @RequestParam String reason) {
    reportService.reportWork(userId, workId, reason);
    return ResponseEntity.ok().build();
  }
}


package org.example.textflowproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/episode/{episodeId}")
  public ResponseEntity<Void> likeEpisode(@PathVariable Long episodeId, @RequestParam Long userId) {
    likeService.likeEpisode(userId, episodeId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/episode/{episodeId}")
  public ResponseEntity<Void> unlikeEpisode(@PathVariable Long episodeId, @RequestParam Long userId) {
    likeService.unlikeEpisode(userId, episodeId);
    return ResponseEntity.ok().build();
  }
}



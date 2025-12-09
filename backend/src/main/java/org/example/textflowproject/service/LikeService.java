package org.example.textflowproject.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.Like.EpisodeLike;
import org.example.textflowproject.domain.user.User;
import org.example.textflowproject.domain.episode.Episode;
import org.example.textflowproject.domain.Like.EpisodeLikeRepository;
import org.example.textflowproject.domain.user.UserRepository;
import org.example.textflowproject.domain.episode.EpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

  private final EpisodeLikeRepository episodeLikeRepository;
  private final UserRepository userRepository;
  private final EpisodeRepository episodeRepository;

  public void likeEpisode(Long userId, Long episodeId) {
    if (episodeLikeRepository.existsByUser_UserIdAndEpisode_EpisodeId(userId, episodeId)) {
      throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
    }

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    Episode episode = episodeRepository.findById(episodeId)
        .orElseThrow(() -> new IllegalArgumentException("회차를 찾을 수 없습니다."));

    EpisodeLike like = EpisodeLike.builder()
        .user(user)
        .episode(episode)
        .build();

    episodeLikeRepository.save(like);
  }

  public void unlikeEpisode(Long userId, Long episodeId) {
    EpisodeLike like = episodeLikeRepository.findByUser_UserIdAndEpisode_EpisodeId(userId, episodeId)
        .orElseThrow(() -> new IllegalArgumentException("좋아요를 찾을 수 없습니다."));
    episodeLikeRepository.delete(like);
  }
}

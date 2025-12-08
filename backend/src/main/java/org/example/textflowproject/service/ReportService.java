package org.example.textflowproject.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.Report.Report;
import org.example.textflowproject.domain.Report.TargetType;
import org.example.textflowproject.domain.user.User;
import org.example.textflowproject.repository.ReportRepository;
import org.example.textflowproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

  private final ReportRepository reportRepository;
  private final UserRepository userRepository;

  public void reportTarget(Long userId, TargetType targetType, Long targetId, String reason) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    Report report = Report.builder()
        .reporter(user)
        .targetType(targetType)
        .targetId(targetId)
        .reason(reason)
        .build();

    reportRepository.save(report);
  }

  public void reportComment(Long userId, Long commentId, String reason) {
    reportTarget(userId, TargetType.COMMENT, commentId, reason);
  }

  public void reportWork(Long userId, Long workId, String reason) {
    reportTarget(userId, TargetType.WORK, workId, reason);
  }

  public void reportEpisode(Long userId, Long episodeId, String reason) {
    reportTarget(userId, TargetType.EPISODE, episodeId, reason);
  }
}


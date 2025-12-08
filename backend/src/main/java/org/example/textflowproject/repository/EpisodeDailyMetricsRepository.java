package org.example.textflowproject.repository;

import org.example.textflowproject.domain.metrics.EpisodeDailyMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EpisodeDailyMetricsRepository extends JpaRepository<EpisodeDailyMetrics, Long> {
  Optional<EpisodeDailyMetrics> findByEpisode_EpisodeIdAndMetricDate(Long episodeId, LocalDate metricDate);
  List<EpisodeDailyMetrics> findByEpisode_EpisodeIdOrderByMetricDateDesc(Long episodeId);
}

package org.example.textflowproject.repository;

import org.example.textflowproject.domain.work.Work;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
  Page<Work> findByAuthor_UserId(Long authorId, Pageable pageable);
  List<Work> findByAuthor_UserId(Long authorId);
}

package org.example.textflowproject.repository;

import org.example.textflowproject.domain.Report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}


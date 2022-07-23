package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.DataChangelog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataChangelogRepo extends JpaRepository<DataChangelog, Long> {}

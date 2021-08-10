package org.maia.hrworker.repositores;

import org.maia.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker , Long> {

}

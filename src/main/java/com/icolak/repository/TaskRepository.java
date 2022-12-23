package com.icolak.repository;

import com.icolak.entity.Task;
import com.icolak.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT COUNT(*) " +
            "FROM tasks t JOIN projects p ON t.project_id = p.id " +
            "WHERE p.project_code=?1 AND t.task_status='COMPLETE'", nativeQuery = true)
    int totalCompletedTasks(String projectCode);
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETE'")
    int totalNonCompletedTasks(String projectCode);
}

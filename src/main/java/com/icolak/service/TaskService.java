package com.icolak.service;

import com.icolak.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO findByTaskId(Long id);
    void save(TaskDTO task);
    TaskDTO update(TaskDTO task);
    void delete(Long id);
}

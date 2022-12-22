package com.icolak.service.impl;

import com.icolak.dto.TaskDTO;
import com.icolak.mapper.MapperUtil;
import com.icolak.mapper.TaskMapper;
import com.icolak.repository.TaskRepository;
import com.icolak.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findByTaskId(Long id) {
        return null;
    }

    @Override
    public void save(TaskDTO task) {

    }

    @Override
    public TaskDTO update(TaskDTO task) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

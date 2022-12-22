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
    private final MapperUtil mapperUtil;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
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

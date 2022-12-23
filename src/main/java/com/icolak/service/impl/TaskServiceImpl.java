package com.icolak.service.impl;

import com.icolak.dto.TaskDTO;
import com.icolak.entity.Task;
import com.icolak.enums.Status;
import com.icolak.mapper.MapperUtil;
import com.icolak.mapper.TaskMapper;
import com.icolak.repository.TaskRepository;
import com.icolak.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return mapperUtil.convert(task, new TaskDTO());
        }
        return null;
    }

    @Override
    public void save(TaskDTO task) {
        task.setTaskStatus(Status.OPEN);
        task.setAssignedDate(LocalDate.now());
        taskRepository.save(mapperUtil.convert(task, new Task()));
    }

    @Override
    public TaskDTO update(TaskDTO dto) {
        Optional<Task> dbTask = taskRepository.findById(dto.getId());
        Task convertedTask = mapperUtil.convert(dto, new Task());
        if (dbTask.isPresent()) {
            convertedTask.setTaskStatus(dbTask.get().getTaskStatus());
            convertedTask.setAssignedDate(dbTask.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }
        return dto;
    }

    @Override
    public void delete(Long id) {

        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isPresent()) {
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }
}

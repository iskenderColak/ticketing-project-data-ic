package com.icolak.service.impl;

import com.icolak.dto.ProjectDTO;
import com.icolak.entity.Project;
import com.icolak.enums.Status;
import com.icolak.mapper.ProjectMapper;
import com.icolak.repository.ProjectRepository;
import com.icolak.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll(Sort.by("projectCode")).stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        return projectMapper.convertToDto(projectRepository.findByProjectCode(projectCode));
    }

    @Override
    public void save(ProjectDTO dto) {

        dto.setProjectStatus(Status.OPEN);
        Project project = projectMapper.convertToEntity(dto);
        projectRepository.save(project);
    }

    @Override
    public void deleteByProjectCode(String projectCode) {

    }

    @Override
    public ProjectDTO update(ProjectDTO project) {
        return null;
    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);
        projectRepository.save(project);
    }
}

package com.icolak.service.impl;

import com.icolak.dto.UserDTO;
import com.icolak.entity.User;
import com.icolak.mapper.UserMapper;
import com.icolak.repository.UserRepository;
import com.icolak.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll(Sort.by("firstName")).stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return userMapper.convertToDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(userMapper.convertToEntity(user));
    }

    @Override
    public void deleteByUserName(String username) {
        //userRepository.delete(userRepository.findByUserName(username));
        userRepository.deleteByUserName(username);
    }

    @Override
    public UserDTO update(UserDTO user) {
        //Find current user
        User dbUser = userRepository.findByUserName(user.getUserName()); // has id
        //Map update userDto to entity object
        User convertedUser = userMapper.convertToEntity(user); // does not have id
        //Set id to the converted object
        convertedUser.setId(dbUser.getId());
        //Save the updated user in the db
        userRepository.save(convertedUser);

        return findByUserName(user.getUserName());
    }
}

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
/*
    // This method lists only the users whose isDeleted field is false
    //Instead of this method we use @Where(clause = "is_deleted=false") annotation
    at the top of entity class which is covering all the queries in the repository
    working with that entity

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));
        return userList.stream()
        //        .filter(user -> user.getIsDeleted() == false)
                .filter(user -> !user.getIsDeleted())
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }
*/
    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));
        return userList.stream()
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

    // Soft deleting --> we change isDeleted field of the user but don't remove from the table
    //By @Where annotation at the top of the User entity we can remove the user from the UI
    @Override
    public void delete(String username) {
        User dbUser = userRepository.findByUserName(username);
        dbUser.setIsDeleted(true);
        userRepository.save(dbUser);
    }

    @Override
    public List<UserDTO> listAllUsersByRole(String description) {
        List<User> users = userRepository.findAllByRoleDescriptionIgnoreCase(description);
        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }
}

package com.workshop.usermanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.workshop.usermanagement.dto.UserDto;
import com.workshop.usermanagement.entity.UserEntity;
import com.workshop.usermanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);

        UserEntity savedUser = userRepository.save(userEntity);

        return mapper.map(savedUser, UserDto.class);
    }

    public UserDto getUser(Integer userId) {
        return mapper.map(userRepository.findById(userId).get(), UserDto.class);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);

        UserEntity updatedUser = userRepository.save(userEntity);

        return mapper.map(updatedUser, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntityList = new ArrayList<>();

        userRepository.findAll().forEach((userEntity -> userEntityList.add(userEntity)));

        return userEntityList
                .stream()
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }
}

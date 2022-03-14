package com.workshop.usermanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.workshop.usermanagement.dto.UserDto;
import com.workshop.usermanagement.entity.UserEntity;
import com.workshop.usermanagement.exception.EntityNotFoundException;
import com.workshop.usermanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new EntityNotFoundException("User not found. Id=" + userId));

        return mapper.map(userEntity, UserDto.class);
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
        return userRepository.findAll()
                .stream()
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByIds(List<Integer> userIds) {
        return userRepository.findAllById(userIds)
                .stream()
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }
}

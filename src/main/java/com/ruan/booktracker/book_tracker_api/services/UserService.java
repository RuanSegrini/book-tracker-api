package com.ruan.booktracker.book_tracker_api.services;

import java.util.List;

import com.ruan.booktracker.book_tracker_api.dto.user.UserCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.user.UserDTO;
import com.ruan.booktracker.book_tracker_api.dto.user.UserUpdateDTO;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        List<User> list= repository.findAll();

        return list.stream()
                .map(UserDTO::new)
                .toList();
    }

    public UserDTO findById(Long id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new UserDTO(entity);
    }

    public UserDTO insert(UserCreateDTO dto) {

        User entity = new User();

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());

        entity = repository.save(entity);

        return new UserDTO(entity);
    }

    public UserDTO update(Long id, UserUpdateDTO dto) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(dto, entity);

        entity = repository.save(entity);

        return new UserDTO(entity);
    }

    private void updateData(UserUpdateDTO dto,User entity ) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}

package com.ruan.booktracker.book_tracker_api.services;

import java.util.List;
import java.util.Optional;

import com.ruan.booktracker.book_tracker_api.dto.ReviewDTO;
import com.ruan.booktracker.book_tracker_api.dto.UserDTO;
import com.ruan.booktracker.book_tracker_api.entities.Review;
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

    public User insert(User obj) {
        return repository.save(obj);
    }

    public User update(Long id, User obj) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}

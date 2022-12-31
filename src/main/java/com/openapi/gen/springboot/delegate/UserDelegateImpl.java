package com.openapi.gen.springboot.delegate;

import com.openapi.gen.springboot.api.UserApiDelegate;
import com.openapi.gen.springboot.dto.UserDTO;
import com.openapi.gen.springboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*
 *  @author: DevProblems(Sarang Kumar A Tak)
 *  Youtube channel: https://youtube.com/@devproblems
 */
@Service
public class UserDelegateImpl implements UserApiDelegate {

    private final UserService userService;

    public UserDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}

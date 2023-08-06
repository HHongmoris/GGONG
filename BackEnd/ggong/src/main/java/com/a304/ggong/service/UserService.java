package com.a304.ggong.service;

import com.a304.ggong.dto.UserSignUpDto;
import com.a304.ggong.entity.Role;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception{
        if(userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .name(userSignUpDto.getName())
                .ageRange(userSignUpDto.getAgeRange())
                .gender(userSignUpDto.getGender())
                .favoriteCigarette(userSignUpDto.getFavoriteCigarette())
                .QR(userSignUpDto.getQR())
                .userRating(userSignUpDto.getUserRating())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);

    }
}

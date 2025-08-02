package com.erdal.service;

import com.erdal.dto.LoginRequest;
import com.erdal.dto.LoginResponse;
import com.erdal.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    // Buraya userRepository, passwordEncoder, jwtService vs. gibi bağımlılıkları ekleyeceksin.

    @Override
    public LoginResponse register(RegisterRequest request) {
        // Kayıt işlemini buraya yaz
        // Kullanıcı oluştur, password encode et, token üret vs.

        // Şimdilik dummy token dönelim (sonra gerçek token ile değiştirirsin)
        return new LoginResponse("dummy-token-register", null);
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        // Giriş işlemi burada olacak
        // Kullanıcı doğrulama, password kontrolü, token üretimi vs.

        // Şimdilik dummy token dönelim (sonra gerçek token ile değiştirirsin)
        return new LoginResponse("dummy-token-login", null);
    }
}

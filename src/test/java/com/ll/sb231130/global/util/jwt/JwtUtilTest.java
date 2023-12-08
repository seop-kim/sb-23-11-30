package com.ll.sb231130.global.util.jwt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtUtilTest {
    @Test
    @DisplayName("t1")
    void t1() {
        Map<String, String> data = Map.of("name", "홍길동", "age", "22");
        String jwtToken = JwtUtil.encode(data);

        System.out.println(jwtToken);

        Assertions.assertThat(jwtToken).isNotNull();
    }
}
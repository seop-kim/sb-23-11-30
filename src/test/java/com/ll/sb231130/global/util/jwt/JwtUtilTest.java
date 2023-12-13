package com.ll.sb231130.global.util.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtUtilTest {
    @Test
    @DisplayName("t1")
    void t1() {
        Map<String, Object> data = Map.of("name", "홍길동", "age", "22");
        String jwtToken = JwtUtil.encode(60,data);

        System.out.println(jwtToken);

        assertThat(jwtToken).isNotNull();
    }
}
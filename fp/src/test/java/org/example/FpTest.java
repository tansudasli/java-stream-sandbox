package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FpTest {

    @Test
    @DisplayName("Test Fp class construct")
    void isFpValid() {
        assertEquals(5, new Fp(5).getI());
    }

}
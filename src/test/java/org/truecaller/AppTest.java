package org.truecaller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    private App app = new App();

    @Test
    void main() {
        String[] args = {"5", "5"};
        app.main(args);
    }

    @Test
    void mainInvalidArgs() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            String[] args = {"5A", "5A"};
            app.main(args);
        });
    }

    @Test
    void mainOutOfBoundLargeArgs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"11", "13"};
            app.main(args);
        });
    }

    @Test
    void mainOutOfBoundLessArgs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"-1", "11"};
            app.main(args);
        });
    }
}
package sy.gameObjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameObjectTest {
    private TestGameObject gameObject;

    @BeforeEach
    void setUp() {
        gameObject = new TestGameObject(null);
        gameObject.update(0);
        gameObject.draw(0, null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateCalled() {
        assertTrue(gameObject.isUpdateCalled);
    }

    @Test
    void drawCalled() {
        assertTrue(gameObject.isDrawCalled);
    }
}
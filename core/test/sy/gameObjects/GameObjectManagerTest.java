package sy.gameObjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sy.gameObjects.TestGameObject;
import sy.rendering.RenderPipeline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameObjectManagerTest {
    private GameObjectManager gameObjectManager;
    private TestGameObject initialInsertedObj;

    @BeforeEach
    void setUp() {
        gameObjectManager = new GameObjectManager();
        initialInsertedObj = gameObjectManager.create(TestGameObject.class);
    }

    @AfterEach
    void tearDown() {
        gameObjectManager = null;
        initialInsertedObj = null;
    }

    @Test
    void createGameObject() {
        TestGameObject testGameObject = gameObjectManager.create(TestGameObject.class);
        assertEquals(testGameObject, gameObjectManager.get(testGameObject.getUuid()));
    }

    @Test
    void getGameObject() {
        assertEquals(initialInsertedObj, gameObjectManager.get(initialInsertedObj.getUuid()));
    }

    @Test
    void updateCalled() {
        gameObjectManager.update(0);
        assertTrue(initialInsertedObj.isUpdateCalled);
    }

    @Test
    void drawCalled() {
        gameObjectManager.draw(0, null);
        assertTrue(initialInsertedObj.isDrawCalled);
    }

    @Test
    void lifecycleRemove() {
        initialInsertedObj.setAlive(false);
        gameObjectManager.update(0);
        assertNull(gameObjectManager.get(initialInsertedObj.getUuid()));
    }
}
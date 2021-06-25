package sy.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.gameObjects.GameObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;
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
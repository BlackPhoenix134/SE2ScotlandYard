package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.MapNode;
import sy.core.Math.GoodMath;
import sy.core.Math.Randum;
import sy.core.Tickets.TicketType;
import sy.core.Tickets.Tickets;
import sy.rendering.RenderPipeline;

public class PawnObject extends GameObject implements NetworkIdentifiable {
    private int netId;
    private MapNode mapNode;
    private String name = "Ape " + Randum.get().nextInt(100);

    /**
     * Index vom NodeGraphObject (nodepositions)
     */
    private Sprite sprite;


    PawnObject(String uuid) {
        super(uuid);
        Texture texture = SYAssetManager.getAsset(AssetDescriptors.MONSTER1);
        sprite = new Sprite(texture);
        sprite.setScale(0.45f);
        sprite.setPosition(0, 0);
    }

    public void setTexture(Texture texture){
        sprite = new Sprite(texture);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        if(mapNode != null) {
            sprite.setPosition(mapNode.getPosition().x, mapNode.getPosition().y);
        }
        pipeline.add(sprite, 55);
        //pipeline.add(name, new Vector2(sprite.getX(), sprite.getY()), 2, 100);
    }

    public void setMapNode(MapNode mapNode){
        this.mapNode = mapNode;
        this.sprite.setPosition(mapNode.getPosition().x, mapNode.getPosition().y);
    }

    public MapNode getMapNode(){
        return this.mapNode;
    }

    public boolean removeTicket(TicketType type) {
        return false;
    }

    @Override
    public int getNetId() {
        return this.netId;
    }
    public void setNetId(int netId) {
        this.netId = netId;
    }

    public boolean hasEnoughTickets (TicketType ticketType) {
        return  false;
    }
}
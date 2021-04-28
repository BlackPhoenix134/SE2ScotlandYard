package sy.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RenderPipeline implements Disposable {
    private DefaultRenderer defaultRenderer;
    private PrimitiveRenderer primitiveRenderer;

    private OrthographicCamera camera;
    private Viewport viewport;


    //ToDo: abstract begin
    public DefaultRenderer getDefaultRenderer() {
        return defaultRenderer;
    }

    public PrimitiveRenderer getPrimitiveRenderer() {
        return primitiveRenderer;
    }

    public RenderPipeline(SpriteBatch batch, OrthographicCamera camera, ExtendViewport viewport) {
        this.camera = camera;
        this.viewport = viewport;
        this.defaultRenderer = new DefaultRenderer(batch);
        this.primitiveRenderer = new PrimitiveRenderer(defaultRenderer);
    }

    public void updateBatchMatrix() {
        defaultRenderer.updateBatchMatrix(camera);
        primitiveRenderer.updateBatchMatrix(camera);
    }


    @Override
    public void dispose(){
        defaultRenderer.dispose();
        primitiveRenderer.dispose();
    }
}

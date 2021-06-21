package sy.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import java.util.HashMap;
import java.util.Map;

public class ShaderManager {
    private Map<Integer, ShaderCacheEntry> cache = new HashMap<>();
    public final static ShaderProgram defaultShader = null; //Passing null to batch makes batch use default shader

    public ShaderManager() {
        ShaderProgram.pedantic = false;
    }

    public ShaderProgram loadShader(String vertexShaderPath, String fragmentShaderPath) {
        String fullVertexPath = resolvePath(vertexShaderPath);
        String fullFragPath  = resolvePath(fragmentShaderPath);

        int hashCode = ShaderCacheEntry.hashCodeAlgorithm(fullVertexPath, fullFragPath);
        ShaderProgram retVal;
        if(cache.containsKey(hashCode)) {
            retVal = cache.get(hashCode).getShaderProgram();
        } else {
            ShaderCacheEntry cacheEntry = new ShaderCacheEntry(
                    new ShaderProgram(fullVertexPath, fullFragPath));
            cache.put(cacheEntry.hashCode(), cacheEntry);
            retVal = cacheEntry.getShaderProgram();
        }
        return retVal;
    }

    public ShaderProgram loadShader(String shaderPath, boolean isVertexShader) {
        if(isVertexShader)
            return loadShader(shaderPath, "default.frag.glsl");
        else
            return loadShader("default.vertex.glsl", shaderPath);
    }

    public ShaderProgram loadDefaultShader() {
            return loadShader("default.vertex.glsl", "default.frag.glsl");
    }

    private String resolvePath(String path) {
        return Gdx.files.internal("shader/" + path).readString();
    }
}

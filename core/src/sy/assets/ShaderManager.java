package sy.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.HashMap;
import java.util.Map;

public class ShaderManager {
    private Map<Integer, ShaderCacheEntry> cache = new HashMap<>();

    public ShaderProgram loadShader(String vertexShaderPath, String fragmentShaderPath) {
        int hashCode = ShaderCacheEntry.hashCodeAlgorithm(vertexShaderPath, fragmentShaderPath);
        ShaderProgram retVal;
        if(cache.containsKey(hashCode)) {
            retVal = cache.get(hashCode).getShaderProgram();
        } else {
            ShaderCacheEntry cacheEntry = new ShaderCacheEntry(
                    new ShaderProgram(vertexShaderPath, fragmentShaderPath));
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

    private String resolvePath(String path) {
        return Gdx.files.internal(path).toString();
    }
}

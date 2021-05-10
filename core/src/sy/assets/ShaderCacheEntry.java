package sy.assets;

import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderCacheEntry {
    private ShaderProgram shaderProgram;

    public ShaderCacheEntry(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    @Override
    public int hashCode() {
        return hashCodeAlgorithm(shaderProgram.getVertexShaderSource(), shaderProgram.getFragmentShaderSource());
    }

    public static int hashCodeAlgorithm(String vertexSource, String fragmentSource) {
        int result = 17;
        result += 37 * vertexSource.hashCode();
        result += 37 * fragmentSource.hashCode();
        return result;
    }
}

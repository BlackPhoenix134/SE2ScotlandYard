package sy.core.Visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationController {
    TextureRegion[] sheet;
    private int currIdx = 0;
    private float frameTime = 0;

    public AnimationController(Texture animSheet, int cols, int rows, float frameTime) {
        this.frameTime = frameTime;

        TextureRegion[][] tmp = TextureRegion.split(animSheet,
                animSheet.getWidth() / cols,
                animSheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        sheet = frames;
    }

    public float getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(float frameTime) {
        this.frameTime = frameTime;
    }

    public int getCurrentFrameIdx() {
        return currIdx;
    }

    public int getMaxAnimationIdx() {
        return sheet.length - 1;
    }

    public TextureRegion getNextFrame() {
        currIdx = clampIdx(currIdx + 1);
        return getCurrentFrame();
    }

    public TextureRegion setFrameTo(int idx) {
        currIdx = clampIdx(idx);
        return getCurrentFrame();
    }

    public TextureRegion getCurrentFrame() {
        return sheet[currIdx];
    }

    private int clampIdx(int idx) {
        if(idx > getMaxAnimationIdx())
            return 0;
        return idx;
    }
}

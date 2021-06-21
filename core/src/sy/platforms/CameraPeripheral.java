package sy.platforms;

import sy.core.Consumer;

public interface CameraPeripheral {
    void startCamera();
    void setOnCameraResult(Consumer<byte[]> onCameraResult);
}

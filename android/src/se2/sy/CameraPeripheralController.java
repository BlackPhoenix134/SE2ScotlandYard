package se2.sy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

import sy.core.Consumer;
import sy.platforms.CameraPeripheral;

public class CameraPeripheralController implements CameraPeripheral {
    private AndroidLauncher androidLauncher;
    private Consumer<byte[]> onCameraResult;

    public CameraPeripheralController(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    @Override
    public void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            androidLauncher.startActivityForResult(takePictureIntent, 1);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setOnCameraResult(Consumer<byte[]> onCameraResult) {
        this.onCameraResult = onCameraResult;
    }

    public void callOnCameraResult(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        onCameraResult.call(byteArray);
    }
}

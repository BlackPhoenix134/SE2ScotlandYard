package se2.sy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import sy.GameStart;
import sy.platforms.CameraPeripheral;

public class AndroidLauncher extends AndroidApplication {
	private CameraPeripheralController cameraPeripheralController;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		cameraPeripheralController = new CameraPeripheralController(this);
		initialize(new GameStart(cameraPeripheralController), config);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			new Thread(() -> cameraPeripheralController.callOnCameraResult(imageBitmap)).start();
		}
	}
}

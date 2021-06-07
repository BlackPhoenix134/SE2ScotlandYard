package se2.sy;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import sy.Game;
import sy.camera.AndroidDeviceCameraController;
import sy.camera.DeviceCameraControl;

public class AndroidLauncher extends AndroidApplication {


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Game(), config);

		config.r = 8;
		config.g = 8;
		config.b = 8;
		config.a = 8;

		DeviceCameraControl cameraControl = new AndroidDeviceCameraController(this);
	}
}

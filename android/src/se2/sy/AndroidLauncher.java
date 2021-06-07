package se2.sy;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import sy.Game;
import sy.camera.AndroidDeviceCameraController;
import sy.camera.DeviceCameraControl;
import sy.camera.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	private int origWidth;
	private int origHeight;


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
		initialize(new MyGdxGame(cameraControl), config);

		if (graphics.getView() instanceof SurfaceView) {
			SurfaceView glView = (SurfaceView) graphics.getView();

			// force alpha channel - I'm not sure we need this as the GL surface is already using alpha channel
			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		}
		// we don't want the screen to turn off during the long image saving process
		graphics.getView().setKeepScreenOn(true);

		// keep the original screen size
		origWidth = graphics.getWidth();
		origHeight = graphics.getHeight();
	}



	public void post(Runnable r) {
		handler.post(r);
	}

	public void setFixedSize(int width, int height) {
		if (graphics.getView() instanceof SurfaceView) {
			SurfaceView glView = (SurfaceView) graphics.getView();
			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
			glView.getHolder().setFixedSize(width, height);
		}
	}

	public void restoreFixedSize() {
		if (graphics.getView() instanceof SurfaceView) {
			SurfaceView glView = (SurfaceView) graphics.getView();
			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
			glView.getHolder().setFixedSize(origWidth, origHeight);
		}
	}
}
	}
}

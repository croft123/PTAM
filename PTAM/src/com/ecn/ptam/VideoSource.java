package com.ecn.ptam;

import android.hardware.Camera;

public class VideoSource {

	private Camera mCamera;

	public VideoSource() {
		if (mCamera == null) {
			mCamera = getCameraInstance();
		}
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	public int[] getSize() {
		int[] size = { mCamera.getParameters().getPreviewSize().width,
				mCamera.getParameters().getPreviewSize().height };
		return size;
	}

	public Camera getCamera() {
		return mCamera;
	}

	public byte[] getFrame() {
		byte[] data;
		CameraAnalyser ca = new CameraAnalyser();
		mCamera.setOneShotPreviewCallback(ca);

		try {
			data = ca.waitResult();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data = null;
		}
		return data;
	}
}
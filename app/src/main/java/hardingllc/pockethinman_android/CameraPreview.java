package hardingllc.pockethinman_android;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder mHolder;
    Camera mCamera;

    public CameraPreview(Context context) {
        super(context);
        mCamera = null;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context);
        mCamera = null;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (holder.getSurface() == null){
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            Log.d("1", "Error: " + e.getMessage());
        }

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d("1", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("1", "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO
    }
}
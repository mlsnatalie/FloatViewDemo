package com.example.menglingshuai.floatviewdemo

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

class AddCameraPipActivity : PipActivity() {

    private var addCameraView: ImageView? = null
    var isStart: Boolean = false

    override fun onCreate() {
        super.onCreate()
        windowManagerParams.gravity = Gravity.BOTTOM or Gravity.RIGHT
        windowManagerParams.x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, context.resources.displayMetrics).toInt()
        windowManagerParams.y = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64f, context.resources.displayMetrics).toInt()
    }

    override fun onCreateView(inflater: LayoutInflater): View {
        return inflater.inflate(R.layout.activity_camera_pip_add, null)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
//        isStart = true
        view.setOnTouchListener(PipTouchListener(this).apply { setAutoToEdge(true) })
        addCameraView = view.findViewById(R.id.iv_add)
        view!!.setOnClickListener {
//            CameraTargetHelper.openController()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        isStart = false
        view.setOnTouchListener(null)
    }

    fun showAddCamera() {
        if (!isStart) {
            startPip()
        } else {
            show()
        }
//        show()
    }

    fun hideAddCamera(isClose: Boolean = false) {
        if (isStart) {
            if (isClose) {
                PipLauncher.getInstance().closePip("AddCameraPipActivity")
            } else {
                hide()
            }
        }
    }
}
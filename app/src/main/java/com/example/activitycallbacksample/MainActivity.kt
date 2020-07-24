package com.example.activitycallbacksample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cnt = 0
    val handler = Handler()

    //タイマー処理
    private val runnable = object : Runnable{
        override fun run() {
            cnt++
            textView1.text = cnt.toString()
            if(cnt < 500){
                handler.postDelayed(this,1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //破棄された場合の処理
        if(savedInstanceState !== null) {
            cnt = savedInstanceState.getInt("CNT_KEY")
            textView2.text = savedInstanceState.getString("MSG_KEY")
        }

        handler.post(runnable)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //Bundleオブジェクトで保存
        outState.putInt("CNT_KEY", cnt)
        outState.putString("MSG_KEY", textView2.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        //処理例
        //textView2.text = savedInstanceState?.getString("MSG_KEY")
    }

    override fun onPause() {
        super.onPause()
        textView2.text = getString(R.string.txt_stop)
        handler.removeCallbacks(runnable)
    }

    override fun onStart() {
        super.onStart()
        textView2.text = getString(R.string.txt_start)
        handler.post(runnable)
    }
}



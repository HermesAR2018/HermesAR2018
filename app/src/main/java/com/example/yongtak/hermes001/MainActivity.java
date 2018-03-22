package com.example.yongtak.hermes001;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.mock.MockContext;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.ar.core.Camera;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;

public class MainActivity extends AppCompatActivity {
    private Session session;
    private GLSurfaceView surfaceView;
    private Context mainPointer;
    private Frame frame;
    private Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mainPointer = this;
        //여기까지 추가된 것

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    session = new Session(mainPointer);
                    session.resume();

                    Snackbar.make(view, "성공하였습니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } catch (RuntimeException e) {
                    Snackbar.make(view, "실행에 실패하였습니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                // 아래는 복사 붙여 넣기
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

                if (session == null) {
                    return;
                }
                // Notify ARCore session that the view size changed so that the perspective matrix and
                // the video background can be properly adjusted.

                try {
                    // Obtain the current frame from ARSession. When the configuration is set to
                    // UpdateMode.BLOCKING (it is by default), this will throttle the rendering to the
                    // camera framerate.
                    Frame frame = session.update();
                    Camera camera = frame.getCamera();
                } catch (Throwable t) {
                    // Avoid crashing the application due to unhandled exceptions.
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
    }
}

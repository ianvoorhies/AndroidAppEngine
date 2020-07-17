package com.test.enginetest;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.test.enginetest._super_structure.EngineStructure;
import com.test.enginetest._super_structure.ScreenStructure;
public class Execute extends AppCompatActivity {
    private EngineStructure engine;
    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        engine=new EngineStructure(this);
        engine.switchScene(new ScreenStructure());
    }
    @Override public void onDestroy(){
        super.onDestroy();
        engine.destroy();
    }
    @Override public void onBackPressed(){engine.backPressed();}
    @Override public void onPause(){
        super.onPause();
        engine.pause();
    }
    @Override public void onResume(){
        super.onResume();
        engine.resume();
    }
}


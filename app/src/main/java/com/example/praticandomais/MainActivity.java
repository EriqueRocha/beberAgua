package com.example.praticandomais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnNotify;
    private EditText editText;
    private TextView textView;
    private TimePicker timePicker;
    private int hour;
    private int minute;
    private int interval;
    private boolean activated = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.buttonNotify);
        editText = findViewById(R.id.txtEdit);
        textView = findViewById(R.id.txtDicaInterval);
        timePicker = findViewById(R.id.timer);
         TextView txtLogicoBtnNotify = findViewById(R.id.buttonNotify);
         txtLogicoBtnNotify.setText(R.string.txtBtnNotify);

         TextView txtLogicoDicaEditText = findViewById(R.id.txtEdit);
         txtLogicoDicaEditText.setHint(R.string.txtDica);

         TextView txtLogicoDicaInterval = findViewById(R.id.txtDicaInterval);
         txtLogicoDicaInterval.setText(R.string.txtDicaDoIntevalo);

         timePicker.setIs24HourView(true);

         preferences = getSharedPreferences("dataBase", Context.MODE_PRIVATE);
         activated = preferences.getBoolean("activated",false);

         if(activated){
             btnNotify.setText(R.string.pause);
             btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));

             int hour = preferences.getInt("hour", timePicker.getCurrentHour());
             int minute = preferences.getInt("hour", timePicker.getCurrentMinute());
             int interval = preferences.getInt("interval",0);

             timePicker.setCurrentHour(hour);
             timePicker.setCurrentMinute(minute);
             editText.setText(String.valueOf(interval));
         }

    }

    public void notifyClick(View view){
        String sInterval = editText.getText().toString();

        if(sInterval.isEmpty()){
            Toast.makeText(this,R.string.error_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);

        if(!activated){
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));
            activated=true;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("hour",hour);
            editor.putInt("minute",minute);
            editor.putInt("interval",interval);
            editor.apply();

        }else{
            btnNotify.setText(R.string.txtBtnNotify);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
            activated=false;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated",false);
            editor.remove("hour");
            editor.remove("minute");
            editor.remove("interval");
            editor.apply();
        }

        Log.d("teste","Hora: "+hour+", minutos: "+minute+", interval: "+interval+"min");
    }
}
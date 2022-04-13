package com.example.praticandomais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private Button btnNotify;
    private EditText editText;
    private TextView textView;
    private TimePicker timePicker;
    private int hour;
    private int minute;
    private int interval;

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
    }

    public void notifyClick(View view){
        String sInterval = editText.getText().toString();

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);

        Log.d("teste","Hora: "+hour+", minutos: "+minute+", interval: "+interval+"minutos");
    }
}
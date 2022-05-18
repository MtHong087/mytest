package com.example.boardcast_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyReceiver myReceiver;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        Button button=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2) ;


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent("broadcast_222");
                intent2.setPackage(getPackageName());
                sendBroadcast(intent2);

                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tome");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                editor.apply();

            }
        });


        intentFilter=new IntentFilter();
        intentFilter.addAction("111");
        myReceiver=new MyReceiver();
        registerReceiver(myReceiver,intentFilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("111");
                sendBroadcast(intent);
               // Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
                SharedPreferences pre=getSharedPreferences("data",MODE_PRIVATE);
                String name=pre.getString("name","");
                int age=pre.getInt("age",0);
                boolean married=pre.getBoolean("married",false);
                Log.d("SharedPreferences","name is "+name);
                Log.d("SharedPreferences","age is "+age);
                Log.d("SharedPreferences","married is "+married);
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();



            }
        });

    }
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received in MyBroadcastReceiver",Toast.LENGTH_SHORT).show();
        }
    }
}

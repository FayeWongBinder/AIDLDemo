package com.study.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    IBookManager iBookManager;
   ServiceConnection serviceConnection=new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           Log.e(TAG,"onserviceConnected");
                 iBookManager=IBookManager.Stub.asInterface(service);
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {

       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  List<Book> list=iBookManager.getBookList();
                 if(list!=null){
                     for(int i=0;i<list.size();i++){
                         Log.e("value",list.get(i).toString());
                     }
                 }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iBookManager.setBook(new Book((int)(Math.random()*100+1),"weihua"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
//        Intent intent=new Intent(this,AIDLService.class);
//        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        //abc
    }
}

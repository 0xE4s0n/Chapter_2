package e4s0n.adr.chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.List;

import e4s0n.adr.chapter_2_server.aidl.IBookManager;
import e4s0n.adr.chapter_2_server.aidl.Book;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(iBinder);
            try{
                List<Book> list = bookManager.getBookList();
                Log.i(TAG,"list type:"+list.getClass().getCanonicalName());
                Log.i(TAG,"list:"+list.toString());
            }catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bu = findViewById(R.id.button);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("e4s0n.adr.chapter_2_server","e4s0n.adr.chapter_2_server.BookManagerService");
                intent.setComponent(componentName);
                bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}

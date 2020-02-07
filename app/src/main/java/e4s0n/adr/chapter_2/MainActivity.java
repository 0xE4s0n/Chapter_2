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

import e4s0n.adr.chapter_2_server.aidl.IBinderPool;
import e4s0n.adr.chapter_2_server.aidl.IBookManager;
import e4s0n.adr.chapter_2_server.aidl.Book;
import e4s0n.adr.chapter_2_server.aidl.ICompute;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int BINDER_BOOK = 1;
    public static final int BINDER_COMPUTE = 2;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBinderPool binderPool = IBinderPool.Stub.asInterface(iBinder);

            try{
                IBookManager bookManager = IBookManager.Stub.asInterface(binderPool.queryBinder(BINDER_BOOK));
                ICompute compute = ICompute.Stub.asInterface(binderPool.queryBinder(BINDER_COMPUTE));
                List<Book> list = bookManager.getBookList();
                Log.i(TAG,"list type:"+list.getClass().getCanonicalName());
                Log.i(TAG,"list:"+list.toString());
                Log.i(TAG,"1+2="+compute.add(1,2));
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
                ComponentName componentName = new ComponentName("e4s0n.adr.chapter_2_server","e4s0n.adr.chapter_2_server.BinderPoolService");
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

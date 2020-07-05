package com.study.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class AIDLService extends Service {
    private List<Book> mList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mList;
        }

        @Override
        public void setBook(Book book) throws RemoteException {
            mList.add(book);
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

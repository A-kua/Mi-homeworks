package fan.akua.day2.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import fan.akua.day2.IMyAidlInterface;

public class AIDLService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder.asBinder();
    }

    private static final IMyAidlInterface iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int basicTypes(int a, int b) throws RemoteException {
            return a + b;
        }
    };
}

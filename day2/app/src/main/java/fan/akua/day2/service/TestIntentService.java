package fan.akua.day2.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class TestIntentService extends IntentService {

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }
}
package fan.akua.day2.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Arrays;

import fan.akua.day2.databinding.ActivityProviderBinding;

public class ContentProviderActivity extends Activity {
    private static final String TAG = ContentProviderActivity.class.getName();

    protected ActivityProviderBinding binding;
    protected ContentResolver mContentResolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContentResolver = ContentProviderActivity.this.getContentResolver();
        binding.read.setOnClickListener(v -> {
            Uri mImageUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;

            Cursor mCursor = mContentResolver.query(mImageUri, null,
                    null,null,null);

            if (mCursor == null) {
                binding.tv.setText("获取失败");
                return;
            }
            for (int i = 0; i < mCursor.getColumnCount(); i++) {
                mCursor.moveToPosition(i);
//                int columnIndex = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
//                String string = mCursor.getString(columnIndex);
                String[] columnNames = mCursor.getColumnNames();
                int columnIndex = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                byte[] blob = mCursor.getBlob(columnIndex);

                Log.d(TAG, Arrays.toString(columnNames)+" "+blob.length);
            }

            mCursor.close();
        });
    }
}

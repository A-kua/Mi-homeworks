package fan.akua.day2.activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fan.akua.day2.R;
import fan.akua.day2.databinding.ActivityProviderBinding;

public class ContentProviderActivity extends Activity {
    private static final String TAG = ContentProviderActivity.class.getName();
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 0x111;

    protected ActivityProviderBinding binding;
    protected ContentResolver mContentResolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContentResolver = ContentProviderActivity.this.getContentResolver();
        binding.read.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                readFromContentProvider();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readFromContentProvider();
            } else {
                Toast.makeText(this, "请授权", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void readFromContentProvider() {
        new Thread(() -> {
            Cursor cursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                    null, null, null);
            if (cursor != null) {
                // 先存进ArrayList，加载完再一起交给ListView
                ArrayList<Bitmap> bitmaps = new ArrayList<>();
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID));
                    Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        bitmaps.add(bitmap);
                    } catch (Exception e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
                cursor.close();
                runOnUiThread(() -> binding.listView.setAdapter(new ImageAdapter(bitmaps)));
            }
        }).start();
    }

    private static class ImageAdapter extends BaseAdapter {

        private final List<Bitmap> bitmaps;

        public ImageAdapter(List<Bitmap> bitmaps) {
            this.bitmaps = bitmaps;
        }

        @Override
        public int getCount() {
            return bitmaps.size();
        }

        @Override
        public Object getItem(int position) {
            return bitmaps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.image_view);

            imageView.setImageBitmap(bitmaps.get(position));
            return convertView;
        }
    }
}

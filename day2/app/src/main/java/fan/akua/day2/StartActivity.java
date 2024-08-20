package fan.akua.day2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import fan.akua.day2.databinding.LayoutStartBinding;

public class StartActivity extends Activity {
    protected LayoutStartBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.call.setOnClickListener(v -> {
            if (binding.callNum.getText().toString().isEmpty()) {
                Toast.makeText(StartActivity.this, "请填写内容", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri telUri = Uri.parse("tel:" + binding.callNum.getText().toString());
            Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
            startActivity(intent);
        });
        binding.openContact.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(Contacts.People.CONTENT_URI);
            startActivity(intent);
        });
        binding.openMessage.setOnClickListener(v -> {
            if (binding.callNum.getText().toString().isEmpty()) {
                Toast.makeText(StartActivity.this, "请填写内容", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri telUri = Uri.parse("smsto:" + binding.callNum.getText().toString());
            Intent intent = new Intent(Intent.ACTION_SENDTO, telUri);
            startActivity(intent);
        });
    }
}

package me.tomify.shortcut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addShortcut();
    }

    private Intent shortcutIntent() {
        Intent intent = new Intent();
        intent.setAction("android.provider.action.BROWSE");
        intent.setClassName("com.android.documentsui", "com.android.documentsui.FilesActivity");
        intent.setData(Uri.parse(Environment.getExternalStorageDirectory().getPath()));
        intent.setType("*/*");
        return intent;
    }

    private void addShortcut() {
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent());
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Explorer");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), android.R.drawable.sym_def_app_icon));
        intent.putExtra("duplicate", false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        sendBroadcast(intent);
    }
}

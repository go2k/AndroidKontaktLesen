package sabel.com.kontaktelesen;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnHoleKontakt;
    public static final int RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT) {

        }
    }
    private void getContachInfo(Intent intent) {
        CursorLoader cursorLoader = new CursorLoader(this, intent.getData(), null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int cnt = 1;
        while (cursor.moveToNext()) {
            String contactID = storeValue(cursor, "", ContactsContract.Contacts._ID, true, -1);
        }
    }

    private String storeValue(Cursor cursor, String pre, String key, boolean store, int cnt) {
        String value = cursor.getString(cursor.getColumnIndexOrThrow(key));
        Log.v("KONTAKT", "### " + key + "  " + value);
        if (value != null && store) {
            String newkey = pre + (cnt < 0 ? key : key + " " + String.valueOf(cnt));
            contact.put(newkey, value);
        }
        return value;
    }


    private void initEvents() {

        btnHoleKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,RESULT);
            }
        });
    }

    private void initComponents() {

        btnHoleKontakt = findViewById(R.id.btnHoleKontakt);

    }
}

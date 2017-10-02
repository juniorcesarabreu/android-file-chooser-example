package tk.juniorcesarabreu.androidfilechooserdemo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_CODE = 6384; // onActivityResult Request

    // code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///setContentView(R.layout.activity_main);

        // Create a simple button to start the file chooser process
        Button button = new Button(this);
        button.setText("Select a file");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Display the file chooser dialog
                showChooser();
            }
        });


        setContentView(button);
    }

    private void showChooser() {

        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();

        // Create the chooser intent
        Intent intent = Intent.createChooser(
                target, "Lorem ipsum"
        );

        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existent of afileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {

                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());

                        try {
                            // Get the file path from the URI
                            final String path = FileUtils.getPath(this, uri);

                            Toast.makeText(MainActivity.this, "File selected [x]: " + path, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e("FileSelectorTestActvity", "File select error", e);
                        }
                    }
                }
                break;

        }
    }
}

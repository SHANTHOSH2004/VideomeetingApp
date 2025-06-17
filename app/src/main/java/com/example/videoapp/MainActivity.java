package com.example.videoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeJitsiMeet();
    }

    private void initializeJitsiMeet() {
        try {
            // Use Jitsi's default server or your own hosted instance
            URL serverURL = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    // Remove the welcomePageEnabled line as it's not supported
                    .build();

            // Set default options that will be used for all conferences
            org.jitsi.meet.sdk.JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error initializing video conference", Toast.LENGTH_SHORT).show();
        }
    }

    public void Joinmeeting(View view) {
        TextInputEditText editText = findViewById(R.id.EdRoom);
        String meetingid = editText.getText().toString().trim();

        if (meetingid.isEmpty()) {
            Toast.makeText(this, "Please enter a meeting ID", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(meetingid)
                    .build();

            JitsiMeetActivity.launch(this, options);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error joining meeting", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.converter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                //        .setAction("Action", null).show();
//            }
//        });

        final TextView drOutput = findViewById(R.id.drOutput);
        final EditText drEdit = findViewById(R.id.drEdit);
        //look into "OnEditorAction" for the "Enter" part of the input

        Button drButton = findViewById(R.id.drButton);
        drButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String raw = drEdit.getText().toString();
                    raw.trim();



                    if(onlyNumsSpaces(raw) == false) {
                        System.out.println("please run");
                        drOutput.setText("ERROR\nPlease only enter numbers and spaces.");
                    }
                    else {

                        drOutput.setText("THE DIGITAL ROOT IS: \n" + raw);
                    }





                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    boolean onlyNumsSpaces(String s) {
        System.out.println("String is: "+s);
        if(s.isEmpty()) {
            System.out.println("Returning at isEmpty()");
            return true;
        }

        //Pattern p = Pattern.compile("[^0123456789 ]");
        //Matcher m = p.matcher(s);

        System.out.println("S.matches returns " + s.matches("^[0123456789 ]+$"));

        return s.matches("^[0123456789 ]+$");

    }
}

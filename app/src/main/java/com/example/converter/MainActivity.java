package com.example.converter;

import android.content.Intent;
import android.net.Uri;
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
        //Click event for the button
        drButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //gets the text from the input
                    String raw = drEdit.getText().toString();
                    raw = raw.trim();


                    //checks to see if text is well formatted
                    if(onlyNumsSpaces(raw) == false) {
                        drOutput.setText("ERROR\nPlease only enter numbers and spaces.");
                        Snackbar.make(view, "ERROR", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else {
                        //second check
                        try {
                            //if its well formatted, compute the digital root
                            int sum = 0;
                            int digitalRoot = 0;

                            String[] parts = raw.split(" ");
                            for(String s:parts) {
                                int num = Integer.parseInt(s);
                                sum += num;
                            }

                            while(sum > 0) {
                                digitalRoot += sum%10;
                                sum /= 10;
                            }

                            while(digitalRoot > 10) {
                                sum = digitalRoot;
                                digitalRoot = 0;

                                while(sum > 0) {
                                    digitalRoot += sum%10;
                                    sum /= 10;
                                }
                            }

                            //finished
                            drOutput.setText("THE DIGITAL ROOT IS: \n" + digitalRoot);


                        }
                        catch (Exception e) {
                            drOutput.setText("ERROR\nPlease enter numbers numbers separated by one space.");
                            Snackbar.make(view, "ERROR", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                        }
                    }
                }
            }
        );

        final TextView BTSOutput = findViewById(R.id.BTSOutput);
        final EditText BTSEdit = findViewById(R.id.BTSEdit);
        //look into "OnEditorAction" for the "Enter" part of the input

        Button BTSButton = findViewById(R.id.BTSButton);
        //Click event for the button
        BTSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String raw = BTSEdit.getText().toString();
                String i = "";
                raw=raw.trim();

                try {
                    i = Long.valueOf(raw,36).toString();
                    BTSOutput.setText("Base 36: " + raw + " -> Base 10: " + i);
                }
                catch (Exception e) {
                    System.out.print("Error was: "+ e);
                    BTSOutput.setText("ERROR\nPlease enter a number in base 36.");
                    Snackbar.make(view, "ERROR", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }
        }
        );

        final TextView BTEOutput = findViewById(R.id.BTEOutput);
        final EditText BTEEdit = findViewById(R.id.BTEEdit);
        //look into "OnEditorAction" for the "Enter" part of the input

        Button BTEButton = findViewById(R.id.BTEButton);
        //Click event for the button
        BTEButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             String raw = BTEEdit.getText().toString();
                                             int i = 0;
                                             raw=raw.trim();

                                             try {
                                                 i = Integer.valueOf(raw,10);
                                                 BTEOutput.setText("Base 10: " + raw + " -> Base 36: " + Integer.toString(i, 36));
                                             }
                                             catch (Exception e) {
                                                 System.out.print("Error was: "+ e);
                                                 BTEOutput.setText("ERROR\nPlease enter a number in base 10.");
                                                 Snackbar.make(view, "ERROR", Snackbar.LENGTH_LONG)
                                                         .setAction("Action", null).show();
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
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=zero+escape+999&oq=zero+escape+999&aqs=chrome..69i57j69i59j0l4.6779j0j7&sourceid=chrome&ie=UTF-8"));
            startActivity(browserIntent);
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

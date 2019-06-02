package com.example.sharedprefernces;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView lang_text ;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if ( item.getItemId() == R.id.english ) {
            setLanguage("English from menu!!");
        }
        else{
            setLanguage("Hindi from menu!!");
        }
        return true;
    }

    public void setLanguage(String language){
        sharedPreferences.edit().putString("language", language).apply();
        lang_text.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lang_text = (TextView) findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.sharedprefernces", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language","");
        if (language == "") {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.btn_star)
                    .setTitle("Choose your language")
                    .setMessage("Choose a language you want to proceed with!!")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("English");
                            //Toast.makeText(MainActivity,"Choosen", LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Hindi");
                        }
                    })
                    .show();
        }
        else{
            lang_text.setText(language);
        }

    }
}

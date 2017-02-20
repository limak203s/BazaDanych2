package com.example.majkel.bazadanych;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BazaDbHelper bazaDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bazaDBHelper = new BazaDbHelper(this);
    }


    public void zapiszDoBazy(View view) {

        EditText et = (EditText) findViewById(R.id.et);
        String pole_tekstowe = et.getText().toString();
        Log.d("Elo:", pole_tekstowe);
        SQLiteDatabase db = bazaDBHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BazaDbHelper.Baza.TEKST, pole_tekstowe);

       long id = db.insert(BazaDbHelper.Baza.TABLE_NAME, null, cv);
        Log.d("My activity", "Wstawiony wiersz to: " + id);
    }



    public void odczytajZBazy(View view) {

        SQLiteDatabase db = bazaDBHelper.getReadableDatabase();

        String[] projekcja = {
                BazaDbHelper.Baza._ID,
                BazaDbHelper.Baza.TEKST
        };

        Cursor c = db.query(
                BazaDbHelper.Baza.TABLE_NAME,
                new String[] {BazaDbHelper.Baza.TEKST},
                null,
                null,
                null,
                null,
                null);

      c.moveToLast();
           String tekst = c.getString(c.getColumnIndex(BazaDbHelper.Baza.TEKST));
           TextView tv = (TextView) findViewById(R.id.tv);
           tv.setText(tekst);
           Log.d("Moj log:", c.getPosition() + " " + tekst);



       // TextView tv = (TextView) findViewById(R.id.tv);
        //tv.setText(tekst);
       // tv.setText("Mój tekst testowy");

      // finish();
      //  startActivity(getIntent());

    }

    public void zerowanieBazy(View view) {
        SQLiteDatabase db = bazaDBHelper.getWritableDatabase();
        db.delete(BazaDbHelper.Baza.TABLE_NAME,null, null);
    }

}

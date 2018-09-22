package example.com.fileapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etDisp,etStat;
    Button bread,bwrite;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDisp=findViewById(R.id.editText);
        etStat=findViewById(R.id.editText2);
        bread=findViewById(R.id.button2);
        bwrite=findViewById(R.id.button);

        bread.setOnClickListener(this);
        bwrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==bwrite)
        {
            writeFile();
        }

        if(view==bread)
        {
            readFile();
        }
    }

    public void writeFile(){
        s=etDisp.getText().toString();

        String filename="data.txt";
        try{
            FileOutputStream fos=openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(s.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("Exception ",e.toString());
        }
        etDisp.setText("");
        Toast.makeText(getApplicationContext(),"write"+getFilesDir()+getDir(filename,Context.MODE_PRIVATE),Toast.LENGTH_LONG).show();
    }

    public void readFile(){

        String filename="data.txt";
        String d="";
        try{
            FileInputStream fis=openFileInput(filename);
            byte[] b=new byte[fis.available()];
            int c=fis.available();
            fis.skip(2);
            if(fis.read(b)!= -1)
            {
                d=new String(b);
            }
            fis.close();
            etDisp.setText(d);
            Toast.makeText(getApplicationContext(),"read"+c,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("Exception ",e.toString());
        }
    }

    public void statShow(View v){
        Resources myres=getResources();
        InputStream isr=myres.openRawResource(R.raw.aa);
        String st="";
        try {
         byte[] b=new byte[isr.available()];

            if(isr.read(b)!= -1)
            {
                st=new String(b);
            }
            etStat.setText(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extPage(View v){
        Intent in1=new Intent(MainActivity.this,extActivity.class);
        startActivity(in1);
    }
}

package example.com.fileapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class extActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et1;
    Button bwrite,bread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext);

        et1=findViewById(R.id.editText3);
        bwrite=findViewById(R.id.button6);
        bread=findViewById(R.id.button7);

        bwrite.setOnClickListener(this);
        bread.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        String st= Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(st))
        {
            Toast.makeText(getApplicationContext(),"mounted"+Environment.getExternalStorageDirectory(),Toast.LENGTH_LONG).show();
        }


        if(view==bwrite){
            writetofile();
        }
        if(view==bread){
            readfromfile();
        }
    }

    public void writetofile(){

        String filename="myfile.txt";
        String s=et1.getText().toString();
        try {
            FileOutputStream fos=new FileOutputStream(Environment.getExternalStorageDirectory()+"//"+filename);
            fos.write(s.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readfromfile(){

        String filename="myfile.txt",d="";
        try {
        FileInputStream fis=new FileInputStream(Environment.getExternalStorageDirectory()+"//"+"myfile.txt");
        byte[] b=new byte[fis.available()];
        if(fis.read(b)!= -1)
        {
            d=new String(b);
        }
            Toast.makeText(getApplicationContext(),"read"+d,Toast.LENGTH_LONG).show();
        et1.setText(d);
        fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

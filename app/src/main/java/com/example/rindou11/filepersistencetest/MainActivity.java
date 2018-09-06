package com.example.rindou11.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.save_edit_text);

        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"Reloaded successed.",Toast.LENGTH_LONG).show();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        String save_text= edit.getText().toString();
        save(save_text);
    }

    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();

        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(reader!=null){
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public void save(String inputText){

        FileOutputStream out=null;
        BufferedWriter write=null;

        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            write=new BufferedWriter(new OutputStreamWriter(out));
            write.write(inputText);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if (write!=null){
                    write.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

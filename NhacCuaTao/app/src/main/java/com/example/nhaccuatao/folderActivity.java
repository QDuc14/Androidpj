package com.example.nhaccuatao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class folderActivity extends AppCompatActivity {

    ListView lview;
    private ArrayList<Folder> fileList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        lview = (ListView) findViewById(R.id.lview1);
        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        ListDir(root);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("nameFolder",fileList.get(position).getName());
                startActivity(intent);
                Toast.makeText(folderActivity.this,fileList.get(position).getPath(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    void ListDir(File f){
        File[] files=f.listFiles();
        fileList.clear();
        for (File file: files){
            fileList.add(new Folder(file.getName(),file.getPath()));

        }

        FolderAdapter directoryList=new FolderAdapter(folderActivity.this,R.layout.dong_folder,fileList);
        lview.setAdapter(directoryList);
    }
}
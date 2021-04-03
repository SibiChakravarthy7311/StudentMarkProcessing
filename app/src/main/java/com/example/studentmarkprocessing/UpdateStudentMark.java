package com.example.studentmarkprocessing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudentMark extends AppCompatActivity {
    ListView listView;
    RadioGroup rg;
    EditText rollno,mark;
    Button btn_add;
    RadioButton rb;
    ArrayList<ListviewItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_mark);

//        List View Code
        ListviewItem item = new ListviewItem("18bcs009","amal","1","2","3","4");
        list.add(item);
        ListviewItem item1 = new ListviewItem("18bcs013","dhinesh","1","2","3","4");
        list.add(item1);
        ListviewItem item2 = new ListviewItem("18bcs001","sibi","1","2","3","4");
        list.add(item2);

        listView=(ListView) findViewById(R.id.listview);
        customListAdapter adapter=new customListAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adapter);


//        adding new values
        rg = (RadioGroup) findViewById(R.id.exam_type);
        rollno = (EditText) findViewById(R.id.roll_no);
        mark = (EditText) findViewById(R.id.mark);
        btn_add = (Button) findViewById(R.id.btn_add);

        int id = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(id);
        String type = rb.getText().toString();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Add values to databse
            }
        });
    }
}
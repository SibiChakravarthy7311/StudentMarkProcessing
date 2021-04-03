package com.example.studentmarkprocessing;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudentMark extends AppCompatActivity {
    ListView listView;
    RadioGroup rg;
    EditText rollno,mark;
    Button btn_add;
    RadioButton rb;
    ArrayList<ListviewItem> list = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_mark);

        String id=getIntent().getStringExtra("Id");

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("StaffDetails").child(id);
        listView=(ListView) findViewById(R.id.listview);

//        List View Code
//        ListviewItem item = new ListviewItem("18bcs009","amal","1","2","3","4");
//        list.add(item);
//        ListviewItem item1 = new ListviewItem("18bcs013","dhinesh","1","2","3","4");
//        list.add(item1);
//        ListviewItem item2 = new ListviewItem("18bcs001","sibi","1","2","3","4");
//        list.add(item2);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.child("CSEA").getChildren()){
                    String rollno=ds.child("Rollno").getValue(String.class);
                    String name=ds.child("Name").getValue(String.class);
                    String ccet1=ds.child("C1").getValue(String.class);
                    String ccet2=ds.child("C2").getValue(String.class);
                    String ccet3=ds.child("C3").getValue(String.class);
                    String sem="VI";
                    ListviewItem item=new ListviewItem(rollno,name,ccet1,ccet2,ccet3,sem);
                    list.add(item);
                }
                customListAdapter adapter=new customListAdapter(UpdateStudentMark.this,R.layout.list_item,list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        adding new values
        rg = (RadioGroup) findViewById(R.id.exam_type);
        rollno = (EditText) findViewById(R.id.roll_no);
        mark = (EditText) findViewById(R.id.mark);
        btn_add = (Button) findViewById(R.id.btn_add);

        int ids = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(ids);
        //String type = rb.getText().toString();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Add values to databse
            }
        });
    }
}
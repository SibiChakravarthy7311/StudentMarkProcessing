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
import android.widget.Toast;

import com.example.studentmarkprocessing.databinding.ActivityUpdateStudentMarkBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudentMark extends AppCompatActivity {

    private ActivityUpdateStudentMarkBinding binding;

    ListView listView;
    ArrayList<ListviewItem> list = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_mark);
        binding=ActivityUpdateStudentMarkBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        String id=getIntent().getStringExtra("Id");

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("StaffDetails").child(id);
        listView=(ListView) findViewById(R.id.listview);

//        List View Code
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


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref=ref.child("CSEA").child(binding.rollNo.getText().toString());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            action();
                        }else{
                            binding.rollNo.setError("Rollno not found!!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            private void action() {
                try{
                    int ids=binding.examType.getCheckedRadioButtonId();
                    RadioButton rb=findViewById(ids);
                    String select=rb.getText().toString();
                    String Mark=binding.mark.getText().toString();

                    if(binding.rollNo.getText().toString().length()>1){
                        if(Integer.parseInt(Mark)>0 && Integer.parseInt(Mark)<51){
                            if(select.equals("CCET 1")){ref.child("C1").setValue(binding.mark.getText().toString());}
                            else if(select.equals("CCET 2")){ref.child("C2").setValue(binding.mark.getText().toString());}
                            else{ref.child("C3").setValue(binding.mark.getText().toString());}
                            Toast.makeText(UpdateStudentMark.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            binding.mark.setError("Mark not in range");
                        }
                    }else{binding.rollNo.setError("Enter the rollno");}
                }catch (Exception e){
                    Toast.makeText(UpdateStudentMark.this, "Please select the exam type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
package com.example.moustafashahin.realtime_fb_test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moustafashahin.realtime_fb_test.Adapter.Adapter;
import com.example.moustafashahin.realtime_fb_test.Model.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText text,contexttext;
Button  send;
RecyclerView rv;
DatabaseReference dref;
FirebaseDatabase fbdb;
    FirebaseRecyclerAdapter<Post,Adapter> adapter;
    FirebaseRecyclerOptions<Post> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.testtext);

        contexttext = findViewById(R.id.testcontxt);
        send = findViewById(R.id.send);
        rv = findViewById(R.id.recv);
        rv.setLayoutManager(new LinearLayoutManager(this) );
        fbdb = FirebaseDatabase.getInstance();
        dref = fbdb.getReference("MY_db");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //setRv();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpost();
            }
        });

setRv();
    }

    @Override
    protected void onStop() {
        super.onStop();
    if(adapter!= null)
        adapter.stopListening();
    }

    public void getpost(){
        String title = text.getText().toString();
        String body = contexttext.getText().toString();
        Post post = new Post(title,body);
        dref.push().setValue(post);
        adapter.notifyDataSetChanged();

    }
    public void setRv() {
       options = new FirebaseRecyclerOptions.Builder<Post>().setQuery(dref,Post.class).build();
       adapter = new FirebaseRecyclerAdapter<Post, Adapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Adapter holder, int position, @NonNull Post model) {
                holder.title.setText(model.getTitle());
                holder.body.setText(model.getBody());
            }

            @NonNull
            @Override
            public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View item_data = LayoutInflater.from(MainActivity.this).inflate(R.layout.row_data,parent,false);
                return new Adapter(item_data);
            }
        };
        adapter.startListening();
        rv.setAdapter(adapter);
    }

}

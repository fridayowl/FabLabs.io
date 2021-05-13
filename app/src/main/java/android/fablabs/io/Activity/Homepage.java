package android.fablabs.io.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.fablabs.io.R;
import android.fablabs.io.databinding.ActivityHomepageBinding;
import android.fablabs.io.listener.HistoryItemClickListener;
import android.fablabs.io.model.Myhistorymodel;
import android.os.Bundle;
import  android.fablabs.io.adapter.homepageadapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Homepage extends AppCompatActivity implements HistoryItemClickListener {
   ActivityHomepageBinding binding;
    private ArrayList<Myhistorymodel>_models;
    private  homepageadapter _adapter;
    FirebaseDatabase DB;
    DatabaseReference ROOT;
    private FirebaseAuth mAuth;
    SharedPreferences sharedpreference;
    private Context mcontext;
    int count =0;
    int ch =0;
    List<String> lables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(getApplicationContext());
        DB = FirebaseDatabase.getInstance();
        ROOT = DB.getReference();

        binding.rvCourses.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        );
        binding.rvCourses.setClipToPadding(false);
        binding.rvCourses.setHasFixedSize(true);
        _models = new ArrayList<>();
        sharedpreference = getSharedPreferences(Variables.DEFAUlTDATA, Context.MODE_PRIVATE);
        String key = sharedpreference.getString(Variables.USERID,"null");

        ROOT.child(Variables.USERSORDERDB).child(key).child("HISTORY").addValueEventListener(new ValueEventListener() {
            @Override/**/
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("LOP",snapshot.toString());
                for (DataSnapshot ds : snapshot.getChildren()){

                    ch++;
                    try {
                        String date = ds.getKey();
                        date = date.substring(0,10);
                        Log.d("LOP",date);
                        String headnode= date;
                        date=date.replaceAll("_","/");

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date strDate = sdf.parse(date);
                        if (new Date().before(strDate)) {
                            //  //Toast.makeText(CartActivity.this, "outdates"+ databasedate, //Toast.LENGTH_SHORT).show();
                            _models.add(new Myhistorymodel(date,12,R.drawable.background_color_circle_selector,"upcoming",headnode,ds.getValue().toString()));

                        } else {
                            _models.add(new Myhistorymodel(date,12,R.drawable.background_color_circle_selector,"ended",headnode,ds.getValue().toString()));
                        }
                    }catch (Exception e){}

                    if(ch== snapshot.getChildrenCount())
                    {
                        _adapter = new homepageadapter(mcontext,_models,Homepage.this);
                        binding.rvCourses.setAdapter(_adapter);
                        binding.loadinglayout.setVisibility(View.GONE);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      //  _models.add(new Myhistorymodel("10/12/2020",12,R.drawable.background_color_circle_selector,"upcoming"));
  /*      _models.add(new Myhistorymodel("10/12/2020",12,R.drawable.background_color_circle_selector,"upcoming"));
        _models.add(new Myhistorymodel("10/12/2020",12,R.drawable.background_color_circle_selector,"upcoming"));
        _models.add(new Myhistorymodel("10/12/2020",12,R.drawable.background_color_circle_selector,"upcoming"));*/
        //binding.rvCourses.setAdapter(_adapter);

        binding.btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in  = new Intent(Homepage.this,List_of_Data.class);
                startActivity(in);
            }
        });

    }

    @Override
    public void onItemClicked(Myhistorymodel myhistorymodel) {
        List<String> lables = new ArrayList<>();
        //Toast.makeText(Homepage.this,String.valueOf(myhistorymodel.getHeadnode()),//Toast.LENGTH_LONG).show();
        Log.d("LOP",myhistorymodel.getChildnode()+myhistorymodel.getHeadnode() + Variables.ORDERDB);
        ROOT.child(Variables.ORDERDB).child(myhistorymodel.getHeadnode()).child(myhistorymodel.getChildnode()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren())
                {
                    count ++;
                    ROOT.child(Variables.ORDERDB).child(myhistorymodel.getHeadnode()).child(myhistorymodel.getChildnode()).child(ds.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren())
                            {
                                Log.d("LOP",ds.getKey());
                                lables.add(ds.getKey());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                if(count >= snapshot.getChildrenCount())
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this, R.style.MyDialogTheme);
                    builder.setTitle("Selected Items");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Homepage.this,
                            android.R.layout.simple_dropdown_item_1line, lables);
                    builder.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                         //   Toast.makeText(Homepage.this,"You have selected " + lables.get(which),Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
package android.fablabs.io.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.fablabs.io.R;
import android.fablabs.io.databinding.ActivityCartBinding;
import android.fablabs.io.utils.DrawableUtils;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    String selecteddate = "false";
    boolean datestatus=true;
    FirebaseDatabase DB;
    DatabaseReference ROOT,Item1,Item2,Item3,Item4;
    String databasedate="null";
    private FirebaseAuth mAuth;
    String Userid="null";
    int progressbar=0;
    ProgressDialog progressDialog;
    SharedPreferences sharedpreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar2.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(getApplicationContext());
        DB = FirebaseDatabase.getInstance();
        ROOT = DB.getReference();
        sharedpreference = getSharedPreferences(Variables.DEFAUlTDATA, Context.MODE_PRIVATE);
        String key = sharedpreference.getString(Variables.USERID,"null");
        ////Toast.makeText(CartActivity.this, key, ////Toast.LENGTH_SHORT).show();
        List<EventDay> events = new ArrayList<>();
      //  Log.d("GRAPHVALUES","k");
        /*for (int lm = 0 ; lm<Variables.selectedList1.size();lm++)
        {
            Log.d("LOP",String.valueOf(lm));
            Log.d("LOP",String.valueOf(Variables.selectedList1.get(lm)));
        }*/

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(CartActivity.this, "8")));
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(@NotNull EventDay eventDay) {

                //////Toast.makeText(getApplicationContext(),String.format("%02d", eventDay.getCalendar().getTime().getDay())+"/"+ String.format("%02d", eventDay.getCalendar().getTime().getMonth() + 1)+"/"+ "2021", ////Toast.LENGTH_SHORT).show();
                selecteddate =  String.format("%02d", eventDay.getCalendar().get(Calendar.DAY_OF_MONTH)  )+"/"+  String.format("%02d", eventDay.getCalendar().get(Calendar.MONTH)+1)+"/"+ eventDay.getCalendar().get(Calendar.YEAR);
                binding.fitonedesc.setText("Selected Date :" + selecteddate);
                Variables.dateselected = selecteddate;
                databasedate =  String.format("%02d", eventDay.getCalendar().get(Calendar.DAY_OF_MONTH) )+"_"+ String.format("%02d", eventDay.getCalendar().get(Calendar.MONTH)+1)+"_"+ eventDay.getCalendar().get(Calendar.YEAR);
                Variables.DATABASEID = databasedate;

                try {
                   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                   Date strDate = sdf.parse(selecteddate);
                   if (new Date().after(strDate)) {
                     //  ////Toast.makeText(CartActivity.this, "outdates"+ databasedate, ////Toast.LENGTH_SHORT).show();
                       binding.fitonedesc.setText("Selected Date :! Select future date");

                       datestatus =false;
                   } else {
                        binding.fitonedesc.setText("Selected Date :" + selecteddate);

                       datestatus = true;
                   }
               }catch (Exception e){}
            }
        });

     binding.bgprogress.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent in = new Intent(CartActivity.this,Homepage.class);
             startActivity(in);
         }
     });
        binding.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CartActivity.this,Homepage.class);
                startActivity(in);
            }
        });
        Log.d("Cart Activty ",String.valueOf(Variables.selectedList1.size()));
        ////Toast.makeText(CartActivity.this,String.valueOf(Variables.selectedList1.size()),////Toast.LENGTH_LONG).show();
        binding.ibvIcon1.setBadgeValue(Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() + Variables.selectedList4.size());




        binding.proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                 if(!selecteddate.equals("false"))
                 {

                     Log.d("LOP",databasedate);
                     selecteddate = "false";
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh-mm-ss");
                     String format = simpleDateFormat.format(new Date());
                     String key  =  format ;
                     SharedPreferences sharedpreference;
                     sharedpreference = getSharedPreferences(Variables.DEFAUlTDATA, Context.MODE_PRIVATE);
                     String userid = sharedpreference.getString(Variables.USERID,"null");
                     ROOT.child(Variables.USERSORDERDB).child(userid).child("HISTORY").child(databasedate+key).setValue(key);
                     ////Toast.makeText(CartActivity.this, key, ////Toast.LENGTH_SHORT).show();
                     Log.d("LOP",key);
                     Variables.SELECTEDDATE= key;
                     for (int i = 0 ; i< Variables.selectedList1.size();i++)
                     {
                         // Display Progress Dialog
                         binding.progressBar2.setVisibility(View.VISIBLE);
                           Log.d("LOP",String.valueOf(Variables.selectedList1.get(i)));
                           ROOT.child(Variables.ORDERDB).child(databasedate).child(key).child(Variables.ICONSUMABLE).child(Variables.selectedList1.get(i)).setValue("1").addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   binding.progressBar2.setVisibility(View.INVISIBLE);
                                   progressbar++;
                                   if(progressbar >= Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() +Variables.selectedList4.size() )
                                   {
                                       Intent in  = new Intent(CartActivity.this,WhatAppActivity.class);
                                       startActivity(in);
                                       SharedPreferences.Editor editor =  sharedpreference.edit();
                                       editor.putString(Variables.FLOW,"HASHISTORY");
                                       editor.commit();
                                   }

                               }
                           });


                     }
                     for (int j = 0 ; j< Variables.selectedList2.size();j++)
                      {
                          binding.progressBar2.setVisibility(View.VISIBLE);
                          Log.d("LOP",String.valueOf(Variables.selectedList2.get(j)));
                          ROOT.child(Variables.ORDERDB).child(databasedate).child(key).child(Variables.ITOOLS).child(Variables.selectedList2.get(j)).setValue("1").addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void aVoid) {
                                 binding.progressBar2.setVisibility(View.INVISIBLE);
                                 progressbar++;
                                  if(progressbar >= Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() +Variables.selectedList4.size() )
                                  {
                                      Intent in  = new Intent(CartActivity.this,WhatAppActivity.class);
                                      startActivity(in);
                                      SharedPreferences.Editor editor4 =  sharedpreference.edit();
                                      editor4.putString(Variables.FLOW,"HASHISTORY");
                                      editor4.commit();
                                  }
                              }
                          });


                     }
                     for (int k = 0 ; k< Variables.selectedList3.size();k++)
                     {
                         binding.progressBar2.setVisibility(View.VISIBLE);
                         Log.d("LOP",String.valueOf(Variables.selectedList3.get(k)));
                         ROOT.child(Variables.ORDERDB).child(databasedate).child(key).child(Variables.IMACHINES).child(Variables.selectedList3.get(k)).setValue("1") .addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 binding.progressBar2.setVisibility(View.INVISIBLE);
                                 progressbar++;
                                 if(progressbar >= Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() +Variables.selectedList4.size() )
                                 {
                                     Intent in  = new Intent(CartActivity.this,WhatAppActivity.class);
                                     startActivity(in);
                                     SharedPreferences.Editor editor2 =  sharedpreference.edit();
                                     editor2.putString(Variables.FLOW,"HASHISTORY");
                                     editor2.commit();
                                 }
                             }
                         });



                     }
                     for (int f = 0 ;f< Variables.selectedList4.size();f++)
                     {
                         binding.progressBar2.setVisibility(View.VISIBLE);
                         Log.d("LOP",String.valueOf(Variables.selectedList4.get(f)));

                             ROOT.child(Variables.ORDERDB).child(databasedate).child(key).child(Variables.MAKERS).child(Variables.selectedList4.get(f)).setValue("1").addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {
                                     Log.d("LOP","success");
                                     binding.progressBar2.setVisibility(View.INVISIBLE);
                                     progressbar++;
                                     if(progressbar >= Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() +Variables.selectedList4.size() )
                                     {
                                         Intent in  = new Intent(CartActivity.this,WhatAppActivity.class);
                                         startActivity(in);
                                         SharedPreferences.Editor editor3 =  sharedpreference.edit();
                                         editor3.putString(Variables.FLOW,"HASHISTORY");
                                         editor3.commit();

                                     }
                                 }
                             });

                     }




                 }
            }
        });


       /* binding.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progressbar >= Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() +Variables.selectedList4.size() )
                {
                    Intent in  = new Intent(CartActivity.this,Homepage.class);
                    startActivity(in);
                }
            }
        });
*/

    }



}
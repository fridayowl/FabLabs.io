package android.fablabs.io.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.fablabs.io.databinding.ActivityListOfDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.barisatalay.filterdialog.FilterDialog;
import com.barisatalay.filterdialog.model.DialogListener;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.ArrayList;
import java.util.List;

public class List_of_Data extends AppCompatActivity {
   ActivityListOfDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListOfDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bgprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(List_of_Data.this,CartActivity.class);
                startActivity(in);
            }
        });
        binding.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(List_of_Data.this,CartActivity.class);
                startActivity(in);
            }
        });
        List<String> stringList = new ArrayList<>();
        List<String> stringList1 = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();
        List<String> stringList3 = new ArrayList<>();





        stringList.add("Resistors");
        stringList.add("Capacitor");
        stringList.add("ICs ");
        stringList.add("Tranformers");
        stringList.add("Relay modules");
        stringList.add("Arduino Development boards");
        stringList.add("Wifi modules");

        stringList1.add("Soldering iron");
        stringList1.add("De-soldering pump");
        stringList1.add("Hot air gun");
        stringList1.add("Glue gun");
        stringList1.add("Vernier Caliper");
        stringList1.add("Digital Multimeter");
        stringList1.add("Screw driver kit");

        stringList2.add("Laser cut machine");
        stringList2.add("CNC machine");
        stringList2.add("Portable Water Jet machine");
        stringList2.add("3D Printing");
        stringList2.add("CNC Lathe machine ");
        stringList2.add("Wire cut machine");

        stringList3.add("Mr Richard");
        stringList3.add("Mr Nerzgul Salih");
        stringList3.add("Mr Sreedhar");
        stringList3.add("Ms Vidyashree L");
        stringList3.add("Ms Harshitha R");



        /*filterDialog.show(new DialogListener.Single() {
            @Override
            public void onResult(FilterItem selectedItem) {
                Toast.makeText(List_of_Data.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                //filterDialog.dispose();
            }
        });*/


        binding.fitone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog filterDialog = new FilterDialog(List_of_Data.this);
                filterDialog.setToolbarTitle("String Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setList(stringList);
                filterDialog.backPressedEnabled(false);


                filterDialog.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterDialog.dispose();
                    }
                });
                filterDialog.setToolbarTitle(" ");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setSelectButtonText("Select");
                filterDialog.setSelectableCount(200);
                filterDialog.show("Otel", "Adi", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItems) {
                        Variables.selectedList1.clear();
                        for (int i=0;i<selectedItems.size();i++) {
                            Variables.selectedList1.add(selectedItems.get(i).getName());
                          // Toast.makeText(List_of_Data.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                        binding.ibvIcon1.setBadgeValue(selectedItems.size());
                        filterDialog.dispose();
                    }
                });

            }
        });


        binding.fittwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog filterDialog1 = new FilterDialog(List_of_Data.this);
                filterDialog1.setToolbarTitle("String Filter");
                filterDialog1.setSearchBoxHint("You can search");
                filterDialog1.setList(stringList1);
                filterDialog1.backPressedEnabled(false);


                filterDialog1.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterDialog1.dispose();
                    }
                });
                filterDialog1.setToolbarTitle(" ");
                filterDialog1.setSearchBoxHint("You can search");
                filterDialog1.setSelectButtonText("Select");
                filterDialog1.setSelectableCount(200);
                filterDialog1.show("Otel", "Adi", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItems) {
                        for (int i=0;i<selectedItems.size();i++) {
                            Variables.selectedList2.add(selectedItems.get(i).getName());
                          //  Toast.makeText(List_of_Data.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                        binding.ibvIcon2.setBadgeValue(selectedItems.size());

                        filterDialog1.dispose();
                    }
                });
            }
        });

        binding.fitthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog filterDialog2 = new FilterDialog(List_of_Data.this);
                filterDialog2.setToolbarTitle("String Filter");
                filterDialog2.setSearchBoxHint("You can search");
                filterDialog2.setList(stringList2);
                filterDialog2.backPressedEnabled(false);


                filterDialog2.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        filterDialog2.dispose();
                    }
                });
                filterDialog2.setToolbarTitle(" ");
                filterDialog2.setSearchBoxHint("You can search");
                filterDialog2.setSelectButtonText("Select");
                filterDialog2.setSelectableCount(200);
                filterDialog2.show("Otel", "Adi", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItemsk) {
                        for (int i=0;i<selectedItemsk.size();i++) {
                            Variables.selectedList3.add(selectedItemsk.get(i).getName());
                            //Toast.makeText(List_of_Data.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                        binding.ibvIcon3.setBadgeValue(selectedItemsk.size());

                        filterDialog2.dispose();
                    }
                });
            }
        });
        binding.fitfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog filterDialog3 = new FilterDialog(List_of_Data.this);
                filterDialog3.setToolbarTitle("String Filter");
                filterDialog3.setSearchBoxHint("You can search");
                filterDialog3.setList(stringList3);
                filterDialog3.backPressedEnabled(false);


                filterDialog3.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterDialog3.dispose();
                    }
                });
                filterDialog3.setToolbarTitle(" ");
                filterDialog3.setSearchBoxHint("You can search");
                filterDialog3.setSelectButtonText("Select");
                filterDialog3.setSelectableCount(200);
                filterDialog3.show("Otel", "Adi", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItems) {
                        for (int i=0;i<selectedItems.size();i++) {
                            Variables.selectedList4.add(selectedItems.get(i).getName());
                            //Toast.makeText(List_of_Data.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                        binding.ibvIcon4.setBadgeValue(selectedItems.size());

                        filterDialog3.dispose();
                    }
                });
            }
        });




    }





}
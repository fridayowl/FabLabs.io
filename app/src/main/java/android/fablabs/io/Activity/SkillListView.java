package android.fablabs.io.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.fablabs.io.R;
import android.fablabs.io.adapter.ExpListViewAdapterWithCheckbox;
import android.fablabs.io.adapter.skillsetcustomadapter;
import android.fablabs.io.databinding.ActivitySkillListViewBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillListView extends AppCompatActivity {
    ActivitySkillListViewBinding binding;
    //  ExpandableListAdapter listAdapter;
    ExpListViewAdapterWithCheckbox listAdapter;

    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> listSearchDataHeader;
    HashMap<String, List<String>> listSearchDataChild;
    HashMap<Integer, boolean[]> defaultmcheckstates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySkillListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // get the listview


        // preparing list data
        prepareListData();

        listAdapter = new ExpListViewAdapterWithCheckbox(this, listDataHeader, listDataChild);

        // setting list adapter
        binding.evList.setAdapter(listAdapter);

        // Listview Group click listener
        binding.evList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
       binding.evList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        binding.evList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        binding.evList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

//        Button button = (Button) findViewById(R.id.button);
//        final TextView textView = (TextView) findViewById(R.id.textView);
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                int count =0;
//                for(int mGroupPosition =0; mGroupPosition < listAdapter.getGroupCount(); mGroupPosition++)
//                {
//                    count = count +  listAdapter.getNumberOfCheckedItemsInGroup(mGroupPosition);
//                }
//                textView.setText(""+count);
//            }
//        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listSearchDataHeader  = new ArrayList<String>();
                listSearchDataChild= new HashMap<String, List<String>>();
                defaultmcheckstates=new HashMap<Integer, boolean[]>();
                defaultmcheckstates=listAdapter.getmChildCheckStates();
                if (binding.etSearch.getText().toString().length() == 0) {
                    listAdapter = new ExpListViewAdapterWithCheckbox(getApplicationContext(), listDataHeader, listDataChild,defaultmcheckstates);

                    binding.evList.setAdapter(listAdapter);
                }else {

//                    Log.d("Search", "#"+String.valueOf(listDataChild.get(listDataHeader.get(1)).get(1)));
//                    Log.d("Search", String.valueOf(listDataChild.get(listDataHeader.get(1)).size()));
//                    Log.d("Search", String.valueOf(listDataChild.get(listDataHeader.get(2)).size()));
//                     Log.d("Search", String.valueOf(listDataHeader.size()));
//                    Log.d("Search", String.valueOf(listDataHeader.get(0)));
                    int number_ofresult=0;
                    for (int i = 0; i < listDataHeader.size(); i++) {
                        if(listDataHeader.get(i).toLowerCase().contains(binding.etSearch.getText().toString()))
                        {
                            Log.d("Search","Found!!");
                            listSearchDataHeader.add(listDataHeader.get(i));
                            List<String> top250 = new ArrayList<String>();
                            for(int j =0 ;j< listDataChild.get(listDataHeader.get(i)).size();j++){
                                Log.d("Search",listDataChild.get(listDataHeader.get(i)).get(j));
                                top250.add(listDataChild.get(listDataHeader.get(i)).get(j));
                            }

                            listSearchDataChild.put(listSearchDataHeader.get(number_ofresult), top250);
                            number_ofresult++;


                        }

                    }
                    listAdapter = new ExpListViewAdapterWithCheckbox(getApplicationContext(), listSearchDataHeader, listSearchDataChild,defaultmcheckstates);

                    // setting list adapter
                    binding.evList.setAdapter(listAdapter);
                }
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Project management and Web design");
        listDataHeader.add("Computer-aided design");
        listDataHeader.add("Computer-controlled cutting");
        listDataHeader.add("Electronics production");
        listDataHeader.add("Electronics design");
        listDataHeader.add("Computer-controlled machining");
        listDataHeader.add("Embedded programming");
        listDataHeader.add("Mechanical and machine design");
        listDataHeader.add("Input and output devices");
        listDataHeader.add("Molding and casting");
        listDataHeader.add("Networking and communications");
        listDataHeader.add("Interface and application programming");
        listDataHeader.add("UI and UX Design");
        listDataHeader.add("Invention, intellectual property, and income");
        listDataHeader.add("Scale-up and production");
        listDataHeader.add("Project Presentation and documentation");


        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("name1");
        top250.add("name2");
        top250.add("name3");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

         for(int i=0;i<=15;i++) {
             listDataChild.put(listDataHeader.get(i), top250); // Header, Child data
         }
    }
}
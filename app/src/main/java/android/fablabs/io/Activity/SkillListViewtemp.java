package android.fablabs.io.Activity;

import android.fablabs.io.adapter.skillsetcustomadapter;
import android.fablabs.io.databinding.ActivitySkillListViewBinding;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillListViewtemp extends AppCompatActivity {
     ActivitySkillListViewBinding binding;
     List<HashMap<String,Object>> lv_skillset = new ArrayList<>();
     skillsetcustomadapter skillsetcustomadapter;
     private final int lastExpandedPos =-1;
     ArrayList<String> abc = new ArrayList<>();
     View previousview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySkillListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        abc.add(String.valueOf(01));

        init();

    }

    private  void init(){
        fn_arraylist();
        skillsetcustomadapter = new skillsetcustomadapter(lv_skillset,getApplicationContext());
        binding.evList.setAdapter(skillsetcustomadapter);
        binding.evList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true; // This way the expander cannot be collapsed
            }
        });
        for (int i = 0; i < skillsetcustomadapter.getGroupCount(); i++) {
            binding.evList.expandGroup(i);
        }
//       binding.evList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                if(binding.evList.isGroupExpanded(groupPosition))
//                {
//                    binding.evList.collapseGroup(groupPosition);
//                }else
//                {
//                    binding.evList.expandGroup(groupPosition);
//                }
//
//                return true; // This way the expander cannot be collapsed
//            }
//        });
//       binding.evList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//           @Override
//           public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
////              int index = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(i,i1));
////              expandableListView.setItemChecked(index,true);
//////
////               Object obj = expandableListView.getTag();
////               if(obj instanceof View)
////               {
////                   ((View)obj ).findViewById(R.id.rtl).setBackgroundColor(Color.RED);
////               }
////               view.findViewById(R.id.rtl).setBackgroundColor(Color.RED);
////               expandableListView.setTag(view);
////               Toast.makeText(SkillListView.this,String.valueOf(i)+String.valueOf(i1),Toast.LENGTH_LONG).show();
//               CheckBox cb = (CheckBox)view.findViewById( R.id.check1 );
//               if( cb != null )
//                   cb.toggle();
//               return false;
//           }
//       });


        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.etSearch.getText().toString().length() == 0) {
                    skillsetcustomadapter = new skillsetcustomadapter(lv_skillset, getApplicationContext());
                    binding.evList.setAdapter(skillsetcustomadapter);

                    for (int i = 0; i < skillsetcustomadapter.getGroupCount(); i++) {
                        binding.evList.expandGroup(i);
                    }
                } else{

                    List<HashMap<String, Object>> lv_search = new ArrayList<>();

                    for (int i = 0; i < lv_skillset.size(); i++) {
                        List<HashMap<String, Object>> lv_searchstate = new ArrayList<>();

                        List<HashMap<String, Object>> lv_state = (List<HashMap<String, Object>>) lv_skillset.get(i).get("State");
                        for (int j = 0; j < lv_state.size(); j++) {
                            if (lv_state.get(j).get("Name").toString().toLowerCase().contains(binding.etSearch.getText().toString()) ||lv_skillset.get(i).get("Name").toString().toLowerCase().contains(binding.etSearch.getText().toString()) ) {
                                lv_searchstate.add(lv_state.get(j));
                            }
                        }

                        if (lv_searchstate.size() != 0) {
                            HashMap<String, Object> hashMap_search = new HashMap<>();
                            hashMap_search.put("Name", lv_skillset.get(i).get("Name").toString());
                            hashMap_search.put("State", lv_searchstate);

                            lv_search.add(hashMap_search);
                        }
                    }
                    skillsetcustomadapter = new skillsetcustomadapter(lv_search, getApplicationContext());
                    binding.evList.setAdapter(skillsetcustomadapter);

                    for (int i = 0; i < skillsetcustomadapter.getGroupCount(); i++) {
                        binding.evList.expandGroup(i);
                    }

                }
            }
        });

    }


    private  void  fn_arraylist(){
        HashMap<String, Object> hashMap_country = new HashMap<>();
        hashMap_country.put("Name", "Afghanistan");

        List<HashMap<String, Object>> lv_state = new ArrayList<>();
        HashMap<String, Object> hashMap_state = new HashMap<>();


        hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);

        hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);
        hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);





        hashMap_state = new HashMap<>();
        hashMap_country.put("State", lv_state);

        lv_skillset.add(hashMap_country);

        /*India*/
        hashMap_country = new HashMap<>();
        hashMap_country.put("Name", "India");

        lv_state = new ArrayList<>();
        hashMap_state = new HashMap<>();
        hashMap_state.put("Name", "Arunachal Pradesh");
        lv_state.add(hashMap_state);





        hashMap_state = new HashMap<>();

        hashMap_country.put("State", lv_state);
        lv_skillset.add(hashMap_country);

        /*America*/
        hashMap_country = new HashMap<>();
        hashMap_country.put("Name", "America");

        lv_state = new ArrayList<>();
        hashMap_state = new HashMap<>();

        hashMap_state.put("Name", "California");
        lv_state.add(hashMap_state);


        hashMap_state = new HashMap<>();
        hashMap_state.put("Name", "Hawaii");
        lv_state.add(hashMap_state);


        hashMap_state = new HashMap<>();
        hashMap_state.put("Name", "Kabul");
        lv_state.add(hashMap_state);

        hashMap_country.put("State", lv_state);
        lv_skillset.add(hashMap_country);

    }

}
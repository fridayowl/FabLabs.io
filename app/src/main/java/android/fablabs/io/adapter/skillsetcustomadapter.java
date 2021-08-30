package android.fablabs.io.adapter;

import android.content.Context;
import android.fablabs.io.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class skillsetcustomadapter extends BaseExpandableListAdapter {
    List<HashMap<String,Object>> lv_data;
    Context context;
    int minormax=0;

    HashMap<Integer, Integer> childCheckedState = new HashMap<>();
    HashMap<Integer, Integer> childCheckboxState = new HashMap<>();

    public skillsetcustomadapter(List<HashMap<String, Object>> lv_data, Context context) {
        this.lv_data = lv_data;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return lv_data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<HashMap<String,Object>> lv_state = (List<HashMap<String, Object>>) lv_data.get(i).get("State");

        return lv_state.size();
    }

    @Override
    public Object getGroup(int i) {
        return lv_data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        List<HashMap<String, Object>> lv_state = (List<HashMap<String, Object>>) lv_data.get(i).get("State");
        return lv_state.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_header, null);
        }

        TextView tv_header = (TextView) convertView.findViewById(R.id.tv_headername);
        tv_header.setText(lv_data.get(i).get("Name").toString());
//        tv_header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(minormax==0) {
//                    minormax = 1;
//                }else{
//                    minormax = 0;
//                }
//            }
//        });

        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_child, null);
        }
        CheckBox filterCheckBox;

        TextView tv_chid = (TextView) convertView.findViewById(R.id.tv_child);
        List<HashMap<String, Object>> lv_state = (List<HashMap<String, Object>>) lv_data.get(i).get("State");
        tv_chid.setText(lv_state.get(i1).get("Name").toString());
        RelativeLayout rlt = (RelativeLayout) convertView.findViewById(R.id.rtl);
//        convertView.filterCheckBox = convertView.findViewById(R.id.check1);


//        rlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(context,lv_data.get(i).get("Name").toString(),Toast.LENGTH_LONG).show();
//                rlt.setBackgroundColor(context.getResources().getColor(R.color.defaultColor));
//            }
//        });

//        if (childCheckboxState.size() > 0) {
//            if (childCheckboxState.get(i1) != null) {
//                if (childCheckboxState.get(i1) == 0) {
//                    filterCheckBox.setChecked(false);
//                } else {
//                    filterCheckBox.setChecked(true);
//                }
//            }
//        }

//        filterCheckBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context,String.valueOf(i1),Toast.LENGTH_LONG).show();
////                if (filterCheckBox.isChecked()) {
////                    childCheckboxState.put(i,1);
//////                    listOfStatusFilters.add(_listDataChild.get(headerText.getTitle()).get(childPosition));
////                } else {
////                    childCheckboxState.put(i, 0);
//////                    listOfStatusFilters.remove(_listDataChild.get(headerText.getTitle()).get(childPosition));
////                }
////
//            }
//        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

//    @Override
//    public void onGroupExpanded(int groupPosition) {
//        Toast.makeText(context,String.valueOf(groupPosition),Toast.LENGTH_LONG).show();
//        super.onGroupExpanded(groupPosition);
//    }


}

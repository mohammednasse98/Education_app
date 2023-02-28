package com.example.education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentStTe extends Fragment {
    String str[] ={"Teacher","Student"};
    ListView listView;
    TextView textView;
    Button button;
    String cho="";
    String string="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentstute,container,false);
        listView=(ListView)view.findViewById(R.id.listchoos);
        button=(Button)view.findViewById(R.id.choose);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(container.getContext(),android.R.layout.simple_list_item_single_choice,str);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        textView=(TextView)view.findViewById(R.id.textError);
        textView.setVisibility(View.INVISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cho= str[i];
                string=str[i];

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cho.equals("")){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Choose one of them!");
                }else{
                    Bundle bundle1=new Bundle();
                    bundle1.putString("choose",cho);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentData fragmentData = new FragmentData();
                    fragmentData.setArguments(bundle1);
                    fragmentTransaction.replace(R.id.frag, fragmentData);
                    fragmentTransaction.commit();
                }
            }
        });
        return  view;
    }
}
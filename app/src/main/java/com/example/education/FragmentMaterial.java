package com.example.education;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.nio.BufferUnderflowException;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMaterial extends Fragment {
    View view;

    String material[]={"Math","English","Chemistry","Physics","Computer","Biology","ِArabic",
            "Geology","Islamic Sciences","Geography"};
    int[] images={R.drawable.math,R.drawable.english,R.drawable.chemistry,
            R.drawable.physics,R.drawable.computer,R.drawable.biology,
            R.drawable.arabic,R.drawable.geology,R.drawable.islamic,R.drawable.georaphy};
    ListView listView ;
    String string="";
    String material1="",phone="",name="",date="",city="",image="",hour;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_material,container,false);
        Bundle bundle=getArguments();
        phone=bundle.getString("phone");
        name=bundle.getString("name");
        city=bundle.getString("city");
        date=bundle.getString("date");
        image=bundle.getString("image");
        listView=view.findViewById(R.id.list_material);
        MyAdapter1 myAdapter=new MyAdapter1(getActivity(),material,images);
        listView.setAdapter(myAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    material1=material[position];
                    Model model=new Model();
                    model.setMaterial(material1);
                    dialog();
                }
            });



        return view;
    }

    private void dialog() {
            Bundle bundle=new Bundle();
        bundle.putString("Material",material1);
        bundle.putString("phone",phone);
        bundle.putString("name",name);
        bundle.putString("city",city);
        bundle.putString("date",date);
        bundle.putString("image",image);
        DialogFrag dialogFrag=new DialogFrag();
            dialogFrag.setArguments(bundle);
             dialogFrag.show(getActivity().getSupportFragmentManager(),"dialog frag");
    }
    class MyAdapter1 extends ArrayAdapter<String>{
        Context context;
        String material[];
        int images[];
        MyAdapter1(Context context,String material[],int images[]){
            super(context,R.layout.design_list,R.id.textView1,material);
            this.context=context;
            this.material=material;
            this.images=images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.design_list,parent,false);
            CircleImageView imageView=view.findViewById(R.id.imageView);
            TextView nMaterial=view.findViewById(R.id.textView1);
            imageView.setImageResource(images[position]);
            nMaterial.setText(material[position]);
            return view;
        }

    }
}

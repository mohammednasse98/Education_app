package com.example.education;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.education.ui.main.PageViewModel;
import com.example.education.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabbedTe extends AppCompatActivity {
            String ss="";

    public static class SectionsPagerAdapter extends FragmentPagerAdapter{
            private static final int[] TAB_TITLES = new int[]{R.string.tab_text_4,R.string.tab_text_5};
            private final Context mContext;
                String[] aa;
            public SectionsPagerAdapter(Context context, FragmentManager fm,String ss) {
                super(fm);
                mContext = context;
                aa=new String[8];
                aa=ss.split("/t");
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        Bundle bundle=new Bundle();
                        bundle.putString("name",aa[0]);
                        bundle.putString("phone",aa[1]);
                        bundle.putString("gender",aa[2]);
                        bundle.putString("city",aa[3]);
                        bundle.putString("teacherClass",aa[4]);
                        bundle.putString("date",aa[5]);
                        bundle.putString("password",aa[6]);
                        bundle.putString("image",aa[7]);
                        FragmentNotificationTe fragmentNotificationTe=new FragmentNotificationTe();
                        fragmentNotificationTe.setArguments(bundle);
                        fragment=fragmentNotificationTe;
                        break;
                    case 1:
                        Bundle bundle1=new Bundle();
                        bundle1.putString("name",aa[0]);
                        bundle1.putString("phone",aa[1]);
                        bundle1.putString("gender",aa[2]);
                        bundle1.putString("city",aa[3]);
                        bundle1.putString("teacherClass",aa[4]);
                        bundle1.putString("date",aa[5]);
                        bundle1.putString("password",aa[6]);
                        bundle1.putString("image",aa[7]);
                        FragmentSettingTe fragmentSettingTe=new FragmentSettingTe();
                        fragmentSettingTe.setArguments(bundle1);
                        fragment=fragmentSettingTe;
                        break;
                    }
                return fragment;

            }
            public CharSequence getPageTitle(int position) {
                return mContext.getResources().getString(TAB_TITLES[position]);
            }

            @Override
            public int getCount() {
                return 2;
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_te);
        Intent intent=getIntent();
        TextView textView=findViewById(R.id.title);
        String name= intent.getStringExtra("name");
        String phone= intent.getStringExtra("phone");
        String gender= intent.getStringExtra("gender");
        String city=intent.getStringExtra("city");
        String date=intent.getStringExtra("date");
        String password=intent.getStringExtra("password");
        String teacherClass=intent.getStringExtra("teacherClass");
        String image=intent.getStringExtra("image");
        ss=name+"/t"+phone+"/t"+gender+"/t"+city+"/t"+teacherClass+"/t"+date+"/t"+password+"/t"+image;
        SectionsPagerAdapter sectionsPagerAdapter1 = new SectionsPagerAdapter(this, getSupportFragmentManager(), ss);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter1);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        textView.setText(name);


    }
    public class PageViewModel extends ViewModel {
        private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
        private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return "Hello world from section: " + input;
            }
        });

        public void setIndex(int index) {
            mIndex.setValue(index);
        }

        public LiveData<String> getText() {
            return mText;
        }
    }
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        private com.example.education.ui.main.PageViewModel pageViewModel;

        public static com.example.education.ui.main.PlaceholderFragment newInstance(int index) {
            com.example.education.ui.main.PlaceholderFragment fragment = new com.example.education.ui.main.PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER, index);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            pageViewModel = new ViewModelProvider(this).get(com.example.education.ui.main.PageViewModel.class);
            int index = 1;
            if (getArguments() != null) {
                index = getArguments().getInt(ARG_SECTION_NUMBER);
            }
            pageViewModel.setIndex(index);
        }

        @Override
        public View onCreateView(
                @NonNull LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_tabbed, container, false);
            final TextView textView = root.findViewById(R.id.section_label);
            pageViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }

    }
    }
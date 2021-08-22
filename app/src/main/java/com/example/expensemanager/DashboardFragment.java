package com.example.expensemanager;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DashboardFragment extends Fragment {

    // Floating Button
    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_income_btn;
    private FloatingActionButton fab_expense_btn;

//    Floating button TextView
    private TextView fab_income_txt;
    private TextView fab_expense_txt;


//boolen
    private boolean isOpen=false;

//    Animation
    private Animation FadeOpen,FadeClose;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

//        Connect floating button to layout

        fab_main_btn = view.findViewById(R.id.fb_main_plus_btn);
        fab_expense_btn=view.findViewById(R.id.expense_ft_btn);
        fab_income_btn=view.findViewById(R.id.income_ft_btn);



//        Connect floating text
        fab_income_txt=view.findViewById(R.id.income_ft_text);
        fab_expense_txt=view.findViewById(R.id.expense_ft_text);



//        Animation connect
        FadeOpen= AnimationUtils.loadAnimation(getActivity(),R.anim.fade_open_anim);
        FadeClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_close_anim);



//        adding onClick listener to main button
        fab_main_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addData();
//               if main button is open add animations
                if(isOpen){
                    fab_income_btn.startAnimation(FadeClose);
                    fab_expense_btn.startAnimation(FadeClose);
                    fab_income_btn.setClickable(false);
                    fab_expense_btn.setClickable(false);

                    fab_expense_txt.startAnimation(FadeClose);
                    fab_income_txt.startAnimation(FadeClose);
                    fab_income_txt.setClickable(false);
                    fab_expense_txt.setClickable(false);
                    isOpen=false;
                }else {
                    fab_income_btn.startAnimation(FadeOpen);
                    fab_expense_btn.startAnimation(FadeOpen);
                    fab_expense_btn.setClickable(true);
                    fab_income_btn.setClickable(true);

                    fab_expense_txt.startAnimation(FadeClose);
                    fab_income_txt.startAnimation(FadeClose);
                    fab_income_txt.setClickable(true);
                    fab_expense_txt.setClickable(true);
                    isOpen=true;

                }
            }
        });

        return view;
    }


    private void addData(){
//        Fab button income...
        fab_income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fab_expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
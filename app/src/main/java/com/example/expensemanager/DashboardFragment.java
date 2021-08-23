package com.example.expensemanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanager.Model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;


public class DashboardFragment extends Fragment {

    // Floating Button
    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_income_btn;
    private FloatingActionButton fab_expense_btn;

//    Floating button TextView
    private TextView fab_income_txt;
    private TextView fab_expense_txt;


//   boolean
    private boolean isOpen=false;

//    Animation
    private Animation FadeOpen,FadeClose;

//    Firebase
   private FirebaseAuth mAuth;
    private DatabaseReference mIncomeDatabase;
  private DatabaseReference mExpenseDatabase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

//        Connect floating button to layout
        fab_main_btn = view.findViewById(R.id.fb_main_plus_btn);
        fab_expense_btn=view.findViewById(R.id.expense_ft_btn);
        fab_income_btn=view.findViewById(R.id.income_ft_btn);


        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();



        mIncomeDatabase = FirebaseDatabase.getInstance().getReference().child("IncomeData").child(uid);
        mExpenseDatabase = FirebaseDatabase.getInstance().getReference().child("ExpenseDatabase").child(uid);




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


//    Floating button animation
    private void ftAnimation(){
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


    private void addData(){
//        Fab button income...
        fab_income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incomeDataInsert();
            }
        });

        fab_expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseDataInsert();
            }
        });
    }

    public void incomeDataInsert(){

        AlertDialog.Builder myDailogue = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View myview = inflater.inflate(R.layout.custom_layout_for_insertdata,null);
        myDailogue.setView(myview);
        AlertDialog dialog = myDailogue.create();

        EditText edtAmount = myview.findViewById(R.id.ammount_edit);
        EditText edtType = myview.findViewById(R.id.type_edit);
        EditText edtNotes = myview.findViewById(R.id.note_edit);
        Button btnSave = myview.findViewById(R.id.btnSave);
        Button btnCancel = myview.findViewById(R.id.btnCancle);

//        use on click listener on buttons
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount= edtAmount.getText().toString().trim();
                String type = edtType.getText().toString().trim();
                String note = edtNotes.getText().toString().trim();
                if(TextUtils.isEmpty(type)){
                    edtType.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(amount)){
                    edtAmount.setError("Required Field...");
                    return;
                }
                int ouramountint = Integer.parseInt(amount);

                if(TextUtils.isEmpty(note)){
                    edtNotes.setError("Required Field...");
                    return;
                }

                String id =  mIncomeDatabase.push().getKey();

//                to get current date
                String mDate = DateFormat.getDateInstance().format(new Date());

//                creating object of model class
                Data data = new Data(ouramountint,type,note,id,mDate);

                mIncomeDatabase.child(id).setValue(data);

                Toast.makeText(getActivity(), "Data Added",Toast.LENGTH_SHORT).show();

                ftAnimation();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ftAnimation();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void expenseDataInsert(){
        AlertDialog.Builder mydialogue = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=LayoutInflater.from(getActivity());

        View myview = inflater.inflate(R.layout.custom_layout_for_insertdata,null);
        mydialogue.setView(myview);

        final AlertDialog dialog = mydialogue.create();

       final EditText ammount = myview.findViewById(R.id.ammount_edit);
        EditText type = myview.findViewById(R.id.type_edit);
        EditText note = myview.findViewById(R.id.note_edit);

        Button btnSave=myview.findViewById(R.id.btnSave);
        Button btnCancel = myview.findViewById(R.id.btnCancle);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String tmAmount = ammount.getText().toString().trim();
                 String tmtype = type.getText().toString().trim();
                 String tmnote = note.getText().toString().trim();

                 if (TextUtils.isEmpty(tmAmount)){
                     ammount.setError("Required Field...");
                     return;
                 }
                 if (TextUtils.isEmpty(tmtype)){
                     type.setError("Required Field...");
                     return;
                 }
                 if(TextUtils.isEmpty(tmnote)){
                     note.setError("Required Field..");
                     return;
                 }
                 ftAnimation();
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ftAnimation();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
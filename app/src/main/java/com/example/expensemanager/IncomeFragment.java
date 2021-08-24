package com.example.expensemanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expensemanager.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class IncomeFragment extends Fragment {


//    Firebase database...

    private FirebaseAuth mAuth;
    private DatabaseReference mIncomeDatabase;


//    Recyclerview
    private RecyclerView recyclerView;

//    TextView to display result
    private TextView incomeTotalSum;

    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_income, container, false);

        mAuth= FirebaseAuth.getInstance();

//        To create unique user
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid= mUser.getUid();


        mIncomeDatabase = FirebaseDatabase.getInstance().getReference().child("IncomeData").child(uid);

        incomeTotalSum=view.findViewById(R.id.income_txt_result);

        recyclerView = view.findViewById(R.id.recycler_id_income);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        mIncomeDatabase.addValueEventListener(new ValueEventListener() {

            int totalValue=0;

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mySnapshot: snapshot.getChildren()){
                    Data data=mySnapshot.getValue(Data.class);

                    totalValue+=data.getAmount();

//                            concate this value

                    String stTotalValue=String.valueOf(totalValue);
                   incomeTotalSum.setText(stTotalValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("IncomeData"), Data.class)
                        .build();


        FirebaseRecyclerAdapter<Data, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {
                holder.setAmount(model.getAmount());
                holder.setDate(model.getDate());
                holder.setNote(model.getNotes());
                holder.setType(model.getType());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.income_recycler_data, parent, false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);





    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }

        private  void setType(String type){
            TextView mType=mView.findViewById(R.id.type_txt_income);
            mType.setText(type);
        }

        private void setNote(String note){
            TextView mNote = mView.findViewById(R.id.note_txt_income);
            mNote.setText(note);
        }

        private void setDate(String date){
            TextView mDate= mView.findViewById(R.id.date_txt_income);
            mDate.setText(date);
        }

        private void setAmount(int amount){
            TextView mAmount=mView.findViewById(R.id.amount_txt_income);
            String stamount=String.valueOf(amount);
            mAmount.setText(stamount);
        }


    }
}
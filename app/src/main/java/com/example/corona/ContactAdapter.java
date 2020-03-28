package com.example.corona;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private Context mContext;
    private ArrayList<ContactItem> mContactList;

    public ContactAdapter(Context context, ArrayList<ContactItem> contactList){
        mContext=context;
        mContactList = contactList;
    }

    @NotNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.conatct_item,viewGroup,false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        ContactItem currentItem = mContactList.get(i);

        String stateText = currentItem.getState();
        final String numberText = currentItem.getNumber();

        contactViewHolder.state.setText(stateText);
        contactViewHolder.number.setText(numberText);

        contactViewHolder.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String n ="";
                if(numberText.startsWith("180031")){
                    n = numberText.substring(0,13);
                }
                else if(numberText.length()>14){
                    n = numberText.substring(0,15);
                }
                else{
                    n = numberText;
                }
                intent.setData(Uri.parse("tel:"+n));
                mContext.startActivity(intent);
            }
        });
        contactViewHolder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String n ="";
                if(numberText.startsWith("180031")){
                    n = numberText.substring(0,13);
                }
                else if(numberText.length()>14){
                    n = numberText.substring(0,15);
                }
                else{
                    n = numberText;
                }
                intent.setData(Uri.parse("tel:"+n));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView state;
        public TextView number;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.contact_text);
            number = itemView.findViewById(R.id.contact_no);
        }
    }
}

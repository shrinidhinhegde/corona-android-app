package com.example.corona;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentCONTACT extends Fragment {

    private RecyclerView mRecyclerView;
    private ContactAdapter mContactAdapter;
    private ArrayList<ContactItem> mContactList;
    private RequestQueue mQueue;
    private String sa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.frag_contact, container, false);
        mRecyclerView = vi.findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(vi.getContext()));
        mContactList = new ArrayList<>();
        mQueue = Volley.newRequestQueue(vi.getContext());
        startAsyncTask(vi);
        return vi;
    }


    public void startAsyncTask(View v){
        ExampleAsyncTask task = new ExampleAsyncTask();
        task.execute("https://api.rootnet.in/covid19-in/contacts");
    }

    private class ExampleAsyncTask extends AsyncTask<String,String ,String>{
        @Override
        protected String doInBackground(String... strings) {
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://api.rootnet.in/covid19-in/contacts", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                JSONObject data = response.getJSONObject("data");
                                JSONObject cont = data.getJSONObject("contacts");
                                JSONArray regional = cont.getJSONArray("regional");
                                int i=0;
                                while(i<regional.length()){
                                    JSONObject region = regional.getJSONObject(i);
                                    String state = region.getString("loc");
                                    String numb = region.getString("number");
                                    mContactList.add(new ContactItem(state,numb));
                                    i++;
                                }
                                mContactAdapter = new ContactAdapter(getContext(),mContactList);
                                mRecyclerView.setAdapter(mContactAdapter);
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mQueue.add(request);
            return sa;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

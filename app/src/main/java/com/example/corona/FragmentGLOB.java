package com.example.corona;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FragmentGLOB extends Fragment {

    private TextView textView;
    private RequestQueue mQueue;
    private String sa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.frag_glob, container, false);
        textView = vi.findViewById(R.id.text_glob);
        mQueue = Volley.newRequestQueue(vi.getContext());
        textView.setText("");
        textView.setVisibility(vi.GONE);
        startAsyncTask(vi);
        return vi;
    }


    public void startAsyncTask(View v){
        ExampleAsyncTask task = new ExampleAsyncTask();
        task.execute("https://covid-193.p.rapidapi.com/statistics");
    }

    private class ExampleAsyncTask extends AsyncTask<String,String ,String>{
        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://covid-193.p.rapidapi.com/statistics" , null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("response");
                                int i = 0;
                                while (i < jsonArray.length()) {
                                    JSONObject respons = jsonArray.getJSONObject(i);
                                    String country = respons.getString("country");
                                    textView.append("Country: "+country);
                                    JSONObject cases = respons.getJSONObject("cases");
                                    textView.append("\nCases");
                                    int newcase;
                                    try {
                                        newcase = cases.getInt("new");
                                    }
                                    catch (JSONException e){
                                        newcase = 0;
                                    }
                                    textView.append( "\nNew: " + String.valueOf(newcase));
                                    int activecase;
                                    try {
                                        activecase = cases.getInt("active");
                                    }
                                    catch (JSONException e){
                                        activecase = 0;
                                    }
                                    textView.append("\nActive: " + String.valueOf(activecase));
                                    int criticalcase;
                                    try {
                                        criticalcase = cases.getInt("critical");
                                    }
                                    catch (JSONException e){
                                        criticalcase = 0;
                                    }
                                    textView.append( "\nCritical: " + String.valueOf(criticalcase));
                                    int totalcase;
                                    try {
                                        totalcase = cases.getInt("total");
                                    }
                                    catch (JSONException e){
                                        totalcase = 0;
                                    }
                                    textView.append( "\nTotal: " + String.valueOf(totalcase));
                                    JSONObject deaths = respons.getJSONObject("deaths");
                                    textView.append( "\nDeaths");
                                    int newdeath;
                                    try {
                                        newdeath = deaths.getInt("new");
                                    }
                                    catch (JSONException e){
                                        newdeath=0;
                                    }
                                    textView.append( "\nNew: " + String.valueOf(newdeath));
                                    int totaldeath;
                                    try {
                                        totaldeath = deaths.getInt("total");
                                    }
                                    catch (JSONException e){
                                        totaldeath = 0;
                                    }
                                    textView.append( "\nTotal: " + String.valueOf(totaldeath) + "\n\n");
                                    i++;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("x-rapidapi-host", "covid-193.p.rapidapi.com");
                    headers.put("x-rapidapi-key", "21e213400cmsh5e9e175e32cec4bp13ad1bjsne84f91c9ccf2");
                    return headers;
                }
            };
            mQueue.add(request);
            return sa;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setVisibility(getView().VISIBLE);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}

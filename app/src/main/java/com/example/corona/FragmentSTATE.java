package com.example.corona;

import android.os.AsyncTask;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

public class FragmentSTATE extends Fragment {

    private TextView textView;
    private RequestQueue mQueue;
    private String sa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.frag_state, container, false);
        textView = vi.findViewById(R.id.text_state);
        mQueue = Volley.newRequestQueue(vi.getContext());
        textView.setText("");
        textView.setVisibility(vi.GONE);
        startAsyncTask(vi);
        return vi;
    }


    public void startAsyncTask(View v){
        ExampleAsyncTask task = new ExampleAsyncTask();
        task.execute("api.rootnet.in/covid19-in/stats/latest");
    }

    private class ExampleAsyncTask extends AsyncTask<String,String ,String>{
        @Override
        protected String doInBackground(String... strings) {
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://api.rootnet.in/covid19-in/stats/latest", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                JSONObject data = response.getJSONObject("data");
                                JSONArray regional = data.getJSONArray("regional");
                                int i=0;
                                while(i<regional.length()){
                                    JSONObject region = regional.getJSONObject(i);
                                    String state = region.getString("loc");
                                    textView.append("State: "+state);
                                    int indian = region.getInt("confirmedCasesIndian");
                                    textView.append("\nCases\nIndian: "+indian);
                                    int foreign = region.getInt("confirmedCasesForeign");
                                    textView.append("\nForeign: "+foreign);
                                    int discharged = region.getInt("discharged");
                                    textView.append("\nDischarged: "+discharged);
                                    int death = region.getInt("deaths");
                                    textView.append("\nDeaths: "+death+"\n\n");
                                    i++;
                                }
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
            textView.setVisibility(getView().VISIBLE);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}

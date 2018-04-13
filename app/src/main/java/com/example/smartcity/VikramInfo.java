package com.example.smartcity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.smartcity.Util.Graph;

import java.util.ArrayList;

public class VikramInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ListView listView;
    ArrayList<ArrayList<String>> r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vikram_info);
        new DownloadFile(this).execute();
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.list_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vno,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        ArrayList<String> arrayList = r.get(pos);
        String arr[] = new String[arrayList.size()];
        for(int i = 0;i<arrayList.size();i++){
            arr[i] = arrayList.get(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    class DownloadFile extends AsyncTask<Void, Void, ArrayList<ArrayList<String>>> {
        ArrayList<String> s = null;
        Context context;
        DownloadFile(Context context){
            this.context = context;
        }
        protected ArrayList<ArrayList<String>> doInBackground(Void... urls) {
            ArrayList<ArrayList<String>> ss = new ArrayList<>();
            Networking.getHttpResponse(ss);
            return ss;
        }

        protected void onPostExecute(ArrayList<ArrayList<String>> result) {
            r = result;
        }
    }
}

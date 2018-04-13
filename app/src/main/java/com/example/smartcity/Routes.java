package com.example.smartcity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.Util.Graph;
import com.example.smartcity.Util.GraphUtil;
import com.example.smartcity.Util.Node;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Routes extends AppCompatActivity {


    AutoCompleteTextView textViewCurrentLocation;
    AutoCompleteTextView textViewDestination;
    TextView textView;
    ArrayList<String> nodes;
    Graph graph;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        new DownloadFile(this).execute();
    }
    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recyler_view);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        textViewCurrentLocation = (AutoCompleteTextView) findViewById(R.id.textView_currentLocation);
        textViewCurrentLocation.setThreshold(1);
        ArrayAdapter<String> adapterCurrentLocation = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, Arrays.copyOf(nodes.toArray(),nodes.toArray().length,String[].class));
        textViewCurrentLocation.setAdapter(adapterCurrentLocation);

        textViewDestination = (AutoCompleteTextView) findViewById(R.id.textView_destination);
        textViewDestination.setThreshold(1);
        ArrayAdapter<String> adapterDestination = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,Arrays.copyOf(nodes.toArray(),nodes.toArray().length,String[].class));
        textViewDestination.setAdapter(adapterDestination);

        textView = (TextView) findViewById(R.id.textView_path);

    }
    public void searchRoute(View view){
        String currentLocation = textViewCurrentLocation.getText().toString();
        String destination = textViewDestination.getText().toString();
        textViewDestination.clearFocus();
        textViewCurrentLocation.clearFocus();
        Node source = new Node(find(currentLocation),currentLocation);
        Node dest = new Node(find(destination),destination);
        ArrayList<Node> nodes = GraphUtil.findPath(graph,source,dest);
        StringBuilder builder = new StringBuilder();
        ArrayList<String> vn = new ArrayList<>();
        for(int i =0;i<nodes.size();i++){
            if(nodes.get(i).vName != null && !vn.contains(nodes.get(i).vName)) {
                vn.add(nodes.get(i).vName);
            }
        }

        ArrayList<String> s = new ArrayList<>();
        for(int i =0;i<nodes.size();i++){
            s.add(nodes.get(i).name);
        }

        adapter = new MyAdapter(s);
        recyclerView.setAdapter(adapter);

        builder.append("Vikram no. To Take: ");
        for(int i = 0;i<vn.size();i++){
            if(i<vn.size()-1)
            builder.append(vn.get(i)+", ");
            else
                builder.append(vn.get(i));
        }
        textView.setText(builder.toString());
        builder.append("\n");
        for(int i = 0;i<nodes.size();i++){
            if(i == nodes.size()-1)
                    builder.append(nodes.get(i).name);
            else {
                    builder.append(nodes.get(i).name+"->");
            }
        }
        //textView.setText(builder.toString());
    }

    class DownloadFile extends AsyncTask<Void, Void, ArrayList<ArrayList<String>>> {
        ArrayList<String> s = null;
        Context context;
        DownloadFile(Context context){
            this.context = context;
        }
        protected ArrayList<ArrayList<String>> doInBackground(Void... urls) {
            ArrayList<ArrayList<String>> ss = new ArrayList<>();
            nodes = Networking.getHttpResponse(ss);
            return ss;
        }

        protected void onPostExecute(ArrayList<ArrayList<String>> result) {
            Log.d("*****FJLKJEL******",String.valueOf(nodes.size()));
            graph = new Graph(nodes.size());
            createGraph(graph,result);
            init();
        }
    }

    public void createGraph(Graph graph,ArrayList<ArrayList<String>> data){
        for(int i = 0;i<data.size();i++){
            ArrayList<String> one = data.get(i);
            Log.d("******V NO*****",one.get(0));
            for(int j = 1;j<one.size()-1;j++){
                GraphUtil.addEdge(graph,new Node(find(one.get(j)),one.get(j),one.get(0)),new Node(find(one.get(j+1)),one.get(j+1)));
            }
        }
    }

    public int find(String s){
        return nodes.indexOf(s);
    }
}

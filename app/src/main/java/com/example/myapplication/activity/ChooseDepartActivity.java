package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.bean.Depart;
import com.example.myapplication.bean.User;
import com.example.myapplication.utils.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ChooseDepartActivity extends AppCompatActivity {
    private String baseurl= Config.baseUrl + "ChooseDepartServlet";
    private String result;
    private List<Depart> departs;
    private ListView departListView;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_depart);

        departListView = (ListView)findViewById(R.id.depart_list_view);
        new DepartAsyncTask().execute();
            user =  new Gson().fromJson( getIntent().getStringExtra("user"), User.class);
        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseDepartActivity.this,IndexActivity.class);
                intent.putExtra("user",new Gson().toJson(user));
                startActivity(intent);
            }
        });


    }

    public class DepartListAdapter extends BaseAdapter{
        private List<Depart> mdeparts;
        DepartListAdapter(List<Depart> departs){
            mdeparts = departs;
        }
        @Override
        public int getCount() {
            return mdeparts.size();
        }

        @Override
        public Object getItem(int i) {
            return mdeparts.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mdeparts.get(i).getDepartId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null){
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.item_depart_list_view,null);
            }
            TextView departName = view.findViewById(R.id.depart_name_tv);
            departName.setText(mdeparts.get(i).getDepartName());

            departName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChooseDepartActivity.this,ChooseDoctorActivity.class);
                    intent.putExtra("depart",new Gson().toJson(mdeparts.get(i)));
                    intent.putExtra("user",new Gson().toJson(user));
                    startActivity(intent);


                }
            });

            return view;
        }
    }

    public class DepartAsyncTask extends AsyncTask<Void,Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            departs = gson.fromJson(result, new TypeToken<List<Depart>>() {}.getType());
            departListView.setAdapter(new DepartListAdapter( departs));
        }
    }
}
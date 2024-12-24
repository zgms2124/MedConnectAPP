package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.bean.Doctor;
import com.example.myapplication.bean.Order;
import com.example.myapplication.bean.Schedule;
import com.example.myapplication.bean.User;
import com.example.myapplication.utils.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MyInfoActivity extends AppCompatActivity {
    private User user;
    private ListView myInfoListView;
    private String result;
    private String baseurl = Config.baseUrl + "myinfoServlet";
    private List<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);

        TextView uname = findViewById(R.id.welcome_uname);
        ImageView btn_back = findViewById(R.id.btn_back);
        myInfoListView = findViewById(R.id.myinfo_list_view);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, IndexActivity.class);
                intent.putExtra("user", new Gson().toJson(user));
                startActivity(intent);
            }
        });

        if(user != null){
            uname.setText("" + user.getUname() + "，祝您身体健康！");
        }

        baseurl = baseurl + "?uid=" + user.getUid();
        new MyinfoAsyncTask().execute();
    }
    public class MyinfoListAdapter extends BaseAdapter {
        private List<Order> morders;
        MyinfoListAdapter(List<Order> orders){
            morders = orders;
        }
        @Override
        public int getCount() {
            return morders == null ? 0 : morders.size();
        }

        @Override
        public Object getItem(int i) {
            return morders.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.item_myinfo_list_view, null);
            }

            TextView time = view.findViewById(R.id.time);
            TextView departname = view.findViewById(R.id.departname);
            TextView dname = view.findViewById(R.id.dname);

            time.setText(morders.get(i).getTime());
            departname.setText(morders.get(i).getDepartname());
            dname.setText(morders.get(i).getDname());

            return view;
        }

    }
    public class MyinfoAsyncTask extends AsyncTask<Void,Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            orders = gson.fromJson(result, new TypeToken<List<Order>>() {}.getType());
            if (orders == null || orders.isEmpty()) {
                // 没有订单信息，更新UI显示没有订单
                myInfoListView.setEmptyView(findViewById(R.id.empty_view)); // 假设你有一个空视图的ID
                Toast.makeText(MyInfoActivity.this, "您还没有订单信息。", Toast.LENGTH_SHORT).show();
            } else {
                myInfoListView.setAdapter(new MyinfoListAdapter(orders));
            }
        }
    }
}
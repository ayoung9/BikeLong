package bikelong.iot2.goott.bikelong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static bikelong.iot2.goott.bikelong.R.id.goal_list;

public class GoalActivity extends AppCompatActivity {

    private ListView mGoalListView;
    private GoalListAdapter mGoalListAdapter;
    private List<Goal> mGoals; //데이터 담을 객체 선언
    private Goal goal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goal);

        mGoals = new ArrayList<>();

        mGoalListAdapter = new GoalListAdapter(mGoals, this ,R.layout.goal_item_view);

        mGoalListView = findViewById(R.id.goal_list);

        mGoalListView.setAdapter(mGoalListAdapter);

        loadGoals();

        mGoalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goalDetail = new Intent(view.getContext(), PieActivity.class);

                Goal goal= mGoals.get(position);

                goalDetail.putExtra("detail_name",goal);

                startActivity(goalDetail);
            }
        });
    }

    private void loadGoals(){

        Thread t = new Thread() { //안드로이드의 네트워크는 반드시 스레드안에서 실행되어야한다.
            public void run() {
                try {
                    int x = (int)(Math.random() * 900) + 100;
                    String y = String.valueOf((int)(Math.random() * 900) + 100);
                    String serverUrl = "http://172.16.6.11:8087/bikelong/goal/mobile_goal.action";

                    URL url = new URL(serverUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    final int responseCode = con.getResponseCode();

                    if (responseCode == 200) {  //정상 응답인 경우
                        processResult(con.getInputStream());
                        //processResult1(con.getInputStream());
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //show error message
                                Toast.makeText(getApplicationContext(),
                                        "error " + responseCode, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
        t.start();
    }

    private void processResult(InputStream inputStream) {

        mGoals.clear();

        try {
            //JSON 문자열 -> 객체 트리로 변환하는 변환기 만들기
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            Goal[] goals = gson.fromJson(reader, Goal[].class); //Json -> 객체

            for (Goal goal : goals) {
                mGoals.add(goal);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //데이터가 변경되었으니 화면 목록을 갱신해서 표시하세요
                    mGoalListAdapter.notifyDataSetChanged();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processResult1(InputStream is) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        final StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
    }



}

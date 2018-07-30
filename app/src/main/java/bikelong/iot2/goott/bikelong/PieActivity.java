package bikelong.iot2.goott.bikelong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieActivity extends AppCompatActivity {

    float rainfall[] = {98.8f,123.8f,161.6f,24.2f,52f,58.2f,35.4f,13.8f,78.4f,203.4f,240.2f,159.7f};
    String monthNames[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    private TextView goalNameTextView;


    /*float rainfall[] = {20.5f,30f};
    String monthNames[] = {"Jan","Feb"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);

        goalNameTextView = (TextView) findViewById(R.id.detail_name);

        Intent intent = getIntent();
        Goal detail_name = (Goal) intent.getSerializableExtra("detail_name");

        goalNameTextView.setText(detail_name.getGoalName());

        setupPieChart();

    }

    private void setupPieChart(){

        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i =1; i< rainfall.length; i++ ){
            pieEntries.add(new PieEntry(rainfall[i], monthNames[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries,"Rainfall for Vancourver");
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(data);
        chart.invalidate();
    }
}

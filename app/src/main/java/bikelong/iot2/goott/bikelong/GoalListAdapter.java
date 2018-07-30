package bikelong.iot2.goott.bikelong;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class GoalListAdapter extends BaseAdapter{

    private List<Goal> mGoals;
    private Context mActivityContext;
    private int mResourceId; //xml파일

    public GoalListAdapter(List<Goal> mGoals, Context mActivityContext, int mResourceId) {
        this.mGoals = mGoals;
        this.mActivityContext = mActivityContext;
        this.mResourceId = mResourceId;

    }

    @Override
    public int getCount() {
        return mGoals.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGoals.get(position).getGoalNo();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            view = View.inflate(mActivityContext,mResourceId,null);
        }

        Goal goal = mGoals.get(position);

        TextView goalName = view.findViewById(R.id.goal_name);
        goalName.setText(goal.getGoalName());

        TextView goalInfo = view.findViewById(R.id.goal_info);
        goalInfo.setText(goal.getGoalInfo());

        return  view;
    }
}

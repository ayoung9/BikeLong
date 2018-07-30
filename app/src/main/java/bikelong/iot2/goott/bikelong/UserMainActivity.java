package bikelong.iot2.goott.bikelong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserMainActivity extends AppCompatActivity {

    private Button mUseAppButton;
    private TextView userIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain);

        mUseAppButton = (Button) findViewById(R.id.useAppButton);
        userIdTextView = (TextView) findViewById(R.id.userIdTextView);

        //현재 Activity를 호출한 Activity가 전달한 Intent를 반환
        Intent intent = getIntent();
        Member member = (Member) intent.getSerializableExtra("member");

        userIdTextView.setText(member.getName() + " 님");

        mUseAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent = new Intent(v.getContext(), SignInActivity.class);
                startActivity(signinIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        if(item.getItemId() == R.id.select_goal){
            intent = new Intent(this,GoalActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.select_activity){
            intent = new Intent(this,UserMainActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.select_location){
            intent = new Intent(this,UserMainActivity.class);
            startActivity(intent);
        }
     /*   switch (item.getItemId()) {
            case 4 :
                intent = new Intent(this, GoalActivity.class);
                startActivity(intent);
                break;
            case 3 :
                intent = new Intent(this, UserMainActivity.class);
                startActivity(intent);
                break;
            case 2 :
                intent = new Intent(this, UserMainActivity.class);
                startActivity(intent);
                break;
            case 1 :
                intent = new Intent(this, UserMainActivity.class);
                startActivity(intent);
                break;
        }*/
        return super.onOptionsItemSelected(item);
    }
}

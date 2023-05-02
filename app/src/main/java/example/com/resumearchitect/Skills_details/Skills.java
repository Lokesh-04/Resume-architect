package example.com.resumearchitect.Skills_details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import example.com.resumearchitect.R;

public class Skills extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SkillAdapter skillAdapter;
    private final List<String> inputList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        recyclerView = findViewById(R.id.skills_recycler_view);
        skillAdapter = new SkillAdapter(inputList);
        recyclerView.setAdapter(skillAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            inputList.add("");
            skillAdapter.notifyItemInserted(inputList.size() - 1);
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onClick(View view) {

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View rview = recyclerView.getChildAt(i);
                    TextInputEditText skillName = rview.findViewById(R.id.name_of_skill);
                    String skill = skillName.getText().toString().trim();
                    if (!TextUtils.isEmpty(skill)) {
                        editor.putString("skill_" + i, skill);
                    }
                }
                editor.putInt("num_items", recyclerView.getChildCount());
                editor.apply();

            }
        });
    }
}

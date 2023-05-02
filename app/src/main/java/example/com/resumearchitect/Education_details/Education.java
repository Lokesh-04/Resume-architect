package example.com.resumearchitect.Education_details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import example.com.resumearchitect.R;

public class Education extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EduAdapter eduAdapter;
    private final List<String> inputList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        recyclerView = findViewById(R.id.edu_recycler_view);
        eduAdapter = new EduAdapter(inputList);
        recyclerView.setAdapter(eduAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            inputList.add("");
            eduAdapter.notifyItemInserted(inputList.size() - 1);
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onClick(View view) {

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View rview = recyclerView.getChildAt(i);
                    TextInputEditText coursename = rview.findViewById(R.id.coursename);
                    TextInputEditText uni = rview.findViewById(R.id.university);
                    TextInputEditText passout = rview.findViewById(R.id.passout);
                    TextInputEditText grade = rview.findViewById(R.id.grade);
                    String cn = coursename.getText().toString().trim();
                    String u = uni.getText().toString().trim();
                    String p = passout.getText().toString().trim();
                    String g = grade.getText().toString().trim();
                    if (!TextUtils.isEmpty(cn)) {
                        editor.putString("course_name_" + i, cn);
                        editor.putString("university_" + i, u);
                        editor.putString("passout_" + i, p);
                        editor.putString("grade_" + i, g);
                    }
                }
                editor.putInt("num_items", recyclerView.getChildCount());
                editor.apply();

            }
        });
    }
}

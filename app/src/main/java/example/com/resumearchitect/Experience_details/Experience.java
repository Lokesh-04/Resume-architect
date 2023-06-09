package example.com.resumearchitect.Experience_details;

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

import java.util.ArrayList;
import java.util.List;

import example.com.resumearchitect.R;

public class Experience extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpAdapter expAdapter;
    private final List<String> inputList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        recyclerView = findViewById(R.id.exp_recycler_view);
        expAdapter = new ExpAdapter(inputList);
        recyclerView.setAdapter(expAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            inputList.add("");
            expAdapter.notifyItemInserted(inputList.size() - 1);
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onClick(View view) {

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View rview = recyclerView.getChildAt(i);
                    TextInputEditText name = rview.findViewById(R.id.name_of_experience);
                    TextInputEditText type = rview.findViewById(R.id.type_of_experience);
                    TextInputEditText timeline = rview.findViewById(R.id.timeline);
                    TextInputEditText desc = rview.findViewById(R.id.description);
                    String na = name.getText().toString().trim();
                    String ty = type.getText().toString().trim();
                    String ti = timeline.getText().toString().trim();
                    String de = desc.getText().toString().trim();
                    if (!TextUtils.isEmpty(na)) {
                        editor.putString("name_of_experience_" + i, na);
                        editor.putString("type_of_experience_" + i, ty);
                        editor.putString("timeline_of_experience_" + i, ti);
                        editor.putString("description_" + i, de);
                    }
                }
                editor.putInt("num_items", recyclerView.getChildCount());
                editor.apply();

            }
        });
    }
}

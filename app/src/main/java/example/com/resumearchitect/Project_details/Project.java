package example.com.resumearchitect.Project_details;

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

public class Project extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProAdapter proAdapter;
    private final List<String> inputList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        recyclerView = findViewById(R.id.pro_recycler_view);
        proAdapter = new ProAdapter(inputList);
        recyclerView.setAdapter(proAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            inputList.add("");
            proAdapter.notifyItemInserted(inputList.size() - 1);
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onClick(View view) {

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View rview = recyclerView.getChildAt(i);
                    TextInputEditText project_name = rview.findViewById(R.id.name_of_project);
                    TextInputEditText tech_stk = rview.findViewById(R.id.tech_stk);
                    TextInputEditText project_desc = rview.findViewById(R.id.project_description);
                    TextInputEditText project_link = rview.findViewById(R.id.project_link);
                    String pn = project_name.getText().toString().trim();
                    String ts = tech_stk.getText().toString().trim();
                    String pd = project_desc.getText().toString().trim();
                    String pl = project_link.getText().toString().trim();
                    if (!TextUtils.isEmpty(pn)) {
                        editor.putString("project_name_" + i, pn);
                        editor.putString("tech_stk_" + i, ts);
                        editor.putString("project_desc_" + i, pd);
                        editor.putString("project_link_" + i, pl);
                    }
                }
                editor.putInt("num_items", recyclerView.getChildCount());
                editor.apply();

            }
        });
    }
}

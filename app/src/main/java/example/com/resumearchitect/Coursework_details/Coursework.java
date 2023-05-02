package example.com.resumearchitect.Coursework_details;

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

public class Coursework extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CwAdapter achAdapter;
    private final List<String> inputList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursework);

        recyclerView = findViewById(R.id.cw_recycler_view);
        achAdapter = new CwAdapter(inputList);
        recyclerView.setAdapter(achAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            inputList.add("");
            achAdapter.notifyItemInserted(inputList.size() - 1);
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onClick(View view) {

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View rview = recyclerView.getChildAt(i);
                    TextInputEditText crs_name = rview.findViewById(R.id.name_of_course);
                    TextInputEditText crs_ctf = rview.findViewById(R.id.course_certificate);
                    String cn = crs_name.getText().toString().trim();
                    String cc = crs_ctf.getText().toString().trim();
                    if (!TextUtils.isEmpty(cn)) {
                        editor.putString("name_of_course" + i, cn);
                        editor.putString("course_certificate_" + i, cc);
                    }
                }
                editor.putInt("num_items", recyclerView.getChildCount());
                editor.apply();

            }
        });
    }
}

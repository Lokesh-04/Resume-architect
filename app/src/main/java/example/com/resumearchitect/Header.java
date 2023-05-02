package example.com.resumearchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import example.com.resumearchitect.Education_details.Education;
import example.com.resumearchitect.Education_details.EducationData;

public class Header extends AppCompatActivity {

    TextInputEditText i_name,i_mob,i_email,i_lin;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        save = findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i_name = findViewById(R.id.edit_text_name);
                i_email = findViewById(R.id.edit_text_email);
                i_mob = findViewById(R.id.edit_text_mobile);
                i_lin = findViewById(R.id.edit_text_linkedin);
                // add data as extras to the Intent
                if(i_name!=null) {
                    String name = i_name.getText().toString();
                    String email = i_email.getText().toString();
                    String mobile = i_mob.getText().toString();
                    String linkedin = i_lin.getText().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("email", email);
                    editor.putString("mobile", mobile);
                    editor.putString("linkedin", linkedin);
                    editor.apply();
                }
                Intent intent = new Intent(Header.this, Education.class);
                startActivity(intent);


            }
        });



    }
}
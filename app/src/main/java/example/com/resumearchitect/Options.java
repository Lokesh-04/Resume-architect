package example.com.resumearchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import example.com.resumearchitect.Achievement_details.Achievements;
import example.com.resumearchitect.Coursework_details.Coursework;
import example.com.resumearchitect.Education_details.Education;
import example.com.resumearchitect.Experience_details.Experience;
import example.com.resumearchitect.Project_details.Project;
import example.com.resumearchitect.Skills_details.Skills;

public class Options extends AppCompatActivity {

    LinearLayout header,education,experience,project,achievements,skills,coursework;
    Button select_template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        header = findViewById(R.id.header_block);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Header.class);
                startActivity(intent);
            }
        });

        education = findViewById(R.id.education_block);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Education.class);
                startActivity(intent);
            }
        });

        experience = findViewById(R.id.experience_block);
        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Experience.class);
                startActivity(intent);
            }
        });

        project = findViewById(R.id.project_block);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Project.class);
                startActivity(intent);
            }
        });

        achievements = findViewById(R.id.achievements_block);
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Achievements.class);
                startActivity(intent);
            }
        });

        skills = findViewById(R.id.skills_block);
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Skills.class);
                startActivity(intent);
            }
        });

        coursework = findViewById(R.id.coursework_block);
        coursework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Coursework.class);
                startActivity(intent);
            }
        });

        select_template = findViewById(R.id.select_template);
        select_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Templates.class);
                startActivity(intent);
            }
        });

    }
}
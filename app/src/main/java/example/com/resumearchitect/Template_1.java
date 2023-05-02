package example.com.resumearchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import example.com.resumearchitect.Achievement_details.AchievementsData;
import example.com.resumearchitect.Coursework_details.CourseworkData;
import example.com.resumearchitect.Education_details.Education;
import example.com.resumearchitect.Education_details.EducationData;
import example.com.resumearchitect.Experience_details.ExperienceData;
import example.com.resumearchitect.Project_details.ProjectData;
import example.com.resumearchitect.Skills_details.Skills;
import example.com.resumearchitect.Skills_details.SkillsData;

public class Template_1 extends AppCompatActivity {

    String name,email,linkedin,mobile;
    String courseName,university,grade,passoutYear;
    List<EducationData> educationDataList = new ArrayList<>();

    String skillName;
    List<SkillsData> skillDataList = new ArrayList<>();

    String exp_name,exp_type,exp_time,desc;
    List<ExperienceData> experienceDataList = new ArrayList<>();

    String pro_name, tech_stk, pro_desc, pro_link;
    List<ProjectData> projectDataList = new ArrayList<>();

    String achievement;
    List<AchievementsData> AchievementDataList = new ArrayList<>();

    String course_name,course_certificate;
    List<CourseworkData> CourseworkDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template1);

        //Header details
        SharedPreferences sharedPreferences_head = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        name = sharedPreferences_head.getString("name", "");
        email = sharedPreferences_head.getString("email", "");
        mobile = sharedPreferences_head.getString("mobile", "");
        linkedin = sharedPreferences_head.getString("linkedin", "");

        //Education
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems = sharedPreferences.getInt("num_items", 0);
        for (int i = 0; i < numItems; i++) {
            courseName = sharedPreferences.getString("course_name_" + i, "");
            university = sharedPreferences.getString("university_" + i, "");
            passoutYear = sharedPreferences.getString("passout_" + i, "");
            grade = sharedPreferences.getString("grade_" + i, "");
            // Do something with the data

            EducationData item = new EducationData(courseName, university, passoutYear, grade);
            educationDataList.add(item);
        }

        //Skills
        SharedPreferences sharedPreferences_skills = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems_skills = sharedPreferences_skills.getInt("num_items", 0);
        for (int i = 0; i < numItems_skills; i++) {
            skillName = sharedPreferences_skills.getString("skill_" + i, "");
            SkillsData item = new SkillsData(skillName);
            skillDataList.add(item);
        }


        //Experience
        SharedPreferences sharedPreferences_exp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems_exp = sharedPreferences_exp.getInt("num_items", 0);
        for (int i = 0; i < numItems_exp; i++) {
            exp_name = sharedPreferences_exp.getString("name_of_experience" + i, "");
            exp_type = sharedPreferences_exp.getString("type_of_experience" + i, "");
            exp_time = sharedPreferences_exp.getString("timeline_of_experience_" + i, "");
            desc = sharedPreferences_exp.getString("description_" + i, "");
            // Do something with the data

            ExperienceData item = new ExperienceData(exp_name, exp_type, exp_time, desc);
            experienceDataList.add(item);
        }

        //Projects
        SharedPreferences sharedPreferences_pro = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems_pro = sharedPreferences_pro.getInt("num_items", 0);
        for (int i = 0; i < numItems_pro; i++) {
            pro_name = sharedPreferences_pro.getString("project_name_" + i, "");
            tech_stk = sharedPreferences_pro.getString("tech_stk_" + i, "");
            pro_desc = sharedPreferences_pro.getString("project_desc_" + i, "");
            pro_link = sharedPreferences_pro.getString("project_link_" + i, "");
            // Do something with the data

            ProjectData item = new ProjectData(pro_name, tech_stk, pro_desc, pro_link);
            projectDataList.add(item);
        }

        //Achievements
        SharedPreferences sharedPreferences_ach = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems_ach = sharedPreferences_ach.getInt("num_items", 0);
        for (int i = 0; i < numItems_ach; i++) {
            achievement = sharedPreferences_ach.getString("achievement_" + i, "");
            AchievementsData item = new AchievementsData(achievement);
            AchievementDataList.add(item);
        }

        //Coursework
        SharedPreferences sharedPreferences_crs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int numItems_crs = sharedPreferences_crs.getInt("num_items", 0);
        for (int i = 0; i < numItems_crs; i++) {
            course_name = sharedPreferences_crs.getString("name_of_course_" + i, "");
            course_certificate = sharedPreferences_crs.getString("course_certificate_" + i, "");
            CourseworkData item = new CourseworkData(course_name, course_certificate);
            CourseworkDataList.add(item);
        }




        Button downloadButton = findViewById(R.id.download_btn);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePDF();
            }
        });
    }

    private void generatePDF() {
        // Create a new PDF document using iText
        Document document = new Document();

        try {
            // Create a PdfWriter instance to write the PDF file
            String filePath = getExternalFilesDir(null).getAbsolutePath() + File.separator + "example.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the PDF document
            document.open();


            //header section

            // Add the heading to the PDF document
            Paragraph paraHeading = new Paragraph();
            paraHeading.setAlignment(Element.ALIGN_CENTER);
            paraHeading.setSpacingAfter(10);
            Chunk chunkHeading = new Chunk(name, new Font(Font.FontFamily.HELVETICA, 24));
            chunkHeading.setTextRenderMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE, 0.3f, null);
            paraHeading.add(chunkHeading);
            document.add(paraHeading);

                        //social media handles
            // Add the contact details to the PDF document
            Paragraph paraContactDetails = new Paragraph();

            // Add the mail address
            Chunk chunkMail = new Chunk("Mail: mail@example.com");
            chunkMail.setAnchor("mail@example.com");
            paraContactDetails.add(chunkMail);
            paraContactDetails.add(new Chunk(" | ")); // Add a space between the chunks

            // Add the mobile number
            Chunk chunkMobile = new Chunk("Mobile: 1234567890");
            chunkMobile.setAnchor("+91 1234567890");
            paraContactDetails.add(chunkMobile);
            paraContactDetails.add(new Chunk(" | ")); // Add a space between the chunks

            // Add the Facebook handle
            Chunk chunkFacebook = new Chunk("Facebook");
            chunkFacebook.setAnchor("https://www.facebook.com");
            paraContactDetails.add(chunkFacebook);
            paraContactDetails.add(new Chunk(" | ")); // Add a separator between the chunks

            // Add the Twitter handle
            Chunk chunkTwitter = new Chunk("Twitter");
            chunkTwitter.setAnchor("https://www.twitter.com");
            paraContactDetails.add(chunkTwitter);
            paraContactDetails.add(new Chunk(" | ")); // Add a separator between the chunks

            // Add the LinkedIn handle
            Chunk chunkLinkedIn = new Chunk("LinkedIn");
            chunkLinkedIn.setAnchor("https://www.linkedin.com");
            paraContactDetails.add(chunkLinkedIn);

//            paraContactDetails.setSpacingAfter(2);
            paraContactDetails.setAlignment(Element.ALIGN_CENTER);
            paraContactDetails.setSpacingAfter(10);
            document.add(paraContactDetails);

//            // Calculate the line width
//            float lineWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
//            // Add a line separator to the PDF document
//            Paragraph line = new Paragraph(new Chunk(new LineSeparator(0.5f, lineWidth, null, Element.ALIGN_CENTER, -1)));
//            line.setSpacingAfter(5);
//            document.add(line);



            //Education

            // create parent table
            PdfPTable parentTable = new PdfPTable(1);
            parentTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            parentTable.setWidthPercentage(100);
            parentTable.setSpacingBefore(10f);
            parentTable.setSpacingAfter(10f);



            // create nested table
            PdfPTable edu_table = new PdfPTable(2);
            edu_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            float[] columnWidths = {90f, 10f}; // Column widths as percentages of the table width
            edu_table.setWidths(columnWidths);
            edu_table.setWidthPercentage(100);
            edu_table.setSpacingAfter(10);

            PdfPCell education_h = new PdfPCell(new Phrase("Education", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            education_h.setColspan(2);
            education_h.setPaddingBottom(10);
            education_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            education_h.setBorder(Rectangle.NO_BORDER);
            edu_table.addCell(education_h);

            PdfPCell lineCell = new PdfPCell();
            lineCell.setColspan(2);
            lineCell.setBorder(Rectangle.NO_BORDER);
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setPercentage(100f);
            lineSeparator.setLineColor(BaseColor.BLACK);
            lineCell.addElement(lineSeparator);

            edu_table.addCell(lineCell);


            for (EducationData educationData : educationDataList) {
                PdfPCell nestedCell1 = new PdfPCell(new Phrase(educationData.getCourseName(), new Font(Font.FontFamily.HELVETICA, 12)));
                nestedCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                nestedCell1.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell1);

                PdfPCell nestedCell2 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                nestedCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                nestedCell2.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell2);

                PdfPCell nestedCell3 = new PdfPCell(new Phrase(educationData.getUniversity(), new Font(Font.FontFamily.HELVETICA, 11)));
                nestedCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                nestedCell3.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell3);

                PdfPCell nestedCell4 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                nestedCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                nestedCell4.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell4);

                PdfPCell nestedCell5 = new PdfPCell(new Phrase(educationData.getPassoutYear() + "                                                                       " + educationData.getGrade(), new Font(Font.FontFamily.HELVETICA, 10)));
                nestedCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
                nestedCell5.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell5);

                PdfPCell nestedCell6 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 10)));
                nestedCell6.setHorizontalAlignment(Element.ALIGN_LEFT);
                nestedCell6.setBorder(Rectangle.NO_BORDER);
                edu_table.addCell(nestedCell6);
            }

            // add nested table to parent table
            PdfPCell edu_cell = new PdfPCell(edu_table);
            edu_cell.setColspan(1);
            edu_cell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(edu_cell);


            //Experience

            // create nested table
            PdfPTable exp_table = new PdfPTable(1);
            exp_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//            float[] columnWidths = {90f, 10f}; // Column widths as percentages of the table width
//            nestedTable.setWidths(columnWidths);
            exp_table.setWidthPercentage(50);
            exp_table.setSpacingAfter(10);

            PdfPCell experience_h = new PdfPCell(new Phrase("Experience", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            experience_h.setColspan(2);
            experience_h.setPaddingBottom(10);
            experience_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            experience_h.setBorder(Rectangle.NO_BORDER);
            exp_table.addCell(experience_h);

            PdfPCell exp_line = new PdfPCell();
            exp_line.setColspan(2);
            exp_line.setBorder(Rectangle.NO_BORDER);
            LineSeparator exp_sep = new LineSeparator();
            exp_sep.setPercentage(100f);
            exp_sep.setLineColor(BaseColor.BLACK);
            exp_line.addElement(exp_sep);

            exp_table.addCell(exp_line);



            for (ExperienceData experienceData : experienceDataList) {
                PdfPCell exp_cell1 = new PdfPCell(new Phrase(experienceData.getExp_name(), new Font(Font.FontFamily.HELVETICA, 12)));
                exp_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                exp_cell1.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell1);

                PdfPCell exp_cell2 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                exp_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                exp_cell2.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell2);

                PdfPCell exp_cell3 = new PdfPCell(new Phrase(experienceData.getExp_type(), new Font(Font.FontFamily.HELVETICA, 11)));
                exp_cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                exp_cell3.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell3);

                PdfPCell exp_cell4 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                exp_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                exp_cell4.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell4);

                PdfPCell exp_cell5 = new PdfPCell(new Phrase(experienceData.getTimeline() + "                                  " +
                        "                                     " + experienceData.getDesc(), new Font(Font.FontFamily.HELVETICA, 10)));
                exp_cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
                exp_cell5.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell5);

                PdfPCell exp_cell6 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 10)));
                exp_cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
                exp_cell6.setBorder(Rectangle.NO_BORDER);
                exp_table.addCell(exp_cell6);
            }

            // add nested table to parent table
            PdfPCell exp_cell = new PdfPCell(exp_table);
            exp_cell.setColspan(1);
            exp_cell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(exp_cell);




            //Skills
            PdfPTable skillsTable = new PdfPTable(1);
            skillsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//            float[] skillsWidths = {90f, 10f}; // Column widths as percentages of the table width
//            nestedTable.setWidths(columnWidths);
            skillsTable.setWidthPercentage(50);
            skillsTable.setSpacingAfter(10);


            PdfPCell skills_h = new PdfPCell(new Phrase("Skills", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            skills_h.setColspan(2);
            skills_h.setPaddingBottom(10);
            skills_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            skills_h.setBorder(Rectangle.NO_BORDER);
            skillsTable.addCell(skills_h);

            PdfPCell sk_line = new PdfPCell();
            sk_line.setColspan(2);
            sk_line.setBorder(Rectangle.NO_BORDER);
            LineSeparator sk_sep = new LineSeparator();
            sk_sep.setPercentage(100f);
            sk_sep.setLineColor(BaseColor.BLACK);
            sk_line.addElement(sk_sep);

            skillsTable.addCell(sk_line);

            String skillstring = "";
            for (SkillsData skillData : skillDataList) {
                skillstring = skillData.getSkillName() + " • " + skillstring;
            }

            PdfPCell skillcell1 = new PdfPCell(new Phrase(skillstring, new Font(Font.FontFamily.HELVETICA, 12)));
            skillcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            skillcell1.setBorder(Rectangle.NO_BORDER);
            skillsTable.addCell(skillcell1);

            // add nested table to parent table
            PdfPCell skillCell = new PdfPCell(skillsTable);
            skillCell.setColspan(1);
            skillCell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(skillCell);


            //Projects

            // create nested table
            PdfPTable pro_table = new PdfPTable(1);
            pro_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//            float[] columnWidths = {90f, 10f}; // Column widths as percentages of the table width
//            nestedTable.setWidths(columnWidths);
            pro_table.setWidthPercentage(50);
            pro_table.setSpacingAfter(10);

            PdfPCell project_h = new PdfPCell(new Phrase("Projects", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            project_h.setColspan(2);
            project_h.setPaddingBottom(10);
            project_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            project_h.setBorder(Rectangle.NO_BORDER);
            pro_table.addCell(project_h);

            PdfPCell pro_line = new PdfPCell();
            pro_line.setColspan(2);
            pro_line.setBorder(Rectangle.NO_BORDER);
            LineSeparator pro_sep = new LineSeparator();
            pro_sep.setPercentage(100f);
            pro_sep.setLineColor(BaseColor.BLACK);
            pro_line.addElement(pro_sep);

            pro_table.addCell(pro_line);



            for (ProjectData projectData : projectDataList) {
                PdfPCell pro_cell1 = new PdfPCell(new Phrase(projectData.getPro_name(), new Font(Font.FontFamily.HELVETICA, 12)));
                pro_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pro_cell1.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell1);

                PdfPCell pro_cell2 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                pro_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                pro_cell2.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell2);

                PdfPCell pro_cell3 = new PdfPCell(new Phrase(projectData.getTech_stk(), new Font(Font.FontFamily.HELVETICA, 11)));
                pro_cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                pro_cell3.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell3);

                PdfPCell pro_cell4 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 10)));
                pro_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                pro_cell4.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell4);

                PdfPCell pro_cell5 = new PdfPCell(new Phrase(projectData.getPro_desc(), new Font(Font.FontFamily.HELVETICA, 10)));
                pro_cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                pro_cell5.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell5);

                PdfPCell pro_cell6 = new PdfPCell(new Phrase(projectData.getPro_link(), new Font(Font.FontFamily.HELVETICA, 10)));
                pro_cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                pro_cell6.setBorder(Rectangle.NO_BORDER);
                pro_table.addCell(pro_cell6);

//                PdfPCell exp_cell6 = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 10)));
//                exp_cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
//                exp_cell6.setBorder(Rectangle.NO_BORDER);
//                pro_table.addCell(exp_cell6);
            }

            // add nested table to parent table
            PdfPCell pro_cell = new PdfPCell(pro_table);
            pro_cell.setColspan(1);
            pro_cell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(pro_cell);


            //Achievements

            PdfPTable ach_table = new PdfPTable(1);
            ach_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//            float[] skillsWidths = {90f, 10f}; // Column widths as percentages of the table width
//            nestedTable.setWidths(columnWidths);
            ach_table.setWidthPercentage(50);
            ach_table.setSpacingAfter(10);


            PdfPCell ach_h = new PdfPCell(new Phrase("Achievements", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            ach_h.setColspan(2);
            ach_h.setPaddingBottom(10);
            ach_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            ach_h.setBorder(Rectangle.NO_BORDER);
            ach_table.addCell(ach_h);

            PdfPCell ach_line = new PdfPCell();
            ach_line.setColspan(2);
            ach_line.setBorder(Rectangle.NO_BORDER);
            LineSeparator ach_sep = new LineSeparator();
            ach_sep.setPercentage(100f);
            ach_sep.setLineColor(BaseColor.BLACK);
            ach_line.addElement(ach_sep);

            ach_table.addCell(ach_line);

            for (AchievementsData achievementsData : AchievementDataList) {
                PdfPCell ach_cell1 = new PdfPCell(new Phrase("○"+ achievementsData.getAch_name(), new Font(Font.FontFamily.HELVETICA, 12)));
                ach_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                ach_cell1.setBorder(Rectangle.NO_BORDER);
                ach_table.addCell(ach_cell1);
            }



            // add nested table to parent table
            PdfPCell ach_cell = new PdfPCell(ach_table);
            ach_cell.setColspan(1);
            ach_cell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(ach_cell);


            //CourseWork
            // with certifications..
            // create nested table
            PdfPTable crs_table = new PdfPTable(1);
            crs_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//            float[] columnWidths = {90f, 10f}; // Column widths as percentages of the table width
//            nestedTable.setWidths(columnWidths);
            crs_table.setWidthPercentage(50);

            PdfPCell crs_h = new PdfPCell(new Phrase("Coursework", new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD)));
            crs_h.setColspan(2);
            crs_h.setPaddingBottom(10);
            crs_h.setHorizontalAlignment(Element.ALIGN_LEFT);
            crs_h.setBorder(Rectangle.NO_BORDER);
            crs_table.addCell(crs_h);

            PdfPCell crs_line = new PdfPCell();
            crs_line.setColspan(2);
            crs_line.setBorder(Rectangle.NO_BORDER);
            LineSeparator crs_sep = new LineSeparator();
            crs_sep.setPercentage(100f);
            crs_sep.setLineColor(BaseColor.BLACK);
            crs_line.addElement(crs_sep);

            crs_table.addCell(crs_line);


            for (CourseworkData courseworkData : CourseworkDataList) {
                PdfPCell crs_cell1 = new PdfPCell(new Phrase(courseworkData.getCrs_name(), new Font(Font.FontFamily.HELVETICA, 12)));
                crs_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                crs_cell1.setBorder(Rectangle.NO_BORDER);
                crs_table.addCell(crs_cell1);

                PdfPCell crs_cell2 = new PdfPCell(new Phrase(courseworkData.getCrs_ctf(), new Font(Font.FontFamily.HELVETICA, 10)));
                crs_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                crs_cell2.setBorder(Rectangle.NO_BORDER);
                crs_table.addCell(crs_cell2);
            }

            // add nested table to parent table
            PdfPCell crs_cell = new PdfPCell(crs_table);
            crs_cell.setColspan(1);
            crs_cell.setBorder(Rectangle.NO_BORDER);
            parentTable.addCell(crs_cell);



            // add parent table to document
            document.add(parentTable);


            // Close the PDF document
            document.close();

            // Show a toast message to the user indicating the file location
            Toast.makeText(this, "PDF file saved to: " + filePath, Toast.LENGTH_LONG).show();

//            PDFView pdfView = findViewById(R.id.pdfView);
//            pdfView.fromFile(new File(filePath))
//                    .load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}

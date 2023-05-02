package example.com.resumearchitect.Education_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import example.com.resumearchitect.R;

public class EduAdapter extends RecyclerView.Adapter<EduAdapter.InputViewHolder> {

    private List<String> inputList;


    public EduAdapter(List<String> inputList) {
        this.inputList = inputList;
    }

    @NonNull
    @Override
    public InputViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);
        return new InputViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputViewHolder holder, int position) {
        String inputText = inputList.get(position);
        holder.cn.setText(inputText);
        holder.u.setText(inputText);
        holder.p.setText(inputText);
        holder.g.setText(inputText);
        holder.deleteButton.setOnClickListener(v -> {
            inputList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return inputList.size();
    }

    public static class InputViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText cn,u,p,g;
        ImageButton deleteButton;

        public InputViewHolder(@NonNull View itemView) {
            super(itemView);
            cn = itemView.findViewById(R.id.coursename);
            u = itemView.findViewById(R.id.university);
            p = itemView.findViewById(R.id.passout);
            g = itemView.findViewById(R.id.grade);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

}
package example.com.resumearchitect.Project_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import example.com.resumearchitect.R;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.InputViewHolder> {

    private List<String> inputList;

    public ProAdapter(List<String> inputList) {
        this.inputList = inputList;
    }

    @NonNull
    @Override
    public InputViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new InputViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputViewHolder holder, int position) {
        String inputText = inputList.get(position);
        holder.pro_name.setText(inputText);
        holder.tech_stk.setText(inputText);
        holder.pro_desc.setText(inputText);
        holder.pro_link.setText(inputText);
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

        TextInputEditText pro_name,tech_stk,pro_desc,pro_link;
        ImageButton deleteButton;

        public InputViewHolder(@NonNull View itemView) {
            super(itemView);
            pro_name = itemView.findViewById(R.id.name_of_project);
            tech_stk = itemView.findViewById(R.id.tech_stk);
            pro_desc = itemView.findViewById(R.id.project_description);
            pro_link = itemView.findViewById(R.id.project_link);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

}
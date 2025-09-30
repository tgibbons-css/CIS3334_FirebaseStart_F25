package cis3334.firebasestart_f25;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextItem;
    Button buttonPost;
    RecyclerView recyclerViewItems;
    ItemRecycleViewAdapter iItemRecycleViewAdapter;
    ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup ViewModel
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        editTextItem = findViewById(R.id.editTextItem);
        setupButtonPost();
        setupItemRecycleView();
    }

    private void setupButtonPost() {
        buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                Log.d("CIS 3334", "Updating the firebase data");
                String item = editTextItem.getText().toString();
                itemViewModel.addItem(item);
                iItemRecycleViewAdapter.notifyDataSetChanged();

            }
        });
    }

    private void setupItemRecycleView() {
        recyclerViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        iItemRecycleViewAdapter = new ItemRecycleViewAdapter(itemViewModel);
        recyclerViewItems.setAdapter(iItemRecycleViewAdapter);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
    }

}
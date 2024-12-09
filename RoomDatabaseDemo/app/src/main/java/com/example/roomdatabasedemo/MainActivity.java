package com.example.roomdatabasedemo;

import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        Button buttonAddUser = findViewById(R.id.buttonAddUser);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, adapter::submitList);

        buttonAddUser.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            int age = Integer.parseInt(editTextAge.getText().toString());
            userViewModel.insert(new User(name, age));
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userViewModel.searchUsers(s.toString()).observe(MainActivity.this, adapter::submitList);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}

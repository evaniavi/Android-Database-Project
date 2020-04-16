package gr.hua.dit.tableview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button save;
    DBcon handler;
    EditText nameField,lnameField,ageField,search_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new DBcon(MainActivity.this);
        save = findViewById(R.id.save);


        nameField = findViewById(R.id.name);
        lnameField = findViewById(R.id.lastname);
        ageField = findViewById(R.id.age);
        save = findViewById(R.id.save);
        saveData();
        search_name = findViewById(R.id.search);
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = search_name.getText().toString();
                Intent i =new Intent(view.getContext(),SecondActivity.class);
                i.putExtra("result",name);
                setResult(1,i);
                startActivity(i);
            }
        });


    }

    public void saveData(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             boolean isInserted = handler.insertData(nameField.getText().toString()
                        ,lnameField.getText().toString(),
                        ageField.getText().toString());

                if(isInserted == true){
                    Toast.makeText(MainActivity.this, "VALUES SAVED!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this,"ERROR:VALUES NOT SAVED", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}

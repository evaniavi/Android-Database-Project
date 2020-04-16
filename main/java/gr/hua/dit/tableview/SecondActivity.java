package gr.hua.dit.tableview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    DBcon db = new DBcon(SecondActivity.this);

    TextView id,text;

    ArrayList<String> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = findViewById(R.id.text);
        String name = getIntent().getStringExtra("result");

        if (name.isEmpty()) {
            text.append("you didn't give a name to search");
        } else {
            db.viewData(name, users);
            if (users.isEmpty()) {
                text.append("NO ONE WAS FOUND WITH THAT NAME");
            } else {
                int i,j;
                for (i = 0; i < users.size(); i=i+4) {

                    TableLayout table =  findViewById(R.id.table);



                        TableRow row=new TableRow(SecondActivity.this);


                        TextView id3 = new TextView(SecondActivity.this);
                        TextView name3 = new TextView(SecondActivity.this);
                        TextView surname3 = new TextView(SecondActivity.this);
                        TextView age3 = new TextView(SecondActivity.this);
                        j=i;
                        id3.setText(users.get(j));
                        j++;
                        name3.setText(users.get(j));
                        j++;
                        surname3.setText(users.get(j));
                        j++;
                        age3.setText(users.get(j));
                        table.addView(row);
                        row.addView(id3);
                        row.addView(name3);
                        row.addView(surname3);
                        row.addView(age3);



                }
            }
        }






        Button back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClassName("gr.hua.dit.tableview", "gr.hua.dit.tableview.MainActivity");
                startActivity(i);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String returnString = data.getStringExtra("result");
                id.setText(returnString);
            }
        }
    }


}
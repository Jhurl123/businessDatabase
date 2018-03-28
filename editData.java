package edu.dtcc.walldatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fwalls on 11/5/2017.
 */


public class editData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

        Button okayClick = (Button) findViewById(R.id.Okay);
        okayClick.setOnClickListener(new okayClick(this));

        Button cancelClick = (Button) findViewById(R.id.Cancel);
        cancelClick.setOnClickListener(new cancelClick(this));

        Bundle extras = getIntent().getExtras();
        String mode = extras.getString("Mode");

        if (mode.equals("EDIT")) {

            String recordID = extras.getString("RecordID");
            String name = extras.getString("Name");
            String title = extras.getString("Title");
            String company = extras.getString("Company");
            String address = extras.getString("Address");
            String email = extras.getString("Email");
            String phone = extras.getString("Phone");

            TextView recordIDText = (TextView) findViewById(R.id.RecordID);
            recordIDText.setText(recordID);

            EditText nameEdit = (EditText)findViewById(R.id.Name);
            nameEdit.setText(name, TextView.BufferType.EDITABLE);

            EditText titleEdit = (EditText)findViewById(R.id.Title);
            titleEdit.setText(title, TextView.BufferType.EDITABLE);

            EditText companyEdit = (EditText)findViewById(R.id.Company);
            companyEdit.setText(company, TextView.BufferType.EDITABLE);

            EditText addressEdit = (EditText)findViewById(R.id.Address);
            addressEdit.setText(address, TextView.BufferType.EDITABLE);

            EditText emailEdit = (EditText)findViewById(R.id.Email);
            emailEdit.setText(email, TextView.BufferType.EDITABLE);

            EditText phoneEdit = (EditText)findViewById(R.id.Phone);
            phoneEdit.setText(phone, TextView.BufferType.EDITABLE);
        }

        // Toast.makeText(getApplicationContext(),"Mode is: "+ mode,Toast.LENGTH_LONG).show();
    }

    // User clicked OK - Return data.
    private class okayClick implements View.OnClickListener {
        private editData activity;

        private okayClick(editData activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            TextView recordIDText = (TextView) findViewById(R.id.RecordID);
            String recordID = recordIDText.getText().toString();

            EditText nameEdit = (EditText)findViewById(R.id.Name);
            String name = nameEdit.getText().toString();

            EditText titleEdit = (EditText)findViewById(R.id.Title);
            String title = titleEdit.getText().toString();

            EditText companyEdit = (EditText)findViewById(R.id.Company);
            String company = companyEdit.getText().toString();

            EditText addressEdit = (EditText)findViewById(R.id.Address);
            String address = addressEdit.getText().toString();

            EditText emailEdit = (EditText)findViewById(R.id.Email);
            String email = emailEdit.getText().toString();

            EditText phoneEdit = (EditText)findViewById(R.id.Phone);
            String phone = phoneEdit.getText().toString();

            Intent intent=new Intent();
            intent.putExtra("RecordID", recordID);
            intent.putExtra("Name", name);
            intent.putExtra("Title", title);
            intent.putExtra("Company", company);
            intent.putExtra("Address", address);
            intent.putExtra("Email", email);
            intent.putExtra("Phone", phone);
            setResult(Activity.RESULT_OK ,intent);
            finish();
        }
    }

    // User click cancel. Return RESULT_CANCELED
    private class cancelClick implements View.OnClickListener {
        private editData activity;

        private cancelClick(editData activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
    }

}
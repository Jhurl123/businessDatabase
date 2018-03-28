package edu.dtcc.walldatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.dtcc.walldatabase.Card;

public class MainActivity extends AppCompatActivity {

    static final int ADD_RECORD = 1;  // The add record request code
    static final int EDIT_RECORD = 2;  // The edit record request code

    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvCards = (ListView) findViewById(R.id.list);

        //Allows the cards in listview to be selected?
        lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Card card = db.getCard(id);

                Intent intent = new Intent(getApplicationContext(), editData.class);
                String recID = String.valueOf(id);

                String name = card.getName();
                String title = card.getTitle();
                String company = card.getBusiness();
                String address = card.getAddress();
                String email = card.getEmail();
                String phone = card.getPhone();

                // Mode is EDIT
                intent.putExtra("Mode", "EDIT");
                intent.putExtra("RecordID", recID);
                intent.putExtra("Name", name);
                intent.putExtra("Title", title);
                intent.putExtra("Company", company);
                intent.putExtra("Address", address);
                intent.putExtra("Email", email);
                intent.putExtra("Phone", phone);

                // Mode is EDIT
                startActivityForResult(intent, EDIT_RECORD);
                //Toast.makeText(getBaseContext(), recID, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    // This switch structure is for the dropdown menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String message;

        switch (item.getItemId()) {
            case R.id.item1:

                Log.d("AddRecord: ", "Add ..");

                Intent intent = new Intent(getApplicationContext(), editData.class);

                // Mode is ADD - This is the only data that needs to be passed to the activity
                intent.putExtra("Mode", "ADD");

                startActivityForResult(intent, ADD_RECORD);

                break;

            case R.id.item2:

                Log.d("Count: ", "Getting number of cards...");
                message =  "Number of cards = " + Integer.toString(db.getCardCount());
                Log.d("Count: ", message);
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                break;

            case R.id.item3:

                Log.d("Reading: ", "Reading all cards...");

                // Get all cards
                String selectQuery = "SELECT * FROM " + db.getTableName();

                Cursor dbCursor;
                dbCursor = db.getCursor(selectQuery);

                CursorAdapter myAdapter = new DBCustomAdapter(this, dbCursor);

                // Find ListView to populate
                ListView lvItems = (ListView) findViewById(R.id.list);

                // Attach cursor adapter to the ListView
                lvItems.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();

                //Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();

                break;

//            case R.id.item4:

//              break;

//            case R.id.item5:

//              break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_RECORD) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //String address=data.getStringExtra("ADDRESS");
                String name=data.getStringExtra("Name");
                String title=data.getStringExtra("Title");
                String company=data.getStringExtra("Company");
                String address=data.getStringExtra("Address");
                String email=data.getStringExtra("Email");
                String phone=data.getStringExtra("Phone");

                db.addCard(new Card(name, title, company, address, email, phone));

            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Changes NOT Saved!", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == EDIT_RECORD) {
            if (resultCode == RESULT_OK) {
                if (resultCode == RESULT_OK) {

                    int recID = Integer.parseInt(data.getStringExtra("RecordID"));
                    String name = data.getStringExtra("Name");
                    String title = data.getStringExtra("Title");
                    String company = data.getStringExtra("Company");
                    String address = data.getStringExtra("Address");
                    String email = data.getStringExtra("Email");
                    String phone = data.getStringExtra("Phone");

                    Card card = new Card(recID, name, title, company, address, email,  phone);

                    Toast.makeText(getApplicationContext(), "This is where we update the record!!!", Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Changes NOT Saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

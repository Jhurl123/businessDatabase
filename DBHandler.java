package edu.dtcc.walldatabase;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version - Be careful with this!!  If you change the version number it will delete all of your data
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "cardInfo";
    // Cards table name
    private static final String TABLE = "cards";
    // Cards Table Columns names
    // vvvvvvvv ** This has to be called _id ** vvvvvvvv
    private static final String KEY_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_BUSINESS = "business";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PHONE = "phone";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_TITLE + " TEXT," + FIELD_BUSINESS + " TEXT,"
                + FIELD_ADDRESS + " TEXT," + FIELD_EMAIL + " TEXT,"
                + FIELD_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed - Be careful with this.  It will delete all of your data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Creating tables again
        onCreate(db);
    }

    // Adding new business card
    public void addCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, card.getName());
        values.put(FIELD_TITLE, card.getTitle());
        values.put(FIELD_BUSINESS, card.getBusiness());
        values.put(FIELD_ADDRESS, card.getAddress());
        values.put(FIELD_EMAIL, card.getEmail());
        values.put(FIELD_PHONE, card.getPhone());

        // Inserting Row
        db.insert(TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Getting one card
    public Card getCard(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[]{KEY_ID, FIELD_NAME, FIELD_TITLE, FIELD_BUSINESS,
                        FIELD_ADDRESS, FIELD_EMAIL,  FIELD_PHONE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // read row from table and create a card from the values.

        Card contact = new Card(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

        // return card
        return contact;
    }

    public Cursor getCursor(String selectQuery) {
        //String selectQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return cursor;
    }


    // Getting All Cards
    public List<Card> getAllCards() {
        List<Card> cardList = new ArrayList<Card>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Card card = new Card();
                card.setId(Integer.parseInt(cursor.getString(0)));
                card.setName(cursor.getString(1));
                card.setTitle(cursor.getString(2));
                card.setBusiness(cursor.getString(3));
                card.setAddress(cursor.getString(4));
                card.setEmail(cursor.getString(5));
                card.setPhone(cursor.getString(6));
                // Adding contact to list
                cardList.add(card);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cardList;
    }


    // Getting card Count
    public int getCardCount() {
        String countQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int recordCount = cursor.getCount();
        cursor.close();

        // return count
        return recordCount;
    }

    // Updating a card
    public int updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, card.getName()); // Name
        values.put(FIELD_TITLE, card.getTitle()); // Job Title
        values.put(FIELD_BUSINESS, card.getBusiness()); //Business Name
        values.put(FIELD_ADDRESS, card.getAddress()); //Address
        values.put(FIELD_EMAIL, card.getEmail()); //Email
        values.put(FIELD_PHONE, card.getPhone()); //Phone

        // updating row
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(card.getId())});
    }

    // Deleting a card
    public void deleteCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(card.getId()) });
        db.close();
    }

    public String getTableName() {
        return TABLE;
    }

    public String[] getFieldNames() {

        // todo - This needs to be converted to pull the names from the constants. 
        String[] fields = { "_id", "name" , "title" , "business" , "address", "email", "phone" };

        return fields;
    }
}
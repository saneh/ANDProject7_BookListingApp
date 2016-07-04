package in.lemonco.getbooklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class BooksListActivity extends AppCompatActivity implements GoogleBooksApiRequest.AsyncResponse {
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        Intent intent = getIntent();
        Log.i("INTENT RECEVED", "INTENT OK");
        String searchQuery = intent.getStringExtra("SEARCH_QUERY");

        GoogleBooksApiRequest booksApiRequest = new GoogleBooksApiRequest(this);
        booksApiRequest.delegate =this; //to set delegate/listener back to this class
        booksApiRequest.execute(searchQuery);





    }

    //this methods is call in onPostExecute() method of AsyncTask ( GoogleBooksApiRequest)
    public void processFinish(JSONObject jsonObject){
        try {
            books=new ArrayList<Book>();
            JSONArray jArray = jsonObject.getJSONArray("items");
            for(int i = 0; i < jArray.length(); i++){
                JSONObject volumeInfo = jArray.getJSONObject(i).getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                ArrayList<String> authors= new ArrayList<String>();  //List of authors
                JSONArray jsonAuthors = volumeInfo.getJSONArray("authors");
                for(int j =0; j< jsonAuthors.length(); j++){
                    authors.add(jsonAuthors.getString(j));
                }
                books.add(new Book(title,authors));
            }
            //set listview adapter
            ListView listView = (ListView)findViewById(R.id.books_list);
            BookAdapter bookAdapter = new BookAdapter(this,books);

            listView.setAdapter(bookAdapter);

        }catch(JSONException e)
        {
            Log.i("IN PROCESS FINISH","SOMETHING WRONG WITH PARSNING");
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_books_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

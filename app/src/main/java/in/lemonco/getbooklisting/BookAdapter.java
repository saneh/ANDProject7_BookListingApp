package in.lemonco.getbooklisting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Arrayadapter for book objects. returns the custom listItem view
 */
public class BookAdapter extends ArrayAdapter<Book> {
    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    private static class ViewHolder{
        TextView title;
        TextView authors;
    }
    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get data item for this position
        Book currentbook = getItem(position);
        ViewHolder viewHolder;  //view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.book_list_item_layout, parent, false);
            viewHolder.title=(TextView)convertView.findViewById(R.id.bookTitle);
            viewHolder.authors=(TextView)convertView.findViewById(R.id.bookAuthors);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Populate data into the template view using the data object
        viewHolder.title.setText(currentbook.getmTitle());
        viewHolder.authors.setText(currentbook.getmAuthors());

        return convertView;


    }


}

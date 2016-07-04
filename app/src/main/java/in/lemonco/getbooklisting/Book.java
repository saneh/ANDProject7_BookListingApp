package in.lemonco.getbooklisting;

import java.util.ArrayList;

/**
 * This class defines a book object , containing a tile and a list of authors
 */
public class Book {
    private String mTitle;
    private ArrayList<String> mAuthors;

    public Book(String title, ArrayList<String> authors){
        mTitle = title;
        mAuthors = authors;

    }
    //getter methods
    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthors() {
        String authors="";
        for(String author:mAuthors){
            authors += author +"\n";
        }
        return authors;
    }

}

package com.chccc.bible.chapterChooser;

import com.chccc.bible.BibleChapterChooserActivity;
import com.chccc.bible.R;
import com.chccc.bible.dto.BookDTO;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class CustomAutoCompleteTextChangedListener implements TextWatcher{

    public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
    Context context;
    
    public CustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
    }
    
    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        try{
            
            // if you want to see in the logcat what the user types
            Log.e(TAG, "User input: " + userInput);
    
            BibleChapterChooserActivity mainActivity = ((BibleChapterChooserActivity) context);
            
            // update the adapater
            mainActivity.adapter.notifyDataSetChanged();
            
            // get suggestions from the database
            BookDTO[] books = mainActivity.bookHandler.read(userInput.toString());
            
            // update the adapter
            mainActivity.adapter = new AutocompleteCustomArrayAdapter(mainActivity, R.layout.list_view_row, books);
            
            mainActivity.textView.setAdapter(mainActivity.adapter);
            
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

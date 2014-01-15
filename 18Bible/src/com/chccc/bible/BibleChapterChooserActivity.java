package com.chccc.bible;


import com.chccc.bible.db.BookHandler;
import com.chccc.bible.dto.BookDTO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.chccc.bible.chapterChooser.CustomAutoCompleteView;
import com.chccc.bible.chapterChooser.CustomAutoCompleteTextChangedListener;
import com.chccc.bible.chapterChooser.AutocompleteCustomArrayAdapter;

public class BibleChapterChooserActivity extends Activity {
	Button buttonHymnNumberSave;
	EditText textHymnNumber;
	
	public AutoCompleteTextView textView = null;
	public ArrayAdapter<BookDTO> adapter;
	
	public BookHandler bookH;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible_chapter_chooser2);
		
		
		bookH = new BookHandler(BibleChapterChooserActivity.this);
		
		// put sample data to database
        insertBookData();
		
		BookDTO[] books = new BookDTO[0];
		textView = (CustomAutoCompleteView) findViewById(R.id.toNumber);
		 
		textView.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
		
		textView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                
                RelativeLayout rl = (RelativeLayout) arg1;
                TextView tv = (TextView) rl.getChildAt(0);
                textView.setText(tv.getText().toString());
                
            }

        });
		 adapter = new AutocompleteCustomArrayAdapter(this, R.layout.list_view_row, books);
         
		 textView.setThreshold(1);

		 //	Set adapter to AutoCompleteTextView
		 textView.setAdapter(adapter);
		 
		buttonHymnNumberSave = (Button) findViewById(R.id.buttonHymnNumberSave);
		textHymnNumber = (EditText)findViewById(R.id.textHymnNumber);
		
		buttonHymnNumberSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", textHymnNumber.getText().toString());
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 public void insertBookData(){
	        // CREATE
	    	bookH.create( new BookDTO("创世纪", "csj", "01"));
	    	bookH.create( new BookDTO("出埃及记", "cajj", "02"));
	    	bookH.create( new BookDTO("利未记", "lwj", "03"));
	    	bookH.create( new BookDTO("民数记", "msj", "04"));
	    	bookH.create( new BookDTO("申命记", "smj", "05"));
	    	bookH.create( new BookDTO("约书亚记", "ysyj", "06"));
	    	bookH.create( new BookDTO("士师记", "ssj", "07"));
	    	bookH.create( new BookDTO("路得记", "ldj", "08"));
	    	bookH.create( new BookDTO("撒母耳记上", "smejs", "09"));
	    	bookH.create( new BookDTO("撒母耳记下", "smejx", "10"));
	    	bookH.create( new BookDTO("列王记上", "lwjs", "11"));
	    	bookH.create( new BookDTO("列王记下", "lwjx", "12"));
	    	bookH.create( new BookDTO("历代志上", "ldzs", "13"));
	    	bookH.create( new BookDTO("历代志下", "ldzx", "14"));
	    	bookH.create( new BookDTO("以斯拉记", "yslj", "15"));
	    	bookH.create( new BookDTO("尼希米记", "nxmj", "16"));
	    	bookH.create( new BookDTO("以斯帖记", "ystj", "17"));
	    	bookH.create( new BookDTO("约伯记", "ybj", "18"));
	    	bookH.create( new BookDTO("诗篇", "sp", "19"));
	    	bookH.create( new BookDTO("箴言", "zy", "20"));
	    	bookH.create( new BookDTO("传道书", "cds", "21"));
	    	bookH.create( new BookDTO("雅歌", "yg", "22"));
	    	bookH.create( new BookDTO("以赛亚书", "ysys", "23"));
	    	bookH.create( new BookDTO("耶利米书", "ylms", "24"));
	    	bookH.create( new BookDTO("耶利米哀歌", "ylmag", "25"));
	    	bookH.create( new BookDTO("以西结书", "yxjs", "26"));
	    	bookH.create( new BookDTO("但以理书", "dyls", "27"));
	    	bookH.create( new BookDTO("何西阿书", "hxas", "28"));
	    	bookH.create( new BookDTO("约珥书", "yes", "29"));
	    	bookH.create( new BookDTO("阿摩司书", "amss", "30"));
	    	bookH.create( new BookDTO("俄巴底亚书", "ebdys", "31"));
	    	bookH.create( new BookDTO("约拿书", "yns", "32"));
	    	bookH.create( new BookDTO("弥迦书", "mjs", "33"));
	    	bookH.create( new BookDTO("那鸿书", "nhs", "34"));
	    	bookH.create( new BookDTO("哈巴谷书", "hbgs", "35"));
	    	bookH.create( new BookDTO("西番雅书", "xfys", "36"));
	    	bookH.create( new BookDTO("哈该书", "hgs", "37"));
	    	bookH.create( new BookDTO("撒迦利亚书", "sjlys", "38"));
	    	bookH.create( new BookDTO("玛拉基书", "mljs", "39"));
	    	bookH.create( new BookDTO("马太福音", "mtfy", "40"));
	    	bookH.create( new BookDTO("马可福音", "mkfy", "41")); 
	    	bookH.create( new BookDTO("路加福音", "ljfy", "42"));
	    	bookH.create( new BookDTO("约翰福音", "yhfy", "43"));
	    	bookH.create( new BookDTO("使徒行传", "stxz", "44"));
	    	bookH.create( new BookDTO("罗马书", "lms", "45"));
	    	bookH.create( new BookDTO("哥林多前书", "gldqs", "46"));
	    	bookH.create( new BookDTO("哥林多后书", "gldhs", "47"));
	    	bookH.create( new BookDTO("加拉太书", "jlts", "48"));
	    	bookH.create( new BookDTO("以弗所书", "yfss", "49"));
	    	bookH.create( new BookDTO("腓立比书", "flbs", "50"));
	    	bookH.create( new BookDTO("歌罗西书", "glxs", "51"));
	    	bookH.create( new BookDTO("帖撒罗尼加前书", "tslnjqs", "52"));
	    	bookH.create( new BookDTO("帖撒罗尼加后书", "tslnjhs", "53"));
	    	bookH.create( new BookDTO("提摩太前书", "tmtqs", "54"));
	    	bookH.create( new BookDTO("提摩太后书", "tmths", "55"));
	    	bookH.create( new BookDTO("提多书", "tds", "56"));
	    	bookH.create( new BookDTO("腓立门书", "flbs", "57"));
	    	bookH.create( new BookDTO("希伯来书", "xbls", "58"));
	    	bookH.create( new BookDTO("雅各书", "ygs", "59"));
	    	bookH.create( new BookDTO("彼得前书", "bdqs", "60"));
	    	bookH.create( new BookDTO("彼得后书", "bdhs", "61"));
	    	bookH.create( new BookDTO("约翰一书", "yhys", "62"));
	    	bookH.create( new BookDTO("约翰二书", "yhes", "63"));
	    	bookH.create( new BookDTO("约翰三书", "yhss", "64"));
	    	bookH.create( new BookDTO("犹大书", "yds", "65"));
	    	bookH.create( new BookDTO("启示录", "qsl", "66"));
	    }
	    
	
}


package com.chccc.bible;

import com.chccc.bible.fragment.FragmentBookIndex;

import android.support.v4.app.Fragment;

public class BookIndexActivity extends FragmentActivityBuilder {

	protected Fragment createFragment() {
		return new FragmentBookIndex();
	}
}

package com.chccc.hymn;

import android.support.v4.app.Fragment;

// A fragment is a kind of sub-activity that you can add or
// remove from other activities while the activity continues
// to run. 

// We extend the FragmentActivity to be able to use Fragments
// on platforms prior to Android 3.0

public class MainActivity extends FragmentActivityBuilder {

	protected Fragment createFragment() {
		return new FragmentHymnHeaderList();
//		return null;
	}
}
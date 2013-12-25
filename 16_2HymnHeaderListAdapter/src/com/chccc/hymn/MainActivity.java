
package com.chccc.hymn;

import android.support.v4.app.Fragment;

public class MainActivity extends FragmentActivityBuilder {

	protected Fragment createFragment() {
		return new FragmentHymnHeaderList();
	}
}
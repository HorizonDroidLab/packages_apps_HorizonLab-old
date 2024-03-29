/*
 * Copyright (C) 2020 Project-Awaken
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.horizon.lab;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.*;

import androidx.preference.*;
import androidx.recyclerview.widget.*;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;

import com.android.internal.logging.nano.MetricsProto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorizonDashboard extends SettingsPreferenceFragment {
	
	@Override
	public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.horizon_dashboard);
    }
	
	@Override
	public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.HORIZON;
    }
	
	@Override
	public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup container, Bundle icicle) {
		RecyclerView rcv = super.onCreateRecyclerView(inflater, container, icicle);
		GridLayoutManager layoutG = new GridLayoutManager(getActivity(), 2);
		layoutG.setSpanSizeLookup(new SpanSizeLookupG());
		rcv.setLayoutManager(layoutG);
		return rcv;
	}
	
	class SpanSizeLookupG extends GridLayoutManager.SpanSizeLookup {
		@Override
		public int getSpanSize(int position) {
		    if (position == 0 || position == 1) {
				return 2;
			} else {
				return 1;
			}
		}
	}
	
	public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(
                        Context context, boolean enabled) {
                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.horizon_dashboard;
                    return Arrays.asList(sir);
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    final List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
                }
            };
	
}
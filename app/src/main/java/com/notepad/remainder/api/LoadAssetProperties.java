package com.notepad.remainder.api;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadAssetProperties {

	public Properties loadRESTApiFile(Resources resources, String filename,
                                      Context context) {

		AssetManager assetManager = resources.getAssets();
		try {
			InputStream inputStream = assetManager.open(filename);
			Properties properties = new Properties();
			properties.load(inputStream);
			return properties;
		} catch (IOException e) {
			System.err.println("Failed");
			e.printStackTrace();
		}
		return null;
	}
}

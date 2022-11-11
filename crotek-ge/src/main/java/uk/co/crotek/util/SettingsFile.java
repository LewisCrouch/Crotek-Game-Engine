package uk.co.crotek.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsFile
{
	private String path;

	private Map<String, String> config;

	public SettingsFile(String path)
	{
		this.path = path;
		this.config = new HashMap<String, String>();
	}

	public String getString(String key, String defaultValue)
	{
		if(this.config.containsKey(key)) return this.config.get(key);

		this.config.put(key, defaultValue);
		this.saveFile();
		return defaultValue;
	}

	public int getInt(String key, int defaultValue)
	{
		if(this.config.containsKey(key))
		{
			String value = this.config.get(key);
			try
			{
				int val = Integer.parseInt(value);
				return val;
			}
			catch(Exception ex)
			{
				this.config.put(key, defaultValue + "");
				this.saveFile();
				return defaultValue;
			}
		}

		this.config.put(key, defaultValue + "");
		this.saveFile();
		return defaultValue;
	}

	public boolean getBoolean(String key, boolean defaultValue)
	{
		if(this.config.containsKey(key))
		{
			String value = this.config.get(key);
			try
			{
				boolean val = Boolean.parseBoolean(value);
				return val;
			}
			catch(Exception ex)
			{
				this.config.put(key, defaultValue + "");
				this.saveFile();
				return defaultValue;
			}
		}

		this.config.put(key, defaultValue + "");
		this.saveFile();
		return defaultValue;
	}

	public float getFloat(String key, float defaultValue)
	{
		if(this.config.containsKey(key))
		{
			String value = this.config.get(key);
			try
			{
				float val = Float.parseFloat(value);
				return val;
			}
			catch(Exception ex)
			{
				this.config.put(key, defaultValue + "");
				this.saveFile();
				return defaultValue;
			}
		}

		this.config.put(key, defaultValue + "");
		this.saveFile();
		return defaultValue;
	}

	public double getDouble(String key, double defaultValue)
	{
		if(this.config.containsKey(key))
		{
			String value = this.config.get(key);
			try
			{
				double val = Double.parseDouble(value);
				return val;
			}
			catch(Exception ex)
			{
				this.config.put(key, defaultValue + "");
				this.saveFile();
				return defaultValue;
			}
		}

		this.config.put(key, defaultValue + "");
		this.saveFile();
		return defaultValue;
	}

	public boolean saveFile()
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(this.path));

			for(String key : this.config.keySet())
			{
				writer.write(key + "=" + this.config.get(key));
				writer.newLine();
			}
			return true;
		}
		catch(Exception ex)
		{
			Logger.err("Failed to save config file '" + this.path + "'!");
			ex.printStackTrace();
			return false;
		}
		finally
		{
			if(writer != null)
			{
				try
				{
					writer.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public boolean loadFile()
	{
		if(!new File(this.path).exists()) return false;

		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(this.path));

			String line = null;
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split("=");
				this.config.put(parts[0], parts[1]);
			}

			return true;
		}
		catch(Exception ex)
		{
			Logger.err("Failed to load config file '" + this.path + "'!");
			ex.printStackTrace();
			return false;
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}

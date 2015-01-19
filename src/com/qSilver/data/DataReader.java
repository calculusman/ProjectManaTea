package com.qSilver.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class DataReader
{
	protected File file;
	protected BufferedReader reader;

	public DataReader(File parFile)
	{
		this.file = parFile;
	}

	public DataReader(String parDirectory, File parFile)
	{
		this(parFile);
		new File(parDirectory).mkdirs();
	}

	public DataReader(String parDirectory, String parFile)
	{
		this(parDirectory, new File(parDirectory + "/" + parFile));
	}

	public abstract void read();

	protected void begin() throws FileNotFoundException
	{
		if (!this.file.exists())
		{
			throw new FileNotFoundException("Can't find file: " + this.file.getAbsolutePath());
		}

		this.reader = new BufferedReader(new FileReader(this.file));
	}

	protected void end() throws IOException
	{
		this.reader.close();
	}

	public boolean hasData()
	{
		return this.file.exists();
	}
}

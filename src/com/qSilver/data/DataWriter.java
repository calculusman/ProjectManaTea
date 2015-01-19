package com.qSilver.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DataWriter
{
	protected File file;
	protected BufferedWriter writer;

	public DataWriter(File parFile)
	{
		this.file = parFile;
	}

	public DataWriter(String parDirectory, File parFile)
	{
		this(parFile);
		new File(parDirectory).mkdirs();
	}

	public DataWriter(String parDirectory, String parFile)
	{
		this(parDirectory, new File(parDirectory + "/" + parFile));
	}

	public abstract void write();

	protected void begin() throws FileNotFoundException, IOException
	{
		if (this.file.exists()) this.file.delete();
		if (!this.file.createNewFile()) throw new FileNotFoundException("Unable to create file: " + this.file.getAbsolutePath());

		this.writer = new BufferedWriter(new FileWriter(this.file));
	}

	protected void end() throws IOException
	{
		this.writer.close();
	}
}

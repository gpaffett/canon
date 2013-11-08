package com.citi.prepaid.sandbox;

//Note: This class won't compile by design!
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.zip.ZipFile;

public class ListOfNumbers
{

  private List<Integer>    list;
  private Vector<Integer>  victor;
  private static final int SIZE = 10;

  public ListOfNumbers()
  {
    list = new ArrayList<Integer>(SIZE);
    for (int i = 0; i < SIZE; i++)
    {
      list.add(new Integer(i));
    }
  }

  public void writeList() throws IOException
  {
    PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));

    for (int i = 0; i < SIZE; i++)
    {
      out.println("Value at: " + i + " = " + list.get(i));
    }
    out.close();
  }

  public void readList(String fileName)
  {
    String line = null;
    try
    {
      RandomAccessFile raf = new RandomAccessFile(fileName, "r");
      while ((line = raf.readLine()) != null)
      {
        Integer i = new Integer(Integer.parseInt(line));
        System.out.println(i);
        victor.addElement(i);
      }
      raf.close();
    } catch (FileNotFoundException fnf)
    {
      System.err.println("File: " + fileName + " not found.");
    } catch (IOException io)
    {
      System.err.println(io.toString());
    }

  }

  public static void writeToFileZipFileContents(String zipFileName,
      String outputFileName) throws IOException
  {

    Charset charset = StandardCharsets.US_ASCII;
    Path outputFilePath = Paths.get(outputFileName);

    // Open zip file and create output file with
    // try-with-resources statement

    try (ZipFile zf = new ZipFile(zipFileName);
        BufferedWriter writer = Files
            .newBufferedWriter(outputFilePath, charset))
    {
      // Enumerate each entry
      for (Enumeration entries = zf.entries(); entries.hasMoreElements();)
      {
        // Get the entry name and write it to the output file
        String newLine = System.getProperty("line.separator");
        String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement())
            .getName() + newLine;
        writer.write(zipEntryName, 0, zipEntryName.length());
      }
    }
  }

}
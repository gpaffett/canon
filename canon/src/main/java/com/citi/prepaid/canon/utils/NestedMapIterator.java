package com.citi.prepaid.canon.utils;

import java.util.HashMap;
import java.util.Map;

public class NestedMapIterator
{

	public void printMap(Map<String,Object> map)
	{
	  for (Map.Entry entry : map.entrySet())
	  {
        printEntry(entry);
	  }
	}

	private void printEntry (Map.Entry entry)
	{
		Object value = entry.getValue();
		if (  value instanceof Map )
		{
			System.out.print("-" + entry.getKey() + "\n\t");			
			Map<String, Object> map = (Map<String, Object>)value;
      printMap( map );
		}
		else
		{
			System.out.println(entry.getKey() + " = " +  entry.getValue());
		}

	}
}

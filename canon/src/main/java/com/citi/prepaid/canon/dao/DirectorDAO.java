package com.citi.prepaid.canon.dao;

import java.net.URI;
import java.util.Map;

public interface DirectorDAO
{
  public Map<String,Object> getDirectorInformation(URI uri, String key);
}

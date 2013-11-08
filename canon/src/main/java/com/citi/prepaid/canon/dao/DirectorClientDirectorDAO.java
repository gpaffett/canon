package com.citi.prepaid.canon.dao;

import java.net.URI;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ecount.Core2.director.client.DirectorClientFactory;
import com.ecount.Core2.director.client.DirectorClientFactory.DirectorClientTypes;
import com.ecount.Core2.director.client.IDirectorClient;

/**
 * @author gp58112
 *
 */
public class DirectorClientDirectorDAO implements DirectorDAO
{

	protected static Log log = LogFactory.getLog(DirectorClientDirectorDAO.class);

	@Override
	public Map<String, Object> getDirectorInformation(URI uri, String key)
	{
      IDirectorClient directorClient = DirectorClientFactory.getClient(DirectorClientTypes.XMLRPC);

      if (null == directorClient )
      {
        log.error("Unable to create Director Client");
      }

	  return directorClient.get( uri, key, null);
	}

}

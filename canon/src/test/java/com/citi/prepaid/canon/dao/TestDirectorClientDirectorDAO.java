package com.citi.prepaid.canon.dao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import junit.framework.TestCase;

import com.citi.prepaid.canon.dao.DirectorClientDirectorDAO;
import com.citi.prepaid.canon.display.AWTDisplayDirectorData;
import com.citi.prepaid.canon.utils.NestedMapIterator;

public class TestDirectorClientDirectorDAO extends TestCase {

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testGetDirectorInformation()
	{
		DirectorClientDirectorDAO directorDAO = new DirectorClientDirectorDAO();

        try
        {
        	URI uri = new URI("http://ppamwdcpidsvc1.nam.nsroot.net/service/dispatch.asp");
        	Map<String, Object> results = directorDAO.getDirectorInformation(uri, "");
            assertNotNull(results);

            NestedMapIterator printer = new NestedMapIterator();

            printer.printMap(results);
        }
        catch (URISyntaxException ue)
        {
        	System.out.println(ue.getMessage());
        	fail("URI Syntax exception");
        }

	}

}

package com.citi.prepaid.canon.utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.URI;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.citi.prepaid.canon.dao.DirectorClientDirectorDAO;
import com.citi.prepaid.canon.display.AWTDisplayDirectorData;
import com.ecount.Core2.director.client.IDirectorClient;

public class DirectorDisplay
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
        DirectorClientDirectorDAO directorDAO = new DirectorClientDirectorDAO();
        AWTDisplayDirectorData directorDisplay = new AWTDisplayDirectorData();
        String directorURI = "http://ppamwdcpddsvc1.nam.nsroot.net/service/dispatch.asp";
        String key = IDirectorClient.SYSTEM_KEY;
        try
        {
          UIManager
              .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          URI uri = new URI(directorURI);
          Map<String, Object> results = directorDAO.getDirectorInformation(uri, "");

          DefaultMutableTreeNode root = new DefaultMutableTreeNode(key);

          JFrame frame = new JFrame(directorURI);

          directorDisplay.createTree(results, root);

          JTree tree = new JTree(root);

          DefaultMutableTreeNode file = root.getLastLeaf();
          //TreePath path = new TreePath(file.getPath());
          //tree.setSelectionPath(path);
          JScrollPane scroll = new JScrollPane(tree);
          Container c = frame.getContentPane();
          c.add(scroll, BorderLayout.CENTER);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(350, 300);
          frame.setVisible(true);


        } catch (Exception evt)
        {
        }

  }

}

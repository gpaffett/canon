package com.citi.prepaid.canon.display;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.URI;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.citi.prepaid.canon.dao.DirectorClientDirectorDAO;

public class AWTDisplayDirectorData
{

  public void createTree(Map<String,Object> map, DefaultMutableTreeNode node)
  {

    for (Map.Entry entry : map.entrySet())
    {
      addNode(entry, node);
    }

  }

  private void addNode(Map.Entry entry, DefaultMutableTreeNode node)
  {
    Object value = entry.getValue();
    if ( value instanceof Map )
    {
      DefaultMutableTreeNode subNode = new DefaultMutableTreeNode( entry.getKey() );
      node.add(subNode);
      createTree((Map<String, Object>)value, subNode);
    }
    else
    {
      node.add(new DefaultMutableTreeNode(entry.getKey()+ " = " + entry.getValue()) );
    }

  }

}
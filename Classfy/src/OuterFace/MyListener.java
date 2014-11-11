package OuterFace;

import java.awt.Color;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class MyListener implements TreeSelectionListener,MouseListener{
	
	
	
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)e.getNewLeadSelectionPath().getLastPathComponent();
	    System.out.println("What happen?");
		if(node==null){
			return;
		}
		if(node.isLeaf()){
			
			System.out.println("maybe?");
			System.out.println(e.getPath().toString());
		}
	}
	/**
	 MouseListener ml = new MouseAdapter() {
	     public void mousePressed(MouseEvent e) {
	         int selRow = tree.getRowForLocation(e.getX(), e.getY());
	         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
	         if(selRow != -1) {
	             if(e.getClickCount() == 1) {
	                 mySingleClick(selRow, selPath);
	             }
	             else if(e.getClickCount() == 2) {
	                 myDoubleClick(selRow, selPath);
	             }
	         }
	     }
	 };
	 //tree.addMouseListener(ml);
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
   	    
}

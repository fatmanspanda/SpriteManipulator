package SpriteManipulator;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * Need alternative confirm messages
 */
public class BetterJFileChooser extends JFileChooser {
	private static final long serialVersionUID = -7581065406880416887L;

	public BetterJFileChooser() {
		super();
		this.setFileView(new SpritePreview());
	}

	public void approveSelection() {
		File f = getSelectedFile();
		if(f.exists() && getDialogType() == SAVE_DIALOG) {
			String warning;
			String warningHeader;
			if (SpriteManipulator.testFileType(f.getName(), "sfc")) {
				warning =
						"Are you sure you want to patch \"" + f.getName() + "\"?\n"+
						"This cannot be undone.";
				warningHeader = "This is a ROM";
			} else {
				warning = "The file \"" + f.getName() + "\" already exists. Save anyway?";
				warningHeader = "Existing file";
			}
			int result = JOptionPane.showConfirmDialog(
					this,
					warning,
					warningHeader,
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
				case JOptionPane.YES_OPTION :
					super.approveSelection();
					return;
				case JOptionPane.NO_OPTION :
				case JOptionPane.CLOSED_OPTION :
					return;
				case JOptionPane.CANCEL_OPTION:
					cancelSelection();
					return;
			}
		}
		super.approveSelection();
	}

	public void removeAllFilters() {
		FileFilter[] exlist = this.getChoosableFileFilters();
		for (FileFilter r : exlist) {
			this.removeChoosableFileFilter(r);
		}
	}
}
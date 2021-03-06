package org.reldb.dbrowser.ui.content.rel.var;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CTabItem;
import org.reldb.dbrowser.ui.content.rel.DbTreeAction;
import org.reldb.dbrowser.ui.content.rel.DbTreeItem;
import org.reldb.dbrowser.ui.content.rel.RelPanel;
import org.reldb.rel.client.Connection.ExecuteResult;

public class VarDropper extends DbTreeAction {

	public VarDropper(RelPanel relPanel) {
		super(relPanel);
	}

	@Override
	public void go(DbTreeItem item, String imageName) {
		CTabItem tab = relPanel.getTab(item);
		if (tab != null) {
			relPanel.getTabFolder().setSelection(tab);
			MessageDialog.openInformation(relPanel.getShell(), "Note", "You must close the '" + item.getTabName() + "' tab first.");
		} else {
			if (!MessageDialog.openConfirm(relPanel.getShell(), "Confirm DROP", "Are you sure you wish to drop var " + item.getName() + "?"))
				return;
			ExecuteResult result = relPanel.getConnection().execute("DROP VAR " + item.getName() + ";");
			if (result.failed())
				MessageDialog.openError(relPanel.getShell(), "Error", "Unable to drop var " + item.getName() + ": " + result.getErrorMessage());
			else
				relPanel.redisplayed();
		}
	}

}

package org.reldb.dbrowser.ui.content.rel.var;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.reldb.dbrowser.commands.CommandActivator;
import org.reldb.dbrowser.commands.Commands;
import org.reldb.dbrowser.commands.ManagedToolbar;
import org.reldb.dbrowser.ui.content.rel.var.grids.RelvarEditor;

public class VarEditorToolbar extends ManagedToolbar {

	public VarEditorToolbar(Composite parent, RelvarEditor relvarEditor) {
		super(parent);

		addAdditionalItemsBefore(this);

		new CommandActivator(Commands.Do.Refresh, this, "arrow_refresh", SWT.PUSH, "Refresh", e -> relvarEditor.refresh());
		new CommandActivator(null, this, "table_row_insert", SWT.PUSH, "Go to INSERT row", e -> relvarEditor.goToInsertRow());
		new CommandActivator(null, this, "table_row_delete", SWT.PUSH, "DELETE selected tuples", e -> relvarEditor.askDeleteSelected());
	}

	/** Override to add additional toolbar items before the default items. */
	protected void addAdditionalItemsBefore(VarEditorToolbar toolbar) {
	}
}

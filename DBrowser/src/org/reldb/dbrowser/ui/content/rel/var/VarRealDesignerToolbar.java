package org.reldb.dbrowser.ui.content.rel.var;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.reldb.dbrowser.commands.Commands;
import org.reldb.dbrowser.commands.ManagedToolbar;
import org.reldb.dbrowser.ui.content.rel.var.grids.RelvarDesigner;

public class VarRealDesignerToolbar extends ManagedToolbar {

	public VarRealDesignerToolbar(Composite parent, RelvarDesigner relvarDesigner) {
		super(parent);

		addItem(Commands.Do.Refresh, "Refresh", "arrow_refresh", SWT.PUSH).addListener(SWT.Selection, e -> relvarDesigner.refresh());
		addItem(null, "DELETE selected tuples", "table_row_delete", SWT.PUSH).addListener(SWT.Selection,
				e -> relvarDesigner.askDeleteSelected());
	}

}

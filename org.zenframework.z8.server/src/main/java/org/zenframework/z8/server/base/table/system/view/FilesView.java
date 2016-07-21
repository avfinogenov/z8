package org.zenframework.z8.server.base.table.system.view;

import org.zenframework.z8.server.base.table.system.Files;
import org.zenframework.z8.server.runtime.IObject;

public class FilesView extends Files {
	public static class CLASS<T extends FilesView> extends Files.CLASS<T> {
		public CLASS() {
			this(null);
		}

		public CLASS(IObject container) {
			super(container);
			setJavaClass(FilesView.class);
		}

		@Override
		public Object newObject(IObject container) {
			return new FilesView(container);
		}
	}

	static public Files newInstance() {
		return new Files.CLASS<Files>().get();
	}

	public FilesView(IObject container) {
		super(container);
	}

	@Override
	public void constructor2() {
		super.constructor2();

		registerFormField(createdAt);
		registerFormField(name);
		registerFormField(description);
		registerFormField(path);
	}
}
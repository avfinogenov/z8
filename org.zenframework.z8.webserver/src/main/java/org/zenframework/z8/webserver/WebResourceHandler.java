package org.zenframework.z8.webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.zenframework.z8.server.utils.IOUtils;

public class WebResourceHandler {

	protected static final String CLASSPATH_WEBAPP = "web";
	protected static final String WELCOME_FILE = "index.html";
	protected static final String RESOURCE_CACHE = "webcache";

	private File resourceCache;
	private File webapp;

	public void init(File work, File webapp) {
		this.resourceCache = new File(work, RESOURCE_CACHE);
		this.webapp = webapp;
	}

	public void handle(String path, HttpServletResponse response) throws IOException {
		File file = getFile(path);

		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				if (!path.endsWith("/")) {
					response.sendRedirect(path + '/');
					return;
				} else {
					file = getFile(path + WELCOME_FILE);
				}
			}
			if (file.exists()) {
				copy(new FileInputStream(file), response.getOutputStream());
				return;
			}
		}

		response.sendError(HttpServletResponse.SC_NOT_FOUND, "File " + path + " not found");
	}

	protected File getFile(String path) throws IOException {
		File file = new File(webapp, path);
		if (file.exists())
			return file;

		file = new File(resourceCache, path);
		if (file.exists())
			return file;

		URL alternate = getAlternateResource(path);
		if (alternate != null) {
			InputStream in = alternate.openStream();
			if (in.available() == 0) {
				// Is directory
				IOUtils.closeQuietly(in);
				file.mkdirs();
			} else {
				// Cache alternate to file
				file.getParentFile().mkdirs();
				IOUtils.copy(alternate.openStream(), new FileOutputStream(file));
			}
			return file;
		}

		return null;
	}

	protected URL getAlternateResource(String path) throws IOException {
		ClassLoader classLoader = WebServer.class.getClassLoader();
		path = FilenameUtils.concat(CLASSPATH_WEBAPP, path.isEmpty() ? path : path.substring(1));
		return classLoader.getResource(path);
	}

	protected void copy(InputStream in, OutputStream out) throws IOException {
		IOUtils.copy(in, out);
	}

}

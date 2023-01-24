package me.emsockz.rosecg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;

import me.emsockz.rosecg.gui.GUI;


public class Main {
	
	public static GUI gui;
	public static String defaultPath;

	public static void main(String[] args) {
		gui = new GUI();
		gui.setVisible(true);
		gui.revalidate();
		
		defaultPath = getDefaultPath()+File.separator;
		
		createDefaultFiles();

	}
	
	public static void createDefaultFiles() {
		File files = new File(defaultPath+"files"+File.separator);
		File output = new File(defaultPath+"output"+File.separator);
		File output_png = new File(defaultPath+"output"+File.separator+"png"+File.separator);
		
		if (!files.exists()) files.mkdirs();
		if (!output.exists()) output.mkdirs();	
		if (!output_png.exists()) output_png.mkdirs();	
		saveResource("files/line.png", false);
		saveResource("files/arrow.png", false);
		saveResource("output/compass.json", false);
		saveResource("output/symbols.yml", false);
	}
	
	public static String getDefaultPath() {
		CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try { jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) { e.printStackTrace(); }
		
		return jarFile.getParentFile().getPath();
	}
	
	 public static void saveResource(String resourcePath, boolean replace) {
	        if (resourcePath == null || resourcePath.equals("")) {
	            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
	        }

	        resourcePath = resourcePath.replace('\\', '/');
	        InputStream in = getResource(resourcePath);
	        if (in == null) {
	            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + defaultPath);
	        }

	        File outFile = new File(defaultPath, resourcePath);
	        int lastIndex = resourcePath.lastIndexOf('/');
	        File outDir = new File(defaultPath, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

	        if (!outDir.exists()) {
	            outDir.mkdirs();
	        }

	        try {
	            if (!outFile.exists() || replace) {
	                OutputStream out = new FileOutputStream(outFile);
	                byte[] buf = new byte[1024];
	                int len;
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	                out.close();
	                in.close();
	            } else {
	            	System.out.println("Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
	            }
	        } catch (IOException ex) {
	            System.out.println("Could not save " + outFile.getName() + " to " + outFile);
	        }
	    }
	 
    public static InputStream getResource(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = Main.class.getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }
}

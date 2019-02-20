

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filter extends FileFilter{
	private String ext;
	private String description;

	public Filter(String ext, String description){
		this.ext = ext;
		this.description = description;
	}
	@Override
	public String getDescription() {
		return this.description;
	}
	@Override
	public boolean accept(File f) {
		return f.getName().toLowerCase().endsWith (this.ext) || f.isDirectory();
	}
}
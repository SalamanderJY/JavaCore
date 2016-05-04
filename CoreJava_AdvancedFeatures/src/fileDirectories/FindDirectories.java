package fileDirectories;

import java.io.File;
import java.io.IOException;

public class FindDirectories {
    public static void main(String[] args) {
		// if no arguments provides, start at a parent directory.
    	if(args.length == 0)
    		args = new String[] {
    			".."
    	};
    	
    	try {
    		File pathName = new File(args[0]);
    		String[] fileNames = pathName.list();
    		
    		// enumerate all files in the directory.
    		for (int i = 0; i < fileNames.length; i++) {
				File f = new File(pathName.getPath(), fileNames[i]);
				
				// if the file is again a directory, call the main method recursively.
				if (f.isDirectory()) {
					System.out.println(f.getCanonicalPath());
					main (new String[] { f.getPath() });
				}
			}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}

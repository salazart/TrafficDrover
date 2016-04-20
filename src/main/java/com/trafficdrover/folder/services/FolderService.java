package com.trafficdrover.folder.services;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.trafficdrover.utils.PropService;

public class FolderService {
	private static final String PATH_FOLDER = "folder";
	
	private String pathFolder = PropService.getValue(PATH_FOLDER);
	
	public List<File> getFiles(){
		File folder = new File(pathFolder);
		File[] files = folder.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				String[] fileExtensions = {"xml", "xls", "xlsx"};
				String fileExtension = StringUtils.substringAfterLast(name, ".");
				if(Arrays.asList(fileExtensions).contains(fileExtension)){
					return true;
				} else {
					return false;
				}
			}
		});
		return Arrays.asList(files);
	}
}

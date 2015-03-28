package org.openhab.core.jsr223.internal.engine.scriptmanager;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptUpdateWatcher implements Runnable {
	static private final Logger logger = LoggerFactory.getLogger(ScriptUpdateWatcher.class);

	private ScriptManager scriptManager;
	private WatchService watcher;
	private File folder;
	
	private HashMap<File, Long> lastUpdate = new HashMap<File, Long>();

	public ScriptUpdateWatcher(ScriptManager scriptManager, File folder) {
		this.scriptManager = scriptManager;
		this.folder = folder;
	}
	
	@Override
	public void run() {
		try {
			watcher = FileSystems.getDefault().newWatchService();

			Path dir = Paths.get(folder.getAbsolutePath());
			dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			
			
			while(true) {
				WatchKey key;
				
				try {
					key = watcher.take();
				} catch(InterruptedException ex) {
					logger.info("ScriptUpdateWatcher interrupted");
					return;
				}

				long currentTime = System.currentTimeMillis();

				ArrayList<File> removedScripts = new ArrayList<File>();
				ArrayList<File> addedScripts = new ArrayList<File>();
				ArrayList<File> modifiedScripts = new ArrayList<File>();
				
				for (WatchEvent<?> event : key.pollEvents()) {
                   
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    WatchEvent.Kind<Path> kind = ev.kind();
                    
                    Path fileName = ev.context();
                     
                    
                    File f = new File(folder, fileName.toString());
                    
                    Long lastTime = lastUpdate.get(f);
                    
                    if(lastTime == null || currentTime-lastTime > 5000) {
                        logger.debug(kind.name() + ": " + fileName);
                    	lastUpdate.put(f, currentTime);
	                    if(kind == ENTRY_CREATE) {
	                    	
	                    	addedScripts.add(f);
	                    } else if(kind == ENTRY_DELETE) {
	                    	removedScripts.add(f);
	                    } else if(kind == ENTRY_MODIFY) {
	                    	modifiedScripts.add(f);
	                    }
                    }
                }
				
				scriptManager.scriptsChanged(addedScripts, removedScripts, modifiedScripts);
                 
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
				
			}
			
		} catch (IOException e1) {
			logger.error("WatchService could not be started", e1);
		}
	}

}

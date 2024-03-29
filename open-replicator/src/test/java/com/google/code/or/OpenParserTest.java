package com.google.code.or;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.or.binlog.BinlogEventListener;
import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.impl.event.XidEvent;

public class OpenParserTest {
	//
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenParserTest.class);

	/**
	 * 
	 */
	public static void main(String args[]) throws Exception {
		//
		final OpenParser op = new OpenParser();
		op.setStartPosition(4);
		op.setBinlogFileName("mysql_bin.000001");
		op.setBinlogFilePath("C:/Documents and Settings/All Users/Application Data/MySQL/MySQL Server 5.5/data");
		op.setBinlogEventListener(new BinlogEventListener() {
		    public void onEvents(BinlogEventV4 event) {
		    	if(event instanceof XidEvent) {
		    		LOGGER.info("{}", event);
		    	}
		    }
		});
		op.start();
		
		//
		LOGGER.info("press 'q' to stop");
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(String line = br.readLine(); line != null; line = br.readLine()) {
		    if(line.equals("q")) {
		        op.stop(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		        break;
		    }
		}
	}
}

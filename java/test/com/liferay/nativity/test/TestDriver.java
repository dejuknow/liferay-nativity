/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.nativity.test;

import com.liferay.nativity.modules.contextmenu.ContextMenuControlBase;
import com.liferay.nativity.modules.fileicon.FileIconControlBase;
import com.liferay.nativity.plugincontrol.NativityMessage;
import com.liferay.nativity.plugincontrol.win.WindowsNativityPluginControlImpl;

import flexjson.JSONSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gail Hernandez
 */
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_intitializeLogging();
		
		NativityMessage message =  new NativityMessage();
		message.setCommand("BLAH");
		List<String> items = new ArrayList<String>();
		items.add("ONE");
		message.setValue(items);
		
		JSONSerializer serializer = new JSONSerializer();
		_logger.debug(serializer.deepSerialize(message));

		_logger.debug("main");
		
		WindowsNativityPluginControlImpl nativityControl = new WindowsNativityPluginControlImpl();
		TestFileIconControl fileIconControl = new TestFileIconControl(nativityControl);
		TestContextMenuControl contextMenuControl = new TestContextMenuControl(nativityControl);

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(System.in));

		nativityControl.connect();

		String read = "";
		boolean stop = false;
		try {
			while (!stop) {
				_list = !_list;

				_logger.debug("Loop start...");

				_logger.debug("_enableFileIcons");
				_enableFileIcons(fileIconControl);

				_logger.debug("_setMenuTitle");
				_setMenuTitle(contextMenuControl);

				_logger.debug("_setRootFolder");
				_setRootFolder(nativityControl);

				_logger.debug("_setSystemFolder");
				_setSystemFolder(nativityControl);

				_logger.debug("_updateFileIcon");
				_updateFileIcon(fileIconControl);

				_logger.debug("_clearFileIcon");
				_clearFileIcon(fileIconControl);

				_logger.debug("Ready?");

				if (bufferedReader.ready()) {
					_logger.debug("Reading...");

					read = bufferedReader.readLine();

					_logger.debug("Read {}", read);

					if (read.length() > 0) {
						stop = true;
					}

					_logger.debug("Stopping {}", stop);
				}
			}
		} catch (IOException e) {
			_logger.error(e.getMessage(), e);
		}

		_logger.debug("Done");
	}

	private static void _clearFileIcon(FileIconControlBase fileIconControl) {
		if (_list) {
			String[] fileNames = new String[]
			{
				"C:/Users/liferay/Documents/liferay-sync/My Documents (test)/" +
				"temp",
				"C:/Users/liferay/Documents/liferay-sync/" +
				"My Documents (test)temp/Sync.pptx"};

			fileIconControl.removeFileIcons(fileNames);
		}
		else {
			fileIconControl.removeFileIcon(
				"C:/Users/liferay/Documents/liferay-sync/My Documents (test)/" +
				"temp");
		}

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static void _enableFileIcons(FileIconControlBase fileIconControl) {
		fileIconControl.enableFileIcons();

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static void _intitializeLogging() {
		File file = new File("java/nativity-log4j.xml");

		if (file.exists()) {
			DOMConfigurator.configure(file.getPath());
		}
	}

	private static void _setMenuTitle(ContextMenuControlBase contextMenuControl) {
		contextMenuControl.setContextMenuTitle("Test");

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static void _setRootFolder(WindowsNativityPluginControlImpl nativityControl) {
		nativityControl.setRootFolder("C:/Users/liferay/Documents/liferay-sync");

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static void _setSystemFolder(WindowsNativityPluginControlImpl nativityControl) {
		nativityControl.setSystemFolder("C:/Users/liferay/Documents/liferay-sync");

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static void _updateFileIcon(FileIconControlBase fileIconControl) {
		if (_list) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put(
				"C:/Users/liferay/Documents/liferay-sync/" +
				"My Documents (test)/temp", 1);

			map.put(
				"C:/Users/liferay/Documents/liferay-sync/" +
				"My Documents (test)/temp/Sync.pptx", 2);

			fileIconControl.setIconsForFiles(map);
		}
		else {
			fileIconControl.setIconForFile(
				"C:/Users/liferay/Documents/liferay-sync/" +
				"My Documents (test)/temp", 1);
		}

		try {
			Thread.sleep(_waitTime);
		}
		catch (InterruptedException e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static boolean _list = false; private static int _waitTime = 1000;
	private static Logger _logger = LoggerFactory.getLogger(
		TestDriver.class.getName());

}
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.nativity.modules.fileicon.win.WindowsFileIconControlImpl;
import com.liferay.nativity.plugincontrol.NativityPluginControl;

import java.util.Random;

/**
 * @author Gail Hernandez
 */
public class TestFileIconControl extends WindowsFileIconControlImpl{

	public TestFileIconControl(NativityPluginControl pluginControl) {
		super(pluginControl);
		_random = new Random();
	}

	@Override
	public int getIconForFile(String path) {
		return _random.nextInt(10);
	}


	private Random _random;

}
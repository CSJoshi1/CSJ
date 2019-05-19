package com.test.utils;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	

	private ExtentManager() {
	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d = new Date();
			String file = d.toString().replace(":", "_").replace(" ", "_") + ".html";
			try {
				extent = new ExtentReports(new File("Report/" + file).getAbsolutePath(), true,
						DisplayOrder.NEWEST_FIRST);
			} catch (Exception e) {
				e.printStackTrace();
			}
			extent.loadConfig(new File(System.getProperty("user.dir") + "//ReportsConfig.xml"));
			extent.addSystemInfo("Selenium Version", "3.6.0").addSystemInfo("Environment", "QA");
		}
		return extent;

	}

}

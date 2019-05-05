package com.demo.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.demo.utilities.*;

public class businessFunctions extends commonUtils{
	
	@Test
	public void login() throws FileNotFoundException, IOException {
		clearCache("chrome");
		setBrowser("chrome");
		navigate("http://demo.guru99.com/v4/index.php");
		DFsendkeys(getObject("loginPage_Username_Input"),"mngr192887");
		DFsendkeys(getObject("loginPage_Password_Input"), "YtysAmu");
		click(getObject("loginPage_LoginButton_input"));
	}
}

package com.hp.g11n.ss.test;



import com.hp.g11n.automation.passolo.ss.Application;
import org.junit.Test;

import com.hp.g11n.automation.passolo.ss.gui.Main;

public class MainTest {
    @Test
    public void testMainGUI(){
        String[] args={};
        //old main
        // Main.main(args);
        Application.main(args);
    }
}

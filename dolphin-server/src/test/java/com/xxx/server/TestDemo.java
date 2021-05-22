package com.xxx.server;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestDemo {
    @Test
    public void testDemo() {
        int i = 5/0;
        System.out.println("b");
            
    }

    @BeforeEach
    public void before(){
        System.out.println("a");
    }
    @AfterEach
    public void after(){
        System.out.println("c");
    }

}

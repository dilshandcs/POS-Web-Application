package com.micropos.controller;
import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortException;

@Component
public class SerialClass3 {



    public void OpenDrawer() {
    	SerialPort serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            int i=0;

            while(i<500) {
            serialPort.writeBytes("1".getBytes());//Write data to port
            i++;
        }
            serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

}
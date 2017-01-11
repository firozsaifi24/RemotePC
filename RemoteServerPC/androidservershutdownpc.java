/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package androidserver;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Firoz
 */
public class androidservershutdownpc {
    public static void main(String args[])
    {
        try
        {
            ServerSocket serverSocket=new ServerSocket(4444);
            System.out.println("Server started...");
            Socket clientSocket=serverSocket.accept();
            
            DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
            
            String str=(String)dis.readUTF();
            if(str.equals("shutdown"))
            {
                Runtime r=Runtime.getRuntime();
                Process proc=r.exec("shutdown -s -t 0");
            }
            else if(str.equals("restart"))
            {
                Runtime r=Runtime.getRuntime();
                Process proc=r.exec("shutdown -r -t 0");
            }
               
            serverSocket.close();
                                  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

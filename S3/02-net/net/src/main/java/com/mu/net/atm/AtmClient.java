package com.mu.net.atm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 12:08
 **/

public class AtmClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 12000;
        Scanner keyboard = new Scanner(System.in);
        boolean flag = true;

        try(Socket s = new Socket(host, port);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            Scanner sc = new Scanner(s.getInputStream());
        ){
            System.out.println("连接ATM服务器成功");
            do{
                System.out.println("欢迎使用ATM机");
                System.out.println("1.存款");
                System.out.println("2.取款");
                System.out.println("3.查询余额");
                System.out.println("4.退出");
                System.out.println("请输入要执行的操作：");
                String command = keyboard.nextLine();
                String response = null;
                if ("1".equalsIgnoreCase(command)){
                    pw.println("DEPOSIT 1 100");
                }else if ("2".equalsIgnoreCase(command)){
                    pw.println("WITHDRAW 1 10");
                }else if ("3".equalsIgnoreCase(command)) {
                    pw.println("BALANCE 1");
                }else{
                    pw.println("QUIT");
                    pw.flush();
                    flag = false;
                    break;
                }
                pw.flush();
                response = sc.nextLine();
                System.out.println("服务器的响应：" + response);
                Gson gson = new Gson();
                JsonModel<BankAccount> jm = gson.fromJson(response, new TypeToken<JsonModel<BankAccount>>(){}.getType());
                if (jm.getCode()==1){
                    BankAccount ba = jm.getData();
                    System.out.println(ba.getId() + "\t" + ba.getBalance());
                }else{
                    System.out.println(jm.getMessage());
                }
            }while (flag);
            System.out.println("ATM退出当前账号");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

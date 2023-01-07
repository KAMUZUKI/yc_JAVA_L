package com.mu.net.atm;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 14:07
 **/

public class BankTask implements Runnable {
    private Bank bank;
    private Socket socket;
    private boolean flag;

    public BankTask(Socket socket, Bank bank) {
        this.socket = socket;
        this.bank = bank;
        this.flag = true;
    }

    @Override
    public void run() {
        try (Socket s = this.socket;
             Scanner reader = new Scanner(s.getInputStream());
             PrintWriter pw = new PrintWriter(s.getOutputStream());
        ) {
            while (flag) {
                System.out.println("进入了...");
                // 读取客户端发送的请求 如果没有传输数据，会退出
                if (!reader.hasNext()) {
                    System.out.println("ATM客户端" + s.getRemoteSocketAddress() + "断开与服务器的连接...");
                    break;
                }
                // 如果有信息，读取信息
                String commend = reader.next();
                BankAccount account = null;
                JsonModel jm = new JsonModel();
                if ("DEPOSIT".equals(commend)) {
                    // 存款
                    int id = reader.nextInt();
                    double money = reader.nextDouble();
                    account = bank.deposit(id,money);
                } else if ("WITHDRAW".equals(commend)) {
                    // 取款
                    int id = reader.nextInt();
                    double money = reader.nextDouble();
                    account = bank.withdraw(id,money);
                } else if ("BALANCE".equals(commend)) {
                    // 查询余额
                    int id = reader.nextInt();
                    account = bank.search(id);
                } else if ("QUIT".equals(commend)) {
                    // 退出
                    System.out.println("ATM客户端要求主动断开，" + s.getRemoteSocketAddress() + "断开与服务器的连接...");
                    break;
                } else {
                    jm.setCode(0);
                    jm.setMessage("无效的命令");
                    Gson g = new Gson();
                    String json = g.toJson(jm);
                    pw.println(json);
                    pw.flush();
                }
                jm.setCode(1);
                jm.setData(account);
                Gson g = new Gson();
                String json = g.toJson(jm);
                pw.println(json);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

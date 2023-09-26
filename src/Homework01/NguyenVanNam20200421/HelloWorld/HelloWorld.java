package Homework01.NguyenVanNam20200421.HelloWorld;

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args){
        System.out.println("Ten cua ban la gi");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("Ten cua ban la: " + name);
    }
}

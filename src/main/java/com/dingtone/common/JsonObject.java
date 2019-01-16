package com.dingtone.common;

import java.util.Arrays;
import java.util.Random;

public class JsonObject {
/*
    public static void main(String[] args) {
        for(int i=0;i<=100;i++){
            if(i%2==0){
                System.out.println(i);
            }
            else{
                System.out.println(-i);
            }
        }

         }*/
//1.创建数组，用以存储扑克牌
static String[] pokers = new String[54];

    public static void main(String[] args) {
        pokers = newPoker();
        String[] pokers2 = upsetPoker(pokers);
        sendPoker(pokers2);
    }

    public static String[] newPoker() {
        String[] colors = {"红桃","黑桃","梅花","方片"};
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] kings = {"大王","小王"};

        int index = 0;
        for(int i = 0 ; i < numbers.length ; i ++) {
            for(int j = 0 ; j < colors.length ; j ++) {
                pokers[index ++] = colors[j] + numbers[i];
            }
        }
        System.arraycopy(kings, 0, pokers, index, 2);
        System.out.println("现有一副扑克牌" + Arrays.toString(pokers) + "\n");
        return pokers;
    }

    public static String[] upsetPoker(String[] array) {
        String[] newpokers = new String[pokers.length];
        boolean[] mark = new boolean[pokers.length];

        for(int i = 0 ; i < pokers.length ; i ++) {
            Random rd = new Random();
            int index = rd.nextInt(pokers.length);
            if(mark[index] == false) {
                newpokers[i] = pokers[index];
            }else {
                i --;
            }
        }
        pokers = Arrays.copyOf(newpokers, newpokers.length);
        System.out.println("洗过的牌：" + Arrays.toString(newpokers)+"\n");
        return newpokers;
    }

    public static void sendPoker(String[] array) {
        String[] one = new String[0];
        String[] two = new String[0];
        String[] three = new String[0];
        //String[] four = new String[0];

        for(int i = 0 ; i < pokers.length ; i++) {
            if(i % 3 == 0) {
                one = Arrays.copyOf(one, one.length+1);
                one[one.length-1] = pokers[i];
            }else if(i % 3 == 1) {
                two = Arrays.copyOf(two, two.length+1);
                two[two.length-1] = pokers[i];
            }else if(i % 3 == 2) {
                three = Arrays.copyOf(three, three.length+1);
                three[three.length-1] = pokers[i];
            }
         /*    else if(i % 4 == 3) {
            four = Arrays.copyOf(four, four.length+1);
                four[four.length-1] = pokers[i];
        }*/
        }

        //System.arraycopy(pokers, pokers.length-3, underpoker, 0, 3);

        System.out.println("玩家1:" + Arrays.toString(one));
        System.out.println("玩家2:" + Arrays.toString(two));
        System.out.println("玩家3:" + Arrays.toString(three));
        //System.out.println("玩家4:" + Arrays.toString(four));
    }

}



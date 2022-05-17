package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.TranscriptionDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunnableExperiment implements Runnable {
    private String name;

    public RunnableExperiment(String name) {
        this.name = name;
        System.out.println("Creating" + name);
    }

    @Override
    public void run() {

        System.out.println("Running" + name);

        for (int i = 0; i < 50; i++) {
            System.out.println(name + "运行： " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 如果字符串中有数字或连续的空格需要返回提示
     *
     * @param str
     * @return
     */
    public static String killSpaceAndNumber(String str) {
        String s = "";
        String result = str.replaceAll(" +", " ");
        if (str.length() != result.length()) {
            s += "有连续的空格 ";
        }
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(str);
        if (m.matches()) {
            s += "有数字 ";
        }
        return s;
    }

    public static List testList(List<Order> list) {
        /*for (Order order : list) {
            for (Order order1 : list) {
                if(order.getName()==order1.getName()){
                    list.remove(order);
                    list.remove(order1);
                }
            }
        }*/
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Order orderI = list.get(i);
                Order orderJ = list.get(j);
                if (orderI.getName() == orderJ.getName()) {
                    list.remove(orderI);
                    list.remove(orderJ);
                    j--;
                }
            }
        }
        return list;
    }

    /**
     * 排序
     *
     * @param list
     * @return
     */
    public static List sortById(List<Order> list) {
        //ArrayList<Order> result = new ArrayList<>();
        Collections.sort(list, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getIdStr().compareTo(o2.getIdStr());
            }
        });
       /* for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {

            }
        }*/
        return list;
    }

    /**
     * 判断各个时间段是否有重叠的时间
     *
     * @param list
     * @return
     */
    public String overlap(List<TranscriptionDTO> list) {
        String result = "";
        Collections.sort(list, new Comparator<TranscriptionDTO>() {
            @Override
            public int compare(TranscriptionDTO o1, TranscriptionDTO o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                TranscriptionDTO dTOI = list.get(i);
                TranscriptionDTO dTOJ = list.get(j);
                if (Double.parseDouble(dTOJ.getStartTime()) < Double.parseDouble(dTOI.getEndTime())
                        && !dTOJ.getStartTime().equals(dTOI.getStartTime())
                ) {
                    return dTOJ.getStartTime() + "时间重叠";
                }

            }
        }
        return result;
    }

    /**
     * 当单个大写字母前后有空格但没有句号时报错
     */
    public static boolean fullStop(String str) {
        boolean matches = str.matches("^.*\\s[A-Z]\\s+.*$");
        return matches;
    }


    public static class Main {
        public static void main(String[] args) {
            System.out.println(fullStop("<OVERLAP>Please add text</OVERLAP> D"));

            /*//多线程测试
            new Thread(new RunnableExperiment("C")).start();
            new Thread(new RunnableExperiment("D")).start();
            数字和连续空格的测试
            String s="<OVERLAP>Please add 0  text</OVERLAP>";
            System.out.println(s);
            System.out.println(killSpaceAndNumber(s));
            //list删除相同元素的测试
            List<Order> test = new ArrayList<>();
            test.add(new Order(6, "b"));
            test.add(new Order(7, "b"));
            test.add(new Order(8, "b"));
            test.add(new Order(9, "b"));
            test.add(new Order(10, "c"));
            test.add(new Order(11, "c"));
            test.add(new Order(1, "a"));
            test.add(new Order(2, "a"));
            test.add(new Order(3, "a"));
            test.add(new Order(4, "b"));
            test.add(new Order(5, "b"));
            //List list = testList(test);
            System.out.println(test);
            System.out.println("\n");
            System.out.println(sortById(test));
            TranscriptionDTO transcriptionDTO = new TranscriptionDTO();
            List<TranscriptionDTO> dtoList= Arrays.asList(new TranscriptionDTO());
            List<TranscriptionDTO> list=new ArrayList<>();*/

        }
    }


}

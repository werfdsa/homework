package com.nuist.homework0508.javabasicshow;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("aaa");
//        arrayList.add("bbb");
//        arrayList.add(0, "ccc");
//
//        System.out.println(arrayList);
//        System.out.println(arrayList.get(2));
//        System.out.println(arrayList.size());
//
//        for (String s : arrayList) {
//            System.out.println(s);
//        }
//
//
//        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.add("aaa");
//        linkedList.add("bbb");
//        linkedList.add("ccc");
//
//
//        System.out.println(linkedList);
//        System.out.println(linkedList.getFirst());
//        System.out.println(linkedList.getLast());
//
//        for(String s : linkedList){
//            System.out.println(s);
//        }


//        HashSet<String> hashSet = new HashSet<>();
//
//
//        hashSet.add("aaa");
//        hashSet.add("bbb");
//        hashSet.add("ccc");
//
//
//        System.out.println(hashSet);
//        System.out.println(hashSet.size());
//        System.out.println(hashSet.contains("ccc"));
//
//
//        for (String s : hashSet) {
//            System.out.println(s);
//        }
//
//
//        TreeSet<Integer> treeSet = new TreeSet<>();
//
//        treeSet.add(54);
//        treeSet.add(467);
//        treeSet.add(242);
//
//
//        System.out.println(treeSet);
//        System.out.println(treeSet.first());
//        System.out.println(treeSet.last());
//
//
//        for (Integer i: treeSet){
//            System.out.println(i.toString());
//        }


//        HashMap<String, Integer> hashMap = new HashMap<>();
//
//        hashMap.put("aaa", 1);
//        hashMap.put("bbb", 2);
//        hashMap.put("ccc", 3);
//
//
//        System.out.println(hashMap);
//        System.out.println(hashMap.get("bbb"));
//        System.out.println(hashMap.containsValue(3));
//
//        System.out.println(hashMap.keySet());
//        System.out.println(hashMap.values());
//
//
//        Iterator<Map.Entry<String, Integer>> it = hashMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Integer> entry = it.next();
//            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
//        }
//
//
//        TreeMap<String, Integer> treeMap = new TreeMap<>();
//
//        treeMap.put("aaa", 1);
//        treeMap.put("bbb", 2);
//        treeMap.put("ccc", 3);
//
//
//        System.out.println(treeMap);
//
//        System.out.println(treeMap.firstKey());
//        System.out.println(treeMap.lastKey());

//        File f = new File("D:\\Homework");
//        fileSearch(f,0);

//        File f = new File("D:\\Homework\\Test");
//        File copyFile = new File("D:\\Homework\\Copy");
//        fileCopy(f,copyFile);


//        Object o1 = new Object();
//        Object o2 = new Object();
//        Lock lock1 = new ReentrantLock();
//        Lock lock2 = new ReentrantLock();
//        Thread t1 = new Thread(()->{
//            System.out.println("正在试图获取o1的锁");
//            CD(1000);
//            lock1.lock();
//            System.out.println("正在访问o1");
//            CD(5000);
//            System.out.println("正在试图获取o2的锁");
//            CD(1000);
//            lock2.lock();
//            System.out.println("正在访问o2");
//            CD(2000);
//            lock2.unlock();
//            lock1.unlock();
//        });
//        Thread t2 = new Thread(()->{
//            System.out.println("正在试图获取o2的锁");
//            CD(1000);
//            lock2.lock();
//            System.out.println("正在访问o2");
//            CD(5000);
//            System.out.println("正在试图获取o1的锁");
//            CD(1000);
//            lock1.lock();
//            System.out.println("正在访问o1");
//            CD(2000);
//            lock1.unlock();
//            lock2.unlock();
//        });
//        t1.start();
//        t2.start();

        System.out.println(selectById(1));



    }

    public static int selectById(int id){
        int count = 0;
        String sql = "select count(*) from student where id = ?";
        try(Connection c = JDBC.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }


    public static int update(Student student){
        int result =0;
        String sql = "update student(name,gender,age) values(?,?,?)";
        try(Connection c = JDBC.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setInt(3,student.getAge());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    public static int delete(int id){
        int result = 0;
        String sql = "delete from student where id = ?";
        try(Connection c = JDBC.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static int  insert(Student student){
        int result = 0;
        String sql = "insert into student(name,gender,age) values (?,?,?)";
        try(Connection c = JDBC.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,student.getName());
            ps.setString(2,student.getGender());
            ps.setInt(3,student.getAge());
            result = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void CD(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void fileCopy(File file, File copyFile){
        for (File f : file.listFiles()){
            if(f.isDirectory()){
                File newFile = new File(copyFile,f.getName());
                newFile.mkdir();
                fileCopy(f,newFile);
            }else {
                try {
                    new File(copyFile,f.getName()).createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void fileSearch(File f, int level){
        StringBuilder space = new StringBuilder("");
        for (int i = 0; i < level; i++) {
            space.append(" ");
        }
        System.out.println(space+f.getName());
        space.append(" ");
        for (File file : f.listFiles()){
            if(file.isDirectory()){
                fileSearch(file, level+1);
            }else {
                System.out.println(space+file.getName());
            }

        }
    }
}






package com.example.ceciliahumlelu.algorithms_practice;

import android.location.Location;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println(sherlockAndAnagrams("ifailuhkqq"));
        //List<Long> arr = Arrays.asList(1L, 1L, 1L, 1L, 1L, 1L,1L, 1L, 1L, 1L, 1L, 1L);
        //System.out.println(countTriplets(arr,1));
        //System.out.println(countConsecutiveOne(6));
        //int[] a = new int[]{1,12,5,111,200,1000,10};
        //System.out.println(maximumToys(a,50));

        String a = "abcbaba";
        System.out.println(substrCount(5,a));

    }

    static long substrCount(int n, String s) {
        long count1 = 0,count2 = 0;
        Pattern pattern1 = Pattern.compile("\\w+?");
        Matcher matcher1 = pattern1.matcher(s);
        while(matcher1.find()){
            count1++;
        }
        Pattern pattern2 = Pattern.compile("(\\w+?)\\w??\\1");
        Matcher matcher2 = pattern2.matcher(s);
        while(matcher2.find()){
            count2++;
        }
        return count1+count2;

    }

    static String isValid(String s) {
        int[] a = new int[26];
        for(int i=0;i<s.length();i++){
            a[s.charAt(i)-'a']++;
        }
        Arrays.sort(a);
        int min =0,max = a[25];
        int minIndex=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==0) continue;
            min = a[i];
            minIndex = i;
            break;
        }

        if(min==max){
            return "YES";
        }else{
            if(max-min==1&&max>a[24]||min==1&&a[minIndex+1]==max){
                return "YES";
            }
            return "NO";
        }

    }

    static int alternatingCharacters(String s) {
        int count =0;
        char value = s.charAt(0);
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)-value==0){
                count++;
            }else{
                value = s.charAt(i);
            }
        }
        return count;
    }

    static int makeAnagram(String a, String b) {
        char[] newB = b.toCharArray();
        List<Character> bList = new ArrayList<>();
        for(int j=0;j<newB.length;j++){
            bList.add(newB[j]);
        }
        System.out.println('c'-'a');
        List<Character> c = new ArrayList<>();
        for(int i=0;i<a.length();i++){
            char value = a.charAt(i);
            int loc = bList.indexOf(value);
            if(loc>=0){
                bList.remove(loc);
                c.add(value);
            }
        }
        return a.length()+b.length()-2*c.size();

    }

    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        int[] a;
        int[] countArr = new int[201];
        double median;
        a = Arrays.copyOfRange(expenditure,0,0+d);
        for(int j=0;j<a.length;j++){
            countArr[a[j]]++;
        }

        for(int i = d; i< expenditure.length;i++){
            if(i!=d){
                countArr[expenditure[i-d-1]]--;
                countArr[expenditure[i-1]]++;
            }
            median = getMedian(d,countArr);
            if(expenditure[i]>=median*2){
                count++;
            }

        }
        return count;
    }

    private static double getMedian(int d, int[] countArr){
        int count = 0;
        int med;
        double median = 0.0;
        if(d%2==0){
            med = d / 2 ;
        }else{
            med = (d + 1) / 2;
        }
        int index = 0;
        for(int k=0;k<countArr.length;k++){
            if(count < med){
                count += countArr[k];
            }else{
                index = k-1;
                break;
            }
        }
        if(d % 2==0){
            if(med-count == 0){
                for(int i=index+1;i<countArr.length;i++){
                    if(countArr[i]>0){
                        median = (double )(index + i) / 2;
                        break;
                    }
                }
            }else{
                median = index;
            }
        }else{
            median = index;
        }
        return median;
    }

    private int countConsecutiveOne(int n){
        List<Integer> a = new ArrayList<>();
        while(n>0){
            int remainder = n % 2;
            a.add(remainder);
            n = n / 2;
        }
        int max = 0;
        for(int i=0; i<a.size();i++){
            int count = 0;
            if(a.get(i)==1){
                count++;
                int j = i+1;
                while(j<a.size()&&a.get(j)!=0 ){
                    count++;
                    j++;
                }
                if(count>=max){
                    max = count;
                }
            }
        }
        return max;
    }

    static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int total = 0;
        int count = 0;
        for(int j=0;j<prices.length;j++){
            if(total+prices[j]<=k){
                total = total + prices[j];
                count ++;
            }

        }
        return count;
    }

    static int sherlockAndAnagrams(String s) {
        int count = 0;
        String[] list = s.split("");
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            for(int x=1;x<=s.length()-i;x++){
                String sub = s.substring(i,i+x);
                if(map.containsKey(sub)){
                    int val = map.get(sub);
                    map.put(sub,val+1);
                }else{
                    map.put(sub,1);
                }
            }
        }

        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            int val = map.get(key);
            if(val!=1){
                count += val*(val-1)/2;
            }
        }
        return count;

    }

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int alice = 0,bob = 0;
        for(int i=0;i<a.size();i++){
            if(a.get(i)>b.get(i)){
                alice++;
            }else if(a.get(i)<b.get(i)){
                bob++;
            }
        }
        result.add(alice);
        result.add(bob);
        return result;
    }

    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long,Integer> map = new HashMap<>();
        for(int i=0; i<arr.size();i++){
            long val = arr.get(i);
            if(map.containsKey(val)){
                int count = map.get(val);
                map.put(val,count+1);
            }else{
                map.put(val,1);
            }
        }

        long count = 0;
        Set<Long> keys = map.keySet();
        if(r==1){
            for(Long key:keys){
                int value = map.get(key);
                count += value * (value-1) * (value-2) /(3*2*1);
            }
        }else{
            for(Long key:keys){
                if(keys.contains(key*r)&&keys.contains(key*r*r)){
                    count += map.get(key)*map.get(key*r)*map.get(key*r*r);
                }
            }
        }
        return count;
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {

        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<queries.size();i++){
            int type = queries.get(i).get(0);
            int value = queries.get(i).get(1);
            switch (type){
                case 1: if(map.containsKey(value)){
                    int count = map.get(value);
                    map.put(value,count+1);
                }else{
                    map.put(value,1);
                }
                break;
                case 2:if(map.containsKey(value)){
                    int count = map.get(value);
                    if(count-1<0){
                        map.remove(value);
                    }else{
                        map.put(value,count-1);
                    }
                }
                break;
                case 3:
                    if(map.values().contains(value)){
                        result.add(1);
                    }else{
                        result.add(0);
                    }
                    break;
            }
        }

        return result;
    }

    static long countInversions(int[] arr) {
        int[] helper = arr.clone();
       return mergeSort(arr,helper,0,arr.length-1);
    }

    private static long mergeSort(int[] arr, int[] helper, int start, int end) {
        if(start<end){
            int mid = (start+end)/2;
            long count = 0;
            count += mergeSort(arr,helper,start,mid);
            count += mergeSort(arr,helper,mid+1,end);
            count += merge(arr,helper,start,mid,end);
            return count;
        }else{
            return 0;
        }
    }

    private static long merge(int[] arr, int[] helper, int start, int mid, int end) {
        long count = 0;
        int i=start,j = mid+1,k=start;
        while(i<=mid||j<=end){
            if(i>mid){
                arr[k++]=helper[j++];
            }else if(j> end){
                arr[k++]=helper[i++];
            }else if(helper[i]<=helper[j]){
                arr[k++]=helper[i++];
            }else{
                arr[k++]=helper[j++];
                count+=mid+1-i;
            }
        }
        return count;
    }

    static class Node{
        Node left,right;
        int data;
        Node(int data){
            this.data=data;
            left=right=null;
        }
    }



    public static Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static int getHeight(Node root){
        int leftHeight = 1,rightHeight = 1;
        if(root.left==null &&root.right==null){
            return 0;
        }
        if(root.left !=null){
            leftHeight+=getHeight(root.left);
        }
        if(root.right!=null){
            rightHeight+=getHeight(root.right);
        }
        if(leftHeight<=rightHeight) return rightHeight;
        return leftHeight;
    }

}


package com.example.ceciliahumlelu.algorithms_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Linked {

    class Node{
        int data;
        Node next;
        Node(int d){
            data=d;
            next=null;
        }

    }

    public static Node removeDuplicates(Node head){
        if(head==null) return head;
        Node start = head;
        while(start.next!=null){

            if(start.data!=start.next.data){
                start = start.next;
            }else{
                if(start.next.next!=null){
                    start.next = start.next.next;
                }else{
                    start.next = null;
                }
            }

        }
        return start;
    }



}

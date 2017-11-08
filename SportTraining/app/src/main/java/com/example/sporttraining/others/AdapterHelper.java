package com.example.sporttraining.others;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Володимир on 01.08.2016.
 */
public class AdapterHelper {

    private final String GROUP_NAME = "muscule";
    private final String MUSCULE_NAME = "name";

    String[] nameGroup = new String[] {"Груди", "Руки", "Спина", "Плечі", "Ноги"};
    public String[] numberGroup = new String[]{"chest", "hand", "back", "shoulders", "legs"};

    String[] nameOfChest = new String[] {"Жим штанги лежачи", "Віджимання на брусах", "Віджимання від підлоги", "Жим лежачи на похилій лаві", "Жим гантелей лежачи", "Розведення гантелей на похилій лаві"};
    public String[] numberOfChest = new String[] {"zim.txt", "brusi.txt", "floor.txt", "zimcorner.txt", "zimhantel.txt", "rozvhantel.txt"};

    String[] nameOfHand = new String[] {"Підйом штанги на біцепс", "Підйом гантелей на біцепс", "Жим лежачи вузьким хватом", "Французький жим",
                                        "Вправа молот", "Підйом штанги зворотнім хватом", "Жим гантелі з-за голови"};
    public String[] numberOfHand = new String[] {"shtanha.txt", "hanteli.txt", "smallsize.txt", "french.txt", "molot.txt", "zvorotsh.txt", "zzahead.txt"};

    String[] nameOfBack = new String[] {"Підтягування", "Станова тяга", "Тяга штанги в нахилі", "Тяга гантелі одною рукою в нахилі"};
    public String[] numberOfBack = new String[] {"pidtah.txt", "stanova.txt", "tahashtanhi.txt", "tahahanteli.txt"};

    String[] nameOfShoulders = new String[] {"Підйом штанги сидячи", "Жим Арнольда"};
    public String[] numberOfShoulders = new String[] {"upshtanhasit.txt", "arnold.txt"};

    String[] nameOfLegs = new String[] {"Присідання(штанга на спині)", "Підйом на носки стоячи"};
    public String[] numberOfLegs = new String[] {"prusid.txt", "uponleg.txt"};

    Context ctx;

    ArrayList<Map<String, String>> groupData;

    ArrayList<Map<String, String>> childDataItem;

    ArrayList<ArrayList<Map<String, String>>> allChild;

    Map<String, String> map;

    public AdapterHelper(Context _ctx){
        ctx = _ctx;
    }

    SimpleExpandableListAdapter adapter;

    public SimpleExpandableListAdapter getAdapter(){
        //заповнюю групи
        groupData = new ArrayList<Map<String, String>>();
        for(String s : nameGroup){
            map = new HashMap<String, String>();
            map.put(GROUP_NAME, s);
            groupData.add(map);
        }
        String[] groupFrom = new String[]{GROUP_NAME};
        int[] groupTo = new int[]{android.R.id.text1};

        //for 1 group  - chest
        allChild = new ArrayList<ArrayList<Map<String,String>>>();
        childDataItem = new ArrayList<Map<String, String>>();
        for(String s : nameOfChest){
            map = new HashMap<String,String>();
            map.put(MUSCULE_NAME, s);
            childDataItem.add(map);
        }
        allChild.add(childDataItem);

        //for 2 group - hand
        childDataItem = new ArrayList<Map<String, String>>();
        for(String s : nameOfHand){
            map = new HashMap<String,String>();
            map.put(MUSCULE_NAME, s);
            childDataItem.add(map);
        }
        allChild.add(childDataItem);

        //for 2 group - back
        childDataItem = new ArrayList<Map<String, String>>();
        for(String s : nameOfBack){
            map = new HashMap<String,String>();
            map.put(MUSCULE_NAME, s);
            childDataItem.add(map);
        }
        allChild.add(childDataItem);

        //for 2 group - shoulders
        childDataItem = new ArrayList<Map<String, String>>();
        for(String s : nameOfShoulders){
            map = new HashMap<String,String>();
            map.put(MUSCULE_NAME, s);
            childDataItem.add(map);
        }
        allChild.add(childDataItem);

        //for 2 group - legs
        childDataItem = new ArrayList<Map<String, String>>();
        for(String s : nameOfLegs){
            map = new HashMap<String,String>();
            map.put(MUSCULE_NAME, s);
            childDataItem.add(map);
        }
        allChild.add(childDataItem);

        String[] childFrom = new String[]{MUSCULE_NAME};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                allChild,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        return adapter;
    }

    public String getGroupText(int groupPos){
        return ((Map<String, String>) (adapter.getGroup(groupPos))).get(GROUP_NAME);
    }

    public String getChildText(int groupPos, int childPos){
        return ((Map<String, String>) (adapter.getChild(groupPos, childPos))).get(MUSCULE_NAME);
    }

    public String getGroupChildText(int groupPos, int childPos){
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}

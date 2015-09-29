package com.fireant.oschat.utils;


import com.fireant.oschat.bean.JsonUser;

import java.util.Comparator;

public class PinyinComparator implements Comparator<JsonUser>
{

    public int compare(JsonUser user1, JsonUser user2)
    {
        if (user1.getPinyin().equals("@") || user2.getPinyin().equals("#")
                )
            return -1;
        else if (user1.getPinyin().equals("#") || user2.getPinyin().equals("@"))
            return 1;
        else
            return user1.getPinyin().compareTo(user2.getPinyin());
    }

}


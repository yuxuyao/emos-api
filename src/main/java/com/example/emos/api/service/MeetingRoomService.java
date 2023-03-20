package com.example.emos.api.service;

import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.pojo.TbMeetingRoom;

import java.util.ArrayList;
import java.util.HashMap;

public interface MeetingRoomService {
    public ArrayList<HashMap> searchAllMeetingRoom();

    public HashMap searchById(int id);

    public ArrayList<String> searchFreeMeetingRoom(HashMap param);

    PageUtils searchMeetingRoomByPage(HashMap param);

    int insert(TbMeetingRoom meetingRoom);

    int update(TbMeetingRoom meetingRoom);

    int deleteMeetingRoomByIds(Integer[] ids);
}

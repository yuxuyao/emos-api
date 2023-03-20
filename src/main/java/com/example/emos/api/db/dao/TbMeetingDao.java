package com.example.emos.api.db.dao;

import com.example.emos.api.db.pojo.TbMeeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbMeetingDao {
    public boolean searchMeetingMembersInSameDept(String uuid);
    public HashMap searchMeetingById(HashMap param);

    ArrayList<HashMap> searchOfflineMeetingByPage(HashMap param);

    long searchOfflineMeetingCount(HashMap param);

    int updateMeetingInstanceId(HashMap param);

    int insert (TbMeeting meeting);

    ArrayList<HashMap> searchOfflineMeetingInWeek(HashMap param);

    HashMap searchMeetingInfo(long id);

    HashMap searchCurrentMeetingInfo(long id);

    int deleteMeetingApplication(HashMap param);

    ArrayList<HashMap> searchOnlineMeetingByPage(HashMap param);

    long searchOnlineMeetingCount(HashMap param);

    ArrayList<HashMap> searchOnlineMeetingMembers(HashMap param);

    long searchCanCheckinMeeting(HashMap param);

    int updateMeetingPresent(HashMap param);




}
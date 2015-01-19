package com.dc.insta;

import com.dc.insta.core.Triple;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.jinstagram.Instagram;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.users.feed.UserFeedData;
import org.jinstagram.exceptions.InstagramException;

import java.util.*;

import static com.google.common.collect.Sets.newTreeSet;

public class FollowersList {
    private List<UserFeedData> userFollowedByList;
    private List<UserFeedData> userFollowsList;
    private List<UserFeedData> userFollowBackList;
    private UserInfoData userInfoData;
    private UserFeed userFollowed;
    private UserFeed userFollows;
    private Instagram instagram;

    public FollowersList() {
        userFollowedByList = Collections.emptyList();
        userFollowsList = Collections.emptyList();
        userFollowBackList = Collections.emptyList();
        userInfoData = null;
        userFollowed = null;
        userFollows = null;
        instagram = null;
    }

    public FollowersList(Instagram instagram) {
        try {
            userFollowedByList = Collections.emptyList();
            userFollowsList = Collections.emptyList();
            userFollowBackList = Collections.emptyList();
            userInfoData = instagram.getCurrentUserInfo().getData();
            userFollowed = null;
            userFollows = null;
            this.instagram = instagram;
        } catch (InstagramException e) {
            e.printStackTrace();
        }
    }

    public Triple<List<UserFeedData>, List<UserFeedData>, List<UserFeedData>> getFollowers() {
        if(instagram == null) return new Triple(userFollowedByList, userFollowsList, userFollowBackList);

        try {
            userFollowedByList = getUserFollowedBy();
            Thread.sleep(4000);
            userFollowsList = getUserFollows();
            userFollowBackList = toUserFeedDataList(
                    Lists.newArrayList(
                            Sets.intersection(
                                    buildSet(userFollowedByList),
                                    buildSet(userFollowsList)
                            ).toArray()
                    )
            );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Triple(userFollowedByList, userFollowsList, userFollowBackList);
    }

    public List<UserFeedData> getUserFollowedBy(){
        if(instagram == null) return userFollowedByList;

        try {
            userFollowed = instagram.getUserFollowedByList(userInfoData.getId());

            userFollowedByList = userFollowed.getUserList();

            UserFeed userFollowedByNextPage = instagram.getUserFollowedByListNextPage(userFollowed.getPagination());

            while (userFollowedByNextPage.getUserList() != null) {
                Thread.sleep(4000);
                userFollowedByList.addAll(userFollowedByNextPage.getUserList());
                userFollowedByNextPage = instagram.getUserFollowedByListNextPage(userFollowedByNextPage.getPagination());
            }
        } catch (InstagramException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userFollowedByList;
    }

    public List<UserFeedData> getUserFollows() {
        if(instagram == null) return userFollowsList;

        try {
            userFollows = instagram.getUserFollowList(userInfoData.getId());

            userFollowsList = userFollows.getUserList();

            UserFeed userFollowsNextPage = instagram.getUserFollowListNextPage(userFollows.getPagination());

            while (userFollowsNextPage.getUserList() != null) {
                Thread.sleep(4000);
                userFollowsList.addAll(userFollowsNextPage.getUserList());
                userFollowsNextPage = instagram.getUserFollowListNextPage(userFollowsNextPage.getPagination());
            }
        } catch (InstagramException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userFollowsList;
    }

    private <T> List<T> cloneList(List<T> list) {
        List<T> clone = new ArrayList<T>(list.size());
        for(T item: list) clone.add(item);
        return clone;
    }

    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public <T> List<UserFeedData> toUserFeedDataList(List<T> list) {
        List<UserFeedData> result = new ArrayList<UserFeedData>();
        for (Object object : list) {
            result.add((UserFeedData) object);
        }

        return result;
    }

    public Set<UserFeedData> buildSet(List<UserFeedData> list) {
        Set<UserFeedData> set = newTreeSet( new Comparator<UserFeedData>(){
            public int compare( final UserFeedData o1, final UserFeedData o2 ){
                return o1.getId().compareToIgnoreCase(o2.getId());
            }
        });

        set.addAll(list);

        return set;
    }
}

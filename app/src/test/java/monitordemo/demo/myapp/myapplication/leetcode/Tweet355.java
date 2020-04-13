package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zhao on 2020/4/13
 * 355. 设计推特
 */
public class Tweet355 {
    private List<TweetObj> tweetList;
    /**
     * 关注者(我关注的人)列表
     * 其实twitter中：关注我的人定义为followed; 我关注的人定义为following
     */
    private HashMap<Integer, List<Integer>> followeeMap;

    public Tweet355() {
        tweetList = new ArrayList<>();
        followeeMap = new HashMap<>(16);
    }


    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        tweetList.add(new TweetObj(userId, tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        System.out.println("user:" + userId);
        List<Integer> list = new ArrayList<>();
        int size = tweetList.size();
        if (size == 0) {
            return list;
        }
        //获取我关注的人列表
        List<Integer> followeeList = followeeMap.get(userId);
        for (int i = size - 1; i >= 0; i--) {
            TweetObj tweetObj = tweetList.get(i);
            System.out.println(tweetObj.userId + "," + tweetObj.tweetId);

            //自己发的 or 自己关注的人发的
            if (tweetObj.userId == userId || (followeeList != null && followeeList.contains(tweetObj.userId))) {
                list.add(tweetObj.tweetId);
            }
            //最多10条
            if (list.size() == 10) {
                break;
            }
        }
        return list;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        List<Integer> followeeList = followeeMap.get(followerId);
        if (followeeList == null) {
            followeeList = new ArrayList<>();
            followeeList.add(followeeId);
            followeeMap.put(followerId, followeeList);
        } else {
            followeeList.add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> followeeList = followeeMap.get(followerId);
        if (followeeList != null) {
            followeeList.remove(Integer.valueOf(followeeId));
        }
    }

    class TweetObj {
        int userId;
        int tweetId;

        public TweetObj(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }
    }
}



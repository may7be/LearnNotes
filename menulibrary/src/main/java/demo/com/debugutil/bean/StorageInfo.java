package demo.com.debugutil.bean;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/4
 * TODO:
 */
public class StorageInfo {
    public String path;
    public String state;
    public boolean isRemoveable;

    public StorageInfo(String path) {
        this.path = path;
    }

    public boolean isMounted() {
        return "mounted".equals(state);
    }
}

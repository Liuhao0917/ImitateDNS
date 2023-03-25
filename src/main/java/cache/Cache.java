package cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedHashMap<String, String> domainName2IP;
    private int cacheSize;
    public Cache(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / DEFAULT_LOAD_FACTOR) + 1;
        domainName2IP = new LinkedHashMap<String, String>(capacity, DEFAULT_LOAD_FACTOR, true)
        {
            protected boolean removeEldestEntry(Map.Entry eldest)
            {
                return size() > Cache.this.cacheSize;
            }
        };
    }

    public synchronized void addIP(String domainName, String IP){
        this.domainName2IP.put(domainName, IP);
    }

    public synchronized String getIP(String domainName){
        return this.domainName2IP.get(domainName);
    }

    public synchronized String removeIP(String domainName){
        return this.domainName2IP.remove(domainName);
    }

    public synchronized void clear()
    {
        this.domainName2IP.clear();
    }

    public synchronized int usedSize()
    {
        return this.domainName2IP.size();
    }
}

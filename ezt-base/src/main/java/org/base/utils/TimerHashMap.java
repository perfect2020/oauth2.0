package org.base.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangwr on 2016/4/5.
 */
public final class TimerHashMap<K,V> extends HashMap<K,V> {

    @Override
    public V remove(Object key) {
        return super.remove(key);
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    public void put(K key, V value,int removeAfterSeconds) {
        super.put(key, value);
        autoRemove(this, key,value,removeAfterSeconds);
    }

    public V getAndRemove(K key){
        return super.remove(key);
    }

    private void autoRemove(final Map<K, V> target, final Object key, final Object value, int afterSeconds){
        final Timer intervalTimer = new Timer();
        intervalTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                if(target.containsValue(value)){
                    target.remove(key);
                }
                intervalTimer.cancel();
            }
        }, afterSeconds * 1000);
    }
}

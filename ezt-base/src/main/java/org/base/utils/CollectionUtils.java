package org.base.utils;

import java.util.*;

/**
 * set集合util
 * Created by wangwr on 2015/6/9.
 */
public class CollectionUtils {

    public static <R> R first(Set<R> set){
        Iterator<R> it = set.iterator();
        if(it.hasNext()){
            return it.next();
        }
        return null;
    }

    public static <R> R first(R[] arr){
        if(arr.length>0){
            return arr[0];
        }
        return null;
    }

    /**
     * 判断某List是否是子集
     * @param subList
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isSubset(List<T> subList,List<T> list){
        if(subList==null || list==null || subList.isEmpty() || list.isEmpty()){
            return false;
        }
        List<T> subList1 = new ArrayList();
        subList1.addAll(subList);

        for(T t : list){
            if(subList1.contains(t)){
                subList1.remove(t);
            }
        }
        if(subList1.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * some集合是否有部分元素在list集合中
     * @param some
     * @param list
     * @param <T>
     * @return
     */
    public static<T> boolean isSomeInList(List<T> some,List<T> list){
        if(some==null || some.isEmpty() || list==null || list.isEmpty()){
            return false;
        }

        for(T t : some){
            if(list.contains(t)){
                System.out.println(t);
                return true;
            }
        }
        return false;
    }

    /**
     * 将集合按某字符连接：
     * 如将[1,2,3]转为1,2,3
     * @param ts
     * @param splitStr
     * @param <T>
     * @return
     */
    public static<T> String join(Collection<T> ts,String splitStr){
        if(ts==null || ts.isEmpty()){
            return "";
        }
        if(splitStr==null){
            throw new RuntimeException("分隔符不能为空");
        }

        StringBuffer sb = new StringBuffer();
        Iterator<T> iterator = ts.iterator();
        int i = 0;
        while(iterator.hasNext()){
            i++;
            T t = iterator.next();
            sb.append(t);
            if(i!=ts.size()){
                sb.append(splitStr);
            }
        }
        return sb.toString();
    }

    /**
     * 去除重复元素
     * @param ts
     * @param <T>
     * @return
     */
    public static<T> Set removeRepetition(Collection<T> ts){
        if(ts==null || ts.isEmpty()){
            return Collections.emptySet();
        }
        Map<T,Object> map = new LinkedHashMap();
        for(T t : ts){
            if(!map.containsKey(t)){
                map.put(t,-1);
            }
        }
        Set<T> set = map.keySet();
        return set;
    }

}

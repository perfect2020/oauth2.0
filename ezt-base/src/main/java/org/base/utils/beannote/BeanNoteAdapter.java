package org.base.utils.beannote;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.base.utils.JavaTypeUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwr on 2016.3.24.
 */
public class BeanNoteAdapter {

    public static List<Node> readNodes(Class<?> clazz){
        return readNodes(clazz,1);
    }

    private static List<Node> readNodes(Class<?> clazz,int index){
        List<Node> nodes = new ArrayList<Node>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            Node node = new Node();
            node.setIndex(index);
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            node.setNodeKey((jsonProperty==null||jsonProperty.value().length()<=0)?field.getName():jsonProperty.value());
            Note note = field.getAnnotation(Note.class);
            node.setNodeNote(note==null?"-":note.value());
            Class<?> fc = (Class<?>) field.getGenericType();
            if(!JavaTypeUtil.isNormal(fc)){//递归
                node.setSubNodes(readNodes(field.getType(),index+1));
            }
            nodes.add(node);
        }
        return nodes;
    }

}

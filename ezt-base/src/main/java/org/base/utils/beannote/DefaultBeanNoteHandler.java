package org.base.utils.beannote;

import org.base.utils.StrUtil;

import java.util.List;

/**
 * Created by wangwr on 2016.3.24.
 */
public class DefaultBeanNoteHandler implements BeanNoteHandler {

    @Override
    public String noteHandler(List<Node> nodes) {
        return stringfyNote(nodes,0);
    }

    protected String stringfyNote(List<Node> nodes,int index){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StrUtil.repeat(StrUtil.TAB_CHAR,index)).append("{").append(StrUtil.CLER);
        for(Node node:nodes){
            stringBuffer
                    .append(StrUtil.repeat(StrUtil.TAB_CHAR,node.getIndex()))
                    .append(String.format("\"%s\":-%s,",node.getNodeKey(),node.getNodeNote())).append(StrUtil.CLER);
            if(node.getSubNodes().size()>0){
                stringBuffer.append(stringfyNote(node.getSubNodes(),node.getIndex()));
            }
        }
        stringBuffer.append(StrUtil.repeat(StrUtil.TAB_CHAR,index)).append("}").append(StrUtil.CLER);
        return stringBuffer.toString();
    }
}

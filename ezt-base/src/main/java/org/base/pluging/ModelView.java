package org.base.pluging;

import org.base.utils.JsonUtil;

import java.util.Map;

/**
 * Created by wangwr on 2016/4/7.
 */
public class ModelView {

    private String viewLocation;

    private Object model;

    private Map<String,Object> modelMap;

    public ModelView(String viewLocation, Object model) {
        this.model = model;
        this.viewLocation = viewLocation;
    }

    public ModelView(String viewLocation) {
        this.viewLocation = viewLocation;
    }

    public Map<String, Object> getModelMap() {
        if(modelMap==null&&model!=null){
            modelMap = JsonUtil.toMap(JsonUtil.toJson(model));
        }
        return modelMap;
    }

    public void setModelMap(Map<String, Object> modelMap) {
        this.modelMap = modelMap;
    }

    public String getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(String viewLocation) {
        this.viewLocation = viewLocation;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}

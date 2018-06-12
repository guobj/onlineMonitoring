package com.nz.onlineMonitoring.view.mapper;

import com.nz.onlineMonitoring.view.model.VDevStatus;

import java.util.List;
import java.util.Map;

public interface VDevStatusMapper {

	List<VDevStatus> deviceStatusList(Map<String, Object> map);

	Integer count(Map<String, Object> map);
}

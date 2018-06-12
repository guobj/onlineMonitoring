package com.nz.onlineMonitoring.view.service;

import com.nz.onlineMonitoring.view.model.VDevStatus;

import java.util.List;
import java.util.Map;

public interface VDevStatusService {

	List<VDevStatus> deviceStatusList(Map<String, Object> map, VDevStatus vDevStatus);
}

package com.stevenLee.msm.service;

import java.util.Map;

public interface msmService {
    boolean send(String PhoneNumbers, String templateCode,
                 Map<String,Object> param);
}

package com.apcompany.api.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import com.apcompany.api.pojo.VideoAccount;
import com.google.common.collect.Lists;

@Configuration
public class VideoSecureConfig {
	
	private List<VideoAccount> configList = Lists.newArrayList();
	
	static{
	   	
	

    }
	
	public VideoAccount getFreeOne(){
		for(VideoAccount v:configList){
			if(v.getStatus()==0){
				return v;
			}
			continue;
		}
		return null;
	}

	public List<VideoAccount> getConfigList() {
		return configList;
	}

	public void setConfigList(List<VideoAccount> configList) {
		this.configList = configList;
	}

	
	
}

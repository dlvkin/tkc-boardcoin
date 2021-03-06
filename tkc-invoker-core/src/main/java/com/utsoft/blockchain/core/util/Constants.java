package com.utsoft.blockchain.core.util;
/**
 * 内部使用
 * @author hunterfox
 * @date: 2017年8月1日
 * @version 1.0.0
 */
public class Constants {

	
	 public static final String GOSSIPWAITTIME = "fabric.gossipWaitTime";
	 public static final String INVOKEWAITTIME = "fabric.invokeWaitTime";
	 public static final String DEPLOYWAITTIME = "fabric.deployWaitTime";
	 public static final String PROPOSALWAITTIME = "fabric.proposalWaitTime";
	 
	 public static final int FABRIC_MANAGER_INVALID = 1;
	 public static final int FABRIC_MANAGER_VALID = 0;
	 /**
	  * 默认过期时间
	  */
	 public static final long REDIS_EXPIRE_TTL = 60*60*24;
	 public static final String TKC_PREFIX ="TKCBC";
	 
	 public static final  String TKC_TRANSFER_MOVE = "TKC_TRANSFER_MOVE";
}

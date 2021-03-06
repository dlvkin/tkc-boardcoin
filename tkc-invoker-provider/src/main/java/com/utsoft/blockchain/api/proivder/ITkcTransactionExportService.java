package com.utsoft.blockchain.api.proivder;
import com.utsoft.blockchain.api.exception.ServiceProcessException;
import com.utsoft.blockchain.api.pojo.BaseResponseModel;
import com.utsoft.blockchain.api.pojo.TkcQueryDetailRspVo;
import com.utsoft.blockchain.api.pojo.TkcSubmitRspVo;
import com.utsoft.blockchain.api.pojo.TkcTransactionBlockInfoVo;
import com.utsoft.blockchain.api.pojo.TransactionVarModel;

/**
 * tkc 区块链交易，查询 对外服务器rpc 接口
 * <ul>
 *    <li>transfer accounts</li>
 *    <li>query accounts info</li>
 *    <li>query transaction block info</li>
 * </ul>
 * @author hunterfox
 * @date: 2017年8月1日
 * @version 1.0.0
 */
public interface ITkcTransactionExportService {

	
    /**
     * 转账交易
     * @param model
     * @return 返回提交结果
     * @throws ServiceProcessException
     */
	public BaseResponseModel<TkcSubmitRspVo> tranfer(TransactionVarModel model);
	
	/**
	 * 查询个人账号余额
	 * @param applyCategory 业务代码
	 * @param from 来源账号
	 * @param created   created 10位提交时间戳
	 * @param sign 交易签名 sign=md5(applyCategory=1&from=2&created=xxx)
	 * @return 
	 */
	BaseResponseModel<TkcQueryDetailRspVo> getTransactionDetail(String applyCategory,String from,String created,String sign);
	
	/**
	 * 根据交易 txId 查询 block info
	 * @param applyCategory 业务代码
	 * @param from 来源账号
	 * @param txId
	 * @param created  created 10位提交时间戳
	 * @param sign 交易签名 sign=md5(applyCategory=1&from=1&txId=xxxx&created=xxx&)
	 * @return
	 */
	BaseResponseModel<TkcTransactionBlockInfoVo> listStockChanges(String applyCategory,String from,String txId,String created,String sign);
	  
}
